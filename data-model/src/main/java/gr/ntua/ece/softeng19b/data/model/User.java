package gr.ntua.ece.softeng19b.data.model;

public class User {

    private String username;
    private String email;
    private int requestsPerDayQuota; //negative values indicate no quota

    public User() {
        //Keep this for json encoding/decoding
    }

    public User(String username, String email, int requestsPerDayQuota) {
        this.username = username;
        this.email = email;
        this.requestsPerDayQuota = requestsPerDayQuota;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRequestsPerDayQuota(int requestsPerDayQuota) {
        this.requestsPerDayQuota = requestsPerDayQuota;
    }

    public int getRequestsPerDayQuota() {
        return requestsPerDayQuota;
    }

}
