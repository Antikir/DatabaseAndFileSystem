package app;

import app.business.db.IssueServiceDB;
import app.business.db.ProjectServiceDB;
import app.business.db.UserServiceDB;
import app.business.file.IssueServiceFile;
import app.business.file.ProjectServiceFile;
import app.entity.Issue;
import app.entity.Project;
import app.entity.User;
import app.business.file.UserServiceFile;

import java.sql.SQLException;
import java.util.InputMismatchException;

import static app.ui.Console.sc;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceFile userServiceFile = new UserServiceFile();
        ProjectServiceFile projectServiceFile = new ProjectServiceFile();
        IssueServiceFile issueServiceFile = new IssueServiceFile();

        System.out.println("Нажмите: \n 1 - Вывести все сущности (файловая система), " +
                "\n 2 - Добавить сущность (файловая система)," +
                "\n 3 - Работа с БД.");
        try {
            User user1 = new User("user1");
            Project project1 = new Project("project1");
            Issue issue1 = new Issue("issue1");

            int menu = sc();
            switch (menu) {
                case 1:
                    System.out.println();
                    System.out.println("Работа с файловой системой:");
                    userServiceFile.viewAllUsers(user1);
                    projectServiceFile.viewAllProjects(project1);
                    issueServiceFile.viewAllIssues(issue1);

                    break;

                case 2:
                    System.out.println("Работа с файловой системой:");
                    userServiceFile.addUser();
                    projectServiceFile.addProject();
                    issueServiceFile.addIssue();

                    break;

                case 3:
                    System.out.println();
                    UserServiceDB userServiceDB = new UserServiceDB();
                    ProjectServiceDB projectServiceDB = new ProjectServiceDB();
                    IssueServiceDB issueServiceDB = new IssueServiceDB();

                    userServiceDB.dbStart();
                    userServiceDB.createTable();
                    userServiceDB.addUser();
                    userServiceDB.viewAllUsers(user1);

                    projectServiceDB.dbStart();
                    projectServiceDB.createTable();
                    projectServiceDB.addProject();
                    projectServiceDB.viewAllProjects(project1);

                    issueServiceDB.dbStart();
                    issueServiceDB.createTable();
                    issueServiceDB.addIssue();
                    issueServiceDB.viewAllIssues(issue1);

                    break;
                }

            } catch (
                InputMismatchException a) {
                System.out.println("Вы ввели неверные данные. Попробуйте снова!");
                }

    }
}
