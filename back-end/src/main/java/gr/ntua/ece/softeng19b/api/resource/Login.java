package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class Login extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        //login the user
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
