package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.Collections;

public class Logout extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // This is a dummy logout implementation for the sake of the front-end example app
        return new JsonMapRepresentation(Collections.emptyMap());
    }
}
