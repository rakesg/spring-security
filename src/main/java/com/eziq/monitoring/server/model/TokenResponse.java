package com.eziq.monitoring.server.model;

public class TokenResponse {
    private String token;
    private String valid_from;
    private String valid_till;
    private String user;
    private String iss;
    private String status;

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    public String getValid_till() {
        return valid_till;
    }

    public void setValid_till(String valid_till) {
        this.valid_till = valid_till;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token + '\'' +
                ", valid_from='" + valid_from + '\'' +
                ", valid_till='" + valid_till + '\'' +
                ", user='" + user + '\'' +
                ", iss='" + iss + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
