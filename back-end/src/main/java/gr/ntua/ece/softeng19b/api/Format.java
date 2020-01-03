package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.ActualTotalLoadForSpecificDay;
import org.restlet.representation.Representation;

import java.util.List;

public enum Format implements RepresentationGenerator {
    JSON {

        public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }

    },
    CSV {
        public Representation generateRepresentation(List<ActualTotalLoadForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
    };
}
