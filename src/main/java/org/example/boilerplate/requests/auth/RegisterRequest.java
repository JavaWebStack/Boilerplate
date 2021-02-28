package org.example.boilerplate.requests.auth;

import org.javawebstack.validator.Rule;

public class RegisterRequest {
    @Rule({"required", "string(6, 12)"})
    public String userName;

    @Rule({"required", "email"})
    public String eMail;

    @Rule({"required", "string(6" +
            ")"})
    public String password;
}
