package gr.ntua.ece.softeng19b.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.matching.MultipartValuePatternBuilder
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay
import gr.ntua.ece.softeng19b.data.model.User
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

import static com.github.tomakehurst.wiremock.client.WireMock.*

@Stepwise
class RestAPISpecification extends Specification {

    private static final String TOKEN1 = "token1"
    private static final String TOKEN2 = "token2"

    private static final int MOCK_SERVER_PORT = 8766

    private static final String JSON_PAYLOAD_1 = """
[
  {
    "Source": "entso-e",
    "DataSet": "ActualTotalLoad",
    "AreaName": "Greece",
    "AreaTypeCode": "GR",
    "MapCode": "Foo",
    "ResolutionCode": "PT60M",
    "Year": 2000,
    "Month": 1,
    "Day": 1,
    "ActualTotalLoadValue": 0.0
  }
]
""".stripIndent()

    private static final String JSON_PAYLOAD_2 = """
[
  {
    "Source": "entso-e",
    "DataSet": "ActualTotalLoad",
    "AreaName": "Greece",
    "AreaTypeCode": "GR",
    "MapCode": "Foo",
    "ResolutionCode": "PT60M",
    "Year": 2000,
    "Month": 1,
    "Day": 2,
    "ActualTotalLoadValue": 0.0
  }
]
""".stripIndent()

    @Shared WireMockServer wms
    @Shared RestAPI caller1 = new RestAPI("localhost", MOCK_SERVER_PORT)
    @Shared RestAPI caller2 = new RestAPI("localhost", MOCK_SERVER_PORT)

    def setupSpec() {
        wms = new WireMockServer(WireMockConfiguration.options().httpsPort(MOCK_SERVER_PORT))
        wms.start()
    }

    def cleanupSpec() {
        wms.stop()
    }

