package app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import javax.validation.Validation;
import javax.validation.Validator;

import app.controllers.*;

public class Main extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-forum";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        Validator validator =
                Validation.buildDefaultValidatorFactory().getValidator();

        environment.jersey().register(new PagesController());
        environment.jersey().register(new SignupController(validator));
    }
}
