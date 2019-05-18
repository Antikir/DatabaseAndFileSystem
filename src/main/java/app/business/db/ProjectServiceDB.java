package app.business.db;

import app.common.DB;
import app.dao.CreateTableDAO;
import app.dao.ProjectDAO;
import app.entity.Project;
import app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import static app.ui.Console.sc;
import static app.ui.Console.scString;

public class ProjectServiceDB extends DB implements ProjectDAO, CreateTableDAO {

    @Override
    public void dbStart() throws SQLException {
        super.dbStart();
    }

    @Override
    public void createTable() throws SQLException {
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Projects " +
                "(user_id INTEGER NOT NULL, " +
                " project_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                " project_name VARCHAR(100)," +
                " FOREIGN KEY (user_id) REFERENCES users(user_id))";
        stmt.executeUpdate(sql);
        System.out.println();
        System.out.println("Таблица Projects создана.");
    }

    @Override
    public void viewAllProjects(Project project) throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT user_id, project_id, project_name FROM Projects";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Вывод таблицы projects:");
        System.out.println("user_id--project_id--project_name");

        try {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int projectId = rs.getInt("project_id");
                String projectName = rs.getString("project_name");

                System.out.print(userId);
                System.out.print("        " + projectId);
                System.out.println("           " + projectName);
            }

        } catch (InputMismatchException a) {
            System.out.println("Вы ввели неверные данные. Попробуйте снова!");
        }
    }

    UserServiceDB userServiceDB = new UserServiceDB();
    User user1 = new User("user1");

    @Override
    public void addProject() throws SQLException {
        System.out.println("Введите имя нового project:");
        String insertProjectName = scString();

        try {
            System.out.println();
            System.out.println("Выберите и введите user_id:");
            System.out.println();
            userServiceDB.dbStart();
            userServiceDB.viewAllUsers(user1);
            int insertUserId = sc();

            String sql = "INSERT INTO Projects (user_id, project_name) values ('" + insertUserId + "', '" + insertProjectName + "')";
            stmt.executeUpdate(sql);
            System.out.println();
            System.out.println("В таблицу Projects внесены новые данные.");

        } catch (InputMismatchException a) {
            System.out.println("Вы ввели неверные данные. Попробуйте снова!");
        }
    }
}
