/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump;

import ilyasavitski.mysqldump.utils.ProjectUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

@ActionID(
        category = "Project",
        id = "ilyasavitski.mysqldump.MysqldumpRestoreActionListener"
)
@ActionRegistration(
        displayName = "#CTL_MysqldumpRestoreActionListener"
)
@ActionReference(path = "Projects/Actions", position = 0)
@Messages("CTL_MysqldumpRestoreActionListener=Restore Database")
public final class MysqldumpRestoreActionListener implements ActionListener {

    private final Project project;
    private InputOutput io = IOProvider.getDefault().getIO("Database", false);

    public MysqldumpRestoreActionListener(Project project) {
        this.project = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Project project;
        String projectPath,
                databaseHost,
                databaseName,
                databaseUser,
                databasePassword;

        io.select();

        project = this.project;
        projectPath = ProjectUtils.getProjectDirectory(project, io);

        databaseHost = MysqldumpPreferences.getDatabaseHost(project);
        databaseName = MysqldumpPreferences.getDatabaseName(project);
        databaseUser = MysqldumpPreferences.getDatabaseUser(project);
        databasePassword = MysqldumpPreferences.getDatabasePassword(project);

        if (databaseHost == null || databaseHost.isEmpty()) {
            io.getErr().println("Error: database host must be set in the project properties!");
        }

        if (databaseName == null || databaseName.isEmpty()) {
            io.getErr().println("Error: database name must be set in the project properties!");
        }

        if (databaseUser == null || databaseUser.isEmpty()) {
            io.getErr().println("Error: database user must be set in the project properties!");
        }

        if (databaseHost != null && !databaseHost.isEmpty() && databaseName != null && !databaseName.isEmpty() && databaseUser != null && !databaseUser.isEmpty()) {

            io.getOut().println("Database host: " + databaseHost);
            io.getOut().println("Database name: " + databaseName);
            io.getOut().println("Database user: " + databaseUser);

            ProcessBuilder processBuilder;
            File backupFile = new File(projectPath + File.separator + databaseName + ".dev.sql");

            try {

                processBuilder = new ProcessBuilder(
                        "mysql",
                        "--host=" + databaseHost,
                        "--user=" + databaseUser,
                        "--password=" + databasePassword,
                        databaseName
                );

                processBuilder.redirectError(Redirect.INHERIT);
                processBuilder.redirectInput(Redirect.from(backupFile));
                Process process = processBuilder.start();

                BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s = null;
                while ((s = output.readLine()) != null) {
                    io.getOut().println(s);
                }

                while ((s = errors.readLine()) != null) {
                    io.getErr().println(s);
                }

                process.waitFor();

                io.getOut().println("Database " + databaseName + " was successfully restored from " + projectPath);

            } catch (IOException ex) {

                io.getErr().println(ex.getMessage());
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        io.getOut().close();
        io.getErr().close();
    }

}
