package app.business.file;

import app.dao.UserDAO;
import app.entity.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static app.ui.Console.scString;


public class UserServiceFile implements UserDAO {

    List<String> linesUser = new ArrayList<>();

    @Override
    public void viewAllUsers(User user) {

        try {
            linesUser = Files.readAllLines(Paths.get("user.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("<Users>");
        try {
            for (int i = 1; i < linesUser.size(); i++) {
                linesUser.get(i);
                System.out.println(i + "." + " " + "<" + linesUser.get(i) + ">");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser() {
        System.out.println("Введите имя user и нажмите <enter> для записи в файл");
        String insertName = scString();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("user.txt", true)))
        {
            String users = insertName;
            bw.newLine();
            bw.write(users);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
