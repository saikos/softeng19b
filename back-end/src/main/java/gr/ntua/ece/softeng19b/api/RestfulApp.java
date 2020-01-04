package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.api.resource.ActualTotalLoad;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.routing.TemplateRoute;
import org.restlet.routing.Variable;

import java.util.HashMap;
import java.util.Map;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setDefaultMatchingQuery(true);

        /*
         All routes accept the following query params:
         - format (optional, either json or csv, default is json)
         - (year, month,   date): only one of three is required, in the ISO-8601 format
           (YYYY, YYYY-MM, YYYY-MM-DD respectively)
         */
        router.attach("/ActualTotalLoad/{AreaName}/{Resolution}", ActualTotalLoad.class);


        return router;
    }

}
