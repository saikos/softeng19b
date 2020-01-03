package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadParams;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class ActualTotalLoad extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date query parameters
        Integer year = parseYear(getQueryValue("year"));
        Integer month = parseMonth(getQueryValue("month"));
        Integer day = parseDay(year, month, getQueryValue("day"));

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {
            if (day != null) {
                List<ActualTotalLoadForSpecificDay> result = dataAccess.fetchActualDataLoadForSpecificDay(
                        areaName,
                        resolution,
                        year,
                        month,
                        day
                );
                return format.generateRepresentation(result);
            }
        } catch (DataAccessException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Data access exception: " + e.getMessage(), e);
        }

        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);

    }


}
