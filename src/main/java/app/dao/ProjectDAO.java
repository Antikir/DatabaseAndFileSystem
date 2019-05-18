package app.dao;

import app.entity.Project;

import java.sql.SQLException;

public interface ProjectDAO {
    void viewAllProjects(Project project) throws SQLException;

    void addProject() throws SQLException;
}
