package gr.ntua.ece.softeng19b.client;

public class ServerResponseException extends RuntimeException {

    private final int statusCode;

    public ServerResponseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
