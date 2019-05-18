package app.business.file;

import app.dao.ProjectDAO;
import app.entity.Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static app.ui.Console.scString;


public class ProjectServiceFile implements ProjectDAO {

    List<String> linesProject = new ArrayList<>();

    @Override
    public void viewAllProjects(Project project) {

        try {
            linesProject = Files.readAllLines(Paths.get("project.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("<Projects>");

        try {
            for (int i = 1; i < linesProject.size(); i++) {
                linesProject.get(i);
                System.out.println(i + "." + " " + "<" + linesProject.get(i) + ">");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProject() {
        System.out.println();
        System.out.println("Введите имя project и нажмите <enter> для записи в файл");
        String insertName = scString();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("project.txt", true)))
        {
            String projects = insertName;
            bw.newLine();
            bw.write(projects);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
