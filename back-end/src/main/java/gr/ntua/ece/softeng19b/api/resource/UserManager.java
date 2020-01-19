package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class UserManager extends EnergyResource {

    @Override
    protected Representation get() throws ResourceException {
        //read existing user
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }


    @Override
    protected Representation put(Representation entity) throws ResourceException {
        //update existing user
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
