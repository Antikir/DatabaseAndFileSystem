package app.dao;

import app.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    void viewAllUsers(User user) throws SQLException;

    void addUser() throws SQLException;
}
