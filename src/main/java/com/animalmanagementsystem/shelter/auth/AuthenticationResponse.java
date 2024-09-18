package com.animalmanagementsystem.shelter.auth;

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;

        public Builder() {
            //Builder pattern
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token);
        }
    }
}
