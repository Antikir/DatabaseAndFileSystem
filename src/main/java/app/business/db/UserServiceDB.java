package app.business.db;

import app.common.DB;
import app.dao.CreateTableDAO;
import app.dao.UserDAO;
import app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import static app.ui.Console.scString;

public class UserServiceDB extends DB implements UserDAO, CreateTableDAO {

    @Override
    public void dbStart() throws SQLException {
        super.dbStart();
    }

    @Override
    public void createTable() throws SQLException {
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Users " +
                "(user_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                " user_name VARCHAR(255))";
        stmt.executeUpdate(sql);
        System.out.println("Таблица Users создана.");
    }

    @Override
    public void viewAllUsers(User user) throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT user_id, user_name FROM Users";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("Вывод таблицы users:");
        System.out.println("id---name");

        try {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                System.out.print(userId);
                System.out.println("   " + userName);
            }
        }catch (InputMismatchException a) {
            System.out.println("Вы ввели неверные данные. Попробуйте снова!");
        }
    }

    @Override
    public void addUser() throws SQLException {
        System.out.println("Введите имя нового user:");
        String insertName = scString();

        stmt = conn.createStatement();
        String sql = "INSERT INTO Users (user_name) values ('" + insertName + "')";
        stmt.executeUpdate(sql);

        System.out.println();
        System.out.println("В таблицу Users внесены новые данные.");
    }
}
