package org.example.boilerplate.controller;

import org.example.boilerplate.model.User;
import org.example.boilerplate.requests.auth.LoginRequest;
import org.example.boilerplate.requests.auth.RegisterRequest;
import org.example.boilerplate.responses.auth.LoginResponse;
import org.example.boilerplate.responses.auth.RegisterResponse;
import org.javawebstack.framework.HttpController;
import org.javawebstack.httpserver.router.annotation.Body;
import org.javawebstack.httpserver.router.annotation.Get;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.Post;
import org.javawebstack.injector.Inject;
import org.javawebstack.orm.Repo;
import org.javawebstack.templating.TemplateEngine;
import org.javawebstack.templating.TemplateException;

import java.util.HashMap;

@PathPrefix("/auth")
public class AuthController extends HttpController {

    @Inject
    TemplateEngine templateEngine;

    @Get("/login")
    public String loginPage() throws TemplateException {
        return templateEngine.render("login", new HashMap<>());
    }

    @Post("/login")
    public LoginResponse login(@Body LoginRequest request){
        LoginResponse loginResponse = new LoginResponse();

        User user = Repo.get(User.class).where("name", request.userName).first();

        if (user != null && user.checkPassword(request.password)) {
            loginResponse.success = true;
        }

        return loginResponse;
    }

    @Get("/register")
    public String registerPage() throws TemplateException {
        return templateEngine.render("login", new HashMap<>());
    }

    @Post("/login")
    public RegisterResponse register(@Body RegisterRequest request){
        RegisterResponse response = new RegisterResponse();

        User testUser = Repo.get(User.class).where("name", request.userName).orWhere("eMail", request.eMail).first();

        if (testUser == null) {
            User user = new User();
            user.name = request.userName;
            user.eMail = request.eMail;

            user.setPassword(request.password);

            user.save();

            response.success = true;
        }

        return response;
    }


}
