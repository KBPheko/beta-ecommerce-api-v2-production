package com.foodapi.betaecommerceapiv2.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "This is the authentication request model.")
public class AuthenticationRequest {

    @Schema(description = "This is the authentication request's email.", example = "abc123@gmail.com")
    private String email;

    @Schema(description = "This is the authentication request's password.", example = "12345678")
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
