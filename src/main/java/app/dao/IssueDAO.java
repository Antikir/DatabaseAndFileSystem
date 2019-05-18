package app.dao;

import app.entity.Issue;

import java.sql.SQLException;

public interface IssueDAO {
    void viewAllIssues(Issue issue) throws SQLException;

    void addIssue() throws SQLException;
}
