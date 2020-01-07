package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.api.resource.ActualTotalLoadForSpecificDate;
import gr.ntua.ece.softeng19b.api.resource.HealthCheck;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.attach("/HealthCheck", HealthCheck.class);

        /*
         All routes accept a `format` query param (optional, either json or csv, default is json)
         All year, month and date attributes in routes are in the ISO-8601 format
           (YYYY, YYYY-MM, YYYY-MM-DD respectively)
         */
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/date/{date}",
            ActualTotalLoadForSpecificDate.class
        );


        return router;
    }

}
