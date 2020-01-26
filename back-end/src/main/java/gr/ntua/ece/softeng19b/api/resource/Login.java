package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.Map;

public class Login extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // This is a dummy login implementation for the sake of the front-end example app
        return new JsonMapRepresentation(Map.of("token", "dummy-user-token"));
    }
}
