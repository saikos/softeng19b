package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class AddUser extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        //create new user
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
