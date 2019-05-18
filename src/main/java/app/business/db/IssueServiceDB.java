package app.business.db;

import app.common.DB;
import app.dao.CreateTableDAO;
import app.dao.IssueDAO;
import app.entity.Issue;
import app.entity.Project;
import app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import static app.ui.Console.sc;
import static app.ui.Console.scString;

public class IssueServiceDB extends DB implements IssueDAO, CreateTableDAO {

    @Override
    public void dbStart() throws SQLException {
        super.dbStart();
    }

    @Override
    public void createTable() throws SQLException {
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Issues " +
                "(user_id INTEGER NOT NULL, " +
                " project_id INTEGER NOT NULL, " +
                " issue_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                " issue_name VARCHAR(100)," +
                " FOREIGN KEY (user_id) REFERENCES users(user_id)," +
                " FOREIGN KEY (project_id) REFERENCES projects(project_id))";
        stmt.executeUpdate(sql);
        System.out.println();
        System.out.println("Таблица Issues создана.");
    }

    @Override
    public void viewAllIssues(Issue issue) throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT user_id, project_id, issue_id,issue_name FROM Issues";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Вывод таблицы issues:");
        System.out.println("user_id--project_id--issue_id--issue_name");

        while (rs.next()) {
            int userId = rs.getInt("user_id");
            int projectId = rs.getInt("project_id");
            int issueId = rs.getInt("issue_id");
            String issueName = rs.getString("issue_name");

            System.out.print(userId);
            System.out.print("        " + projectId);
            System.out.print("           " + issueId);
            System.out.println("         " + issueName);
        }
    }

    UserServiceDB userServiceDB = new UserServiceDB();
    User user1 = new User("user1");
    ProjectServiceDB projectServiceDB = new ProjectServiceDB();
    Project project1 = new Project("project1");

    @Override
    public void addIssue() throws SQLException {
        System.out.println("Введите имя нового issue:");
        String insertIssueName = scString();

        try {
            System.out.println();
            System.out.println("Выберите и введите user_id:");
            System.out.println();
            userServiceDB.dbStart();
            userServiceDB.viewAllUsers(user1);
            int insertUserId = sc();

            System.out.println();
            System.out.println("Выберите и введите project_id:");
            System.out.println();
            projectServiceDB.dbStart();
            projectServiceDB.viewAllProjects(project1);
            int insertProjectId = sc();

            String sql = "INSERT INTO Issues (user_id, project_id, issue_name) values ('" + insertUserId + "', '" + insertProjectId + "','" + insertIssueName + "')";
            stmt.executeUpdate(sql);
            System.out.println();
            System.out.println("В таблицу Issues внесены новые данные.");

        } catch (InputMismatchException a) {
            System.out.println("Вы ввели неверные данные. Попробуйте снова!");
        }
    }
}
