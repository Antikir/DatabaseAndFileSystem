package app.business.file;

import app.dao.IssueDAO;
import app.entity.Issue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static app.ui.Console.scString;

public class IssueServiceFile implements IssueDAO {

    List<String> linesIssue = new ArrayList<>();

    @Override
    public void viewAllIssues(Issue issue) {

        try {
            linesIssue = Files.readAllLines(Paths.get("issue.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("<Issues>");
        try {
            for (int i = 1; i < linesIssue.size(); i++) {
                linesIssue.get(i);
                System.out.println(i + "." + " " + "<" + linesIssue.get(i) + ">");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addIssue() {
        System.out.println();
        System.out.println("Введите имя issue и нажмите <enter> для записи в файл");
        String insertName = scString();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("issue.txt", true)))
        {
            String issues = insertName;
            bw.newLine();
            bw.write(issues);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
