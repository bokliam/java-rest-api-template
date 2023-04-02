package com.example.api.dao.mapper;

import com.example.api.domain.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {
    public User map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new User( r.getInt("id"),
                r.getString("username"),
                null
        );
    }
}
