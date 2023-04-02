package com.example.api.unit.resource;

import com.example.api.RestApplication;
import com.example.api.RestApplicationConfiguration;
import com.fasterxml.jackson.databind.JsonNode;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UserResourceTest {
    private static final String FILE_PATH = ResourceHelpers.resourceFilePath("config.yml");

    public static final DropwizardAppExtension<RestApplicationConfiguration> DROPWIZARD_APP_EXTENSION = new DropwizardAppExtension<>(RestApplication.class, FILE_PATH);
    private final String GET = "http://localhost:%d/user";
    private DBIFactory factory;
    private DBI dbi;
    private Client client;

    @BeforeEach
    public void setUp() {
        factory = new DBIFactory();
        dbi = factory.build(DROPWIZARD_APP_EXTENSION.getEnvironment(), DROPWIZARD_APP_EXTENSION.getConfiguration().getDataSourceFactory(), "mysql");
        client = DROPWIZARD_APP_EXTENSION.client();
    }

    @Test
    public void getUserReturnsNotFoundWhenInvalidCredentialsProvided() {
        String username = "test";
        String password = "";

        Response response = client.target(getRoute(GET))
                .queryParam("username", username)
                .queryParam("password", password)
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void getUserReturnsUserWhenValidCredentialsProvided() {
        String username = "admin";
        String password = "admin";

        Response response = client.target(getRoute(GET))
                .queryParam("username", username)
                .queryParam("password", password)
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        JsonNode responseEntity = response.readEntity(JsonNode.class);
        assertThat(responseEntity.get("username").asText()).isEqualTo("admin");
        assertThat(responseEntity.get("id").asText()).isEqualTo("1");
    }

    private String getRoute(String path) { return String.format(path, DROPWIZARD_APP_EXTENSION.getLocalPort()); }
}
