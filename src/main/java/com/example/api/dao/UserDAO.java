package com.example.api.dao;

import com.example.api.dao.mapper.UserMapper;
import com.example.api.domain.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

@UseStringTemplate3StatementLocator
@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("SELECT id, username FROM users WHERE username = :username AND password = :password")
    User getUser(@Bind("username") String username, @Bind("password") String password);

    @SqlUpdate("INSERT INTO users (username, password) VALUES (:username, :password)")
    void createUser(@Bind("username") String username, @Bind("password") String password);
}
