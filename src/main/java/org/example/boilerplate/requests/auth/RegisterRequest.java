package org.example.boilerplate.requests.auth;

import org.javawebstack.validator.Rule;

public class RegisterRequest {
    @Rule({"required", "string(6)"})
    public String username;

    @Rule({"required", "email"})
    public String email;

    @Rule({"required", "string(6)"})
    public String password;
}
