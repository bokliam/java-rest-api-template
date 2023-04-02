package com.example.api.unit.domain;

import com.example.api.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    public void constructorCreatesUser() {
        Integer id = 123;
        String username = "testUsername";
        String password = "testPassword";
        User user = new User(id, username, password);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void constructorCreatesUserWhenIdIsNull() {
        Integer id = null;
        String username = "testUsername";
        String password = "testPassword";
        User user = new User(id, username, password);

        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
