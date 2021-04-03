package org.example.boilerplate;

import org.example.boilerplate.controller.AuthController;
import org.example.boilerplate.model.User;
import org.example.boilerplate.responses.ExceptionResponse;
import org.javawebstack.command.CommandSystem;
import org.javawebstack.framework.HttpController;
import org.javawebstack.framework.WebApplication;
import org.javawebstack.framework.config.Config;
import org.javawebstack.framework.util.IO;
import org.javawebstack.httpserver.HTTPServer;
import org.javawebstack.httpserver.util.ResourceFileProvider;
import org.javawebstack.injector.Injector;
import org.javawebstack.orm.ORM;
import org.javawebstack.orm.ORMConfig;
import org.javawebstack.orm.exception.ORMConfigurationException;
import org.javawebstack.orm.wrapper.SQL;
import org.javawebstack.templating.TemplateEngine;
import org.javawebstack.templating.gsp.GSPEngine;

public class ExampleWebApplication extends WebApplication {
    protected void setupConfig(Config config) {
        config.addEnvFile(".env");
        config.addEnvFile(".env.local");
    }

    protected void setupInjection(Injector injector) {
        injector.setInstance(TemplateEngine.class, new GSPEngine(name -> IO.readTextResource(getClass().getClassLoader(), "views/"+name)));
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

    protected void setupCommands(CommandSystem commandSystem) {
    }

    public static void main(String[] args) {
        new ExampleWebApplication().run(args);
    }
}
