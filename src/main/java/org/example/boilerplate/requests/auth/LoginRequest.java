package org.example.boilerplate.requests.auth;

import org.javawebstack.validator.Rule;

public class LoginRequest {
    @Rule({"required", "string"})
    public String userName;

    @Rule({"required", "string"})
    public String password;
}
