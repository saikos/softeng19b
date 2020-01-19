package gr.ntua.ece.softeng19b


import gr.ntua.ece.softeng19b.client.RestAPI
import spock.lang.Shared
import spock.lang.Specification

class FunctionalTest extends Specification{

    @Shared RestAPI caller1 = new RestAPI()
    @Shared RestAPI caller2 = new RestAPI()

    def "T01. Health check status is OK"() {
        given:
        String status = caller1.healthCheck()

        expect:
        status == "OK"

    }

    //Implement all of the above
    def "T02. The database is reset successfully"() {
        expect:
        caller1 != null
    }

    def "T03. Admin logs in successfully"() {
        expect:
        caller1 != null
    }

    def "T04. Admin creates a new user"() {
        expect:
        caller1 != null
    }

    def "T05. User logs in"() {
        expect:
        caller1 != null
    }

    def "T06. User retrieves ActualTotalLoad tuple for 2000-01-01"() {
        expect:
        caller1 != null
    }

    def "T07. Admin limits the quota of the new user"() {
        expect:
        caller1 != null
    }

    def "T08. User cannot read ActualTotalLoad tuple for 2000-01-02 due to quota limit"() {
        expect:
        caller1 != null
    }

    def "T09. Admin updates the quota of the new user again"() {
        expect:
        caller1 != null
    }

    def "T10. Admin logs out"() {
        expect:
        caller1 != null
    }

    def "T11. User retrieves ActualTotalLoad tuple for 2000-01-02"() {
        expect:
        caller1 != null
    }

    def "T12. User uploads an ActualTotalLoad dataset"() {
        expect:
        caller1 != null
    }

    def "T13. User logs out"() {
        expect:
        caller1 != null
    }

}
