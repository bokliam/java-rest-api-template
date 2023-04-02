package com.example.api.unit.dao;

import com.example.api.RestApplication;
import com.example.api.RestApplicationConfiguration;
import com.example.api.dao.UserDAO;
import com.example.api.domain.User;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skife.jdbi.v2.DBI;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UserDAOTest {
    private static final String FILE_PATH = ResourceHelpers.resourceFilePath("config.yml");

    public static final DropwizardAppExtension<RestApplicationConfiguration> DROPWIZARD_APP_EXTENSION = new DropwizardAppExtension<>(RestApplication.class, FILE_PATH);
    private DBIFactory factory;
    private DBI dbi;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        factory = new DBIFactory();
        dbi = factory.build(DROPWIZARD_APP_EXTENSION.getEnvironment(), DROPWIZARD_APP_EXTENSION.getConfiguration().getDataSourceFactory(), "mysql");
        userDAO = dbi.onDemand(UserDAO.class);
    }

    @Test
    public void getUserReturnsNullUserWhenInvalidCredentialsProvided() {
        String username = "test";
        String password = "";

        User user = userDAO.getUser(username, password);

        assertThat(user).isNull();
    }

    @Test
    public void getUserReturnsUserWhenValidCredentialsProvided() {
        String username = "admin";
        String password = "admin";

        User user = userDAO.getUser(username, password);

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isNull();
    }
}
