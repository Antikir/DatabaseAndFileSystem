package app.common;

import java.sql.*;

public class DB {
    public Connection conn = null;
    public Statement stmt = null;

    public void dbStart() throws SQLException {

        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:C:/user.db");
            System.out.println("Cоединения с БД установлено!");

            if (conn == null) {
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {

            }
        }
    }
}
