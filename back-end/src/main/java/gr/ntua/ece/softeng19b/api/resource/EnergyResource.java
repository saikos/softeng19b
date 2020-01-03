package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class EnergyResource extends ServerResource {

    private static final String EMPTY_STRING = "";

    static Format parseFormat(String format) {
        Optional<Format> optional = Arrays.stream(Format.values()).
                filter( (Format f) -> f.name().equalsIgnoreCase(format)).
                findFirst();
        return optional.orElse(Format.JSON);
    }

    static Integer parseYear(String year) {
        //TODO: Implement this
        return 2019;
    }

    static Integer parseMonth(String month) {
        //TODO: Implement this
        return 10;
    }

    static Integer parseDay(Integer year, Integer month, String day) {
        //TODO: Implement this
        return 1;
    }


    protected String getAttributeDecoded(String attr) {
        String value = getAttribute(attr);
        return value == null ?  null : sanitize(URLDecoder.decode(value, StandardCharsets.UTF_8));
    }

    protected String getMandatoryAttribute(String attr, String message) {
        String value = getAttributeDecoded(attr);
        if (value.length() == 0) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, message);
        }
        return value;
    }

    private static String sanitize(String s) {
        return s == null ? EMPTY_STRING : s.trim();
    }

}
