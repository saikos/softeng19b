package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class ImportActualTotalLoadDataSet extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // Import dataset
        // Use the Restlet's FileUpload extension, as demonstrated here:
        // https://restlet.talend.com/documentation/user-guide/2.4/extensions/fileupload
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
