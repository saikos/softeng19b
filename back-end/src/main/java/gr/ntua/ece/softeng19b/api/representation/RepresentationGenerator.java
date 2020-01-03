package gr.ntua.ece.softeng19b.api.representation;

import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import org.restlet.representation.Representation;

import java.util.List;

public interface RepresentationGenerator {

    public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result);

}
