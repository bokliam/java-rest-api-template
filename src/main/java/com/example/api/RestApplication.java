package com.example.api;

import com.example.api.dao.UserDAO;
import com.example.api.health.DatabaseHealthCheck;
import com.example.api.resource.GreetingResource;
import com.example.api.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class RestApplication extends Application<RestApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new RestApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RestApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<RestApplicationConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(final RestApplicationConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(RestApplicationConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);

        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new GreetingResource());
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
        environment.healthChecks().register("health",
                new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
    }

}