    def "T01. Health check status is OK"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/energy/api/HealthCheck")
            ).willReturn(
                okJson('{"status":"OK"}')
            )
        )

        when:
        String status = caller1.healthCheck()

        then:
        status == "OK"
    }

    def "T02. The database is reset successfully"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Reset")
            ).willReturn(
                okJson('{"status":"OK"}')
            )
        )

        when:
        String status = caller1.resetDatabase()

        then:
        status == "OK"
    }

    def "T03. Admin logs in successfully"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Login")
            ).withRequestBody(
                equalTo(ClientHelper.encode([username:"admin", password:"321nimda"]))
            ).willReturn(
                okJson("""{"token":"${TOKEN1}"}""")
            )
        )

        when:
        caller1.login("admin", "321nimda")

        then:
        caller1.isLoggedIn()
    }

    def "T04. Admin creates a new user"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Admin/users")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            )
            .withRequestBody(
                equalTo(ClientHelper.encode([username:"user", email:"user@ntua.gr", password:"4321resu", requestsPerDayQuota:"100"]))
            ).willReturn(
                okJson('{"username":"user", "email":"user@ntua.gr","requestsPerDayQuota":100}')
            )
        )

        when:
        User user = caller1.addUser("user", "user@ntua.gr", "4321resu", 100)

        then:
        user.getUsername() == "user" &&
        user.getEmail() == "user@ntua.gr" &&
        user.getRequestsPerDayQuota() == 100
    }

    def "T05. User logs in"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Login")
            ).withRequestBody(
                equalTo(ClientHelper.encode([username:"user", password:"4321resu"]))
            ).willReturn(
                okJson("""{"token":"${TOKEN2}"}""")
            )
        )

        when:
        caller2.login("user", "4321resu")

        then:
        caller2.isLoggedIn()
    }

    def "T06. User retrieves ActualTotalLoad tuple for 2000-01-01"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/energy/api/ActualTotalLoad/Greece/PT60M/date/2000-01-01?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                okJson(JSON_PAYLOAD_1)
            )
        )

        when:
        List<ATLRecordForSpecificDay> records = caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2000, 1, 1),
            Format.JSON
        )

        then:
        records.size() == 1
    }

    def "T07. Admin limits the quota of the new user"() {
        given:
        wms.givenThat(
            put(
                urlEqualTo("/energy/api/Admin/users/user")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            ).withRequestBody(
                equalTo(ClientHelper.encode([email:"user@ntua.gr", requestsPerDayQuota:"1"]))
            ).willReturn(
                okJson('{"username":"user", "email":"user@ntua.gr","requestsPerDayQuota":1}')
            )
        )

        when:
        User user = caller1.updateUser(new User("user", "user@ntua.gr", 1))

        then:
        user.getRequestsPerDayQuota() == 1
    }

    def "T08. User cannot read ActualTotalLoad tuple for 2000-01-02 due to quota limit"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/energy/api/ActualTotalLoad/Greece/PT60M/date/2000-01-02?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                aResponse().withStatus(402)
            )
        )

        when:
        caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2000, 1, 2),
            Format.JSON
        )

        then:
        ServerResponseException exception = thrown()
        exception.getStatusCode() == 402
    }

    def "T09. Admin updates the quota of the new user again"() {
        given:
        wms.givenThat(
            put(
                urlEqualTo("/energy/api/Admin/users/user")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            ).withRequestBody(
                equalTo(ClientHelper.encode([email:"user@ntua.gr", requestsPerDayQuota:"10"]))
            ).willReturn(
                okJson('{"username":"user", "email":"user@ntua.gr","requestsPerDayQuota":10}')
            )
        )

        when:
        User user = caller1.updateUser(new User("user", "user@ntua.gr", 10))

        then:
        user.getRequestsPerDayQuota() == 10
    }

    def "T10. Admin logs out"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Logout")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN1)
            )
        )

        when:
        caller1.logout()

        then:
        !caller1.isLoggedIn()
    }

    def "T11. User retrieves ActualTotalLoad tuple for 2000-01-02"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/energy/api/ActualTotalLoad/Greece/PT60M/date/2000-01-02?format=json")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).willReturn(
                okJson(JSON_PAYLOAD_2)
            )
        )

        when:
        List<ATLRecordForSpecificDay> records = caller2.getActualTotalLoad(
                "Greece",
                "PT60M",
                LocalDate.of(2000, 1, 2),
                Format.JSON
        )

        then:
        records.size() == 1
    }

    def "T12. User uploads an ActualTotalLoad dataset"() {
        given:
        String csv = Paths.get(getClass().getResource("/test-atl.csv").toURI()).toString()
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Admin/ActualTotalLoad")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            ).withMultipartRequestBody(
                new MultipartValuePatternBuilder().
                    withName("file").
                    withBody(
                        binaryEqualTo(
                            Base64.mimeEncoder.encode(new File(csv).getBytes())
                        )
                    )
            ).willReturn(
                okJson('{"totalRecordsInFile":2, "totalRecordsImported":2,"totalRecordsInDatabase":4}')
            )
        )

        when:
        ImportResult importResult = caller2.importFile("ActualTotalLoad", Path.of(csv))

        then:
        importResult.totalRecordsInFile == 2 &&
        importResult.totalRecordsImported == 2 &
        importResult.totalRecordsInDatabase == 4 //2 (csv) + 2 (2000-01-XX test data)
    }

    def "T13. User logs out"() {
        given:
        wms.givenThat(
            post(
                urlEqualTo("/energy/api/Logout")
            ).withHeader(
                RestAPI.CUSTOM_HEADER, equalTo(TOKEN2)
            )
        )

        when:
        caller2.logout()

        then:
        !caller2.isLoggedIn()
    }

    def "T14. Anonymous users cannot access protected resources"() {
        given:
        wms.givenThat(
            get(
                urlEqualTo("/energy/api/ActualTotalLoad/Greece/PT60M/date/2000-01-02?format=json")
            ).willReturn(
                aResponse().withStatus(401)
            )
        )

        when:
        caller2.getActualTotalLoad(
            "Greece",
            "PT60M",
            LocalDate.of(2000, 1, 2),
            Format.JSON
        )

        then:
        ServerResponseException exception = thrown()
        exception.getStatusCode() == 401
    }

}