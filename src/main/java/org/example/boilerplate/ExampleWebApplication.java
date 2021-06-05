package org.example.boilerplate;

import org.example.boilerplate.controller.AuthController;
import org.example.boilerplate.model.User;
import org.example.boilerplate.responses.ExceptionResponse;
import org.javawebstack.framework.HttpController;
import org.javawebstack.framework.WebApplication;
import org.javawebstack.framework.config.Config;
import org.javawebstack.httpserver.HTTPServer;
import org.javawebstack.orm.ORM;
import org.javawebstack.orm.ORMConfig;
import org.javawebstack.orm.exception.ORMConfigurationException;
import org.javawebstack.orm.wrapper.SQL;
import org.javawebstack.templating.TemplateEngine;
import org.javawebstack.templating.gsp.GSPEngine;
import org.javawebstack.webutils.IO;

public class ExampleWebApplication extends WebApplication {

    private static ExampleWebApplication instance;
    private GSPEngine templatingEngine = new GSPEngine(name -> IO.readTextResource(getClass().getClassLoader(), "views/"+name));

    protected void setupConfig(Config config) {
        config.addEnvFile(".env");
        config.addEnvFile(".env.local");
    }

    protected void setupModels(SQL sql) throws ORMConfigurationException {
        ORMConfig config = new ORMConfig().setTablePrefix("example_");
        ORM.register(User.class.getPackage(), sql, config);
        ORM.autoMigrate();
    }

    protected void setupServer(HTTPServer httpServer) {
        httpServer.exceptionHandler((exchange, throwable)-> new ExceptionResponse(throwable));

        httpServer.controller(HttpController.class, AuthController.class.getPackage());

        httpServer.staticResourceDirectory("/static", getClass().getClassLoader(), "static");
    }

    public GSPEngine getTemplatingEngine() {
        return templatingEngine;
    }

    public static void main(String[] args) {
        instance = new ExampleWebApplication();
        instance.start();
    }

    public static ExampleWebApplication getInstance() {
        return instance;
    }
}
