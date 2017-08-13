/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
//import org.netbeans.api.project.ProjectUtils;
//import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
//import org.openide.filesystems.FileUtil;
//import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
//import org.netbeans.api.project.ui.OpenProjects;
//import org.openide.util.Lookup;
//import org.openide.util.Utilities;
import ilyasavitski.mysqldump.MysqldumpPreferences;

@ActionID(
        category = "Project",
        id = "ilyasavitski.mysqldump.MysqldumpActionListener"
)
@ActionRegistration(
        displayName = "#CTL_MysqldumpActionListener"
)
@ActionReference(path = "Projects/Actions", position = 0)
@Messages("CTL_MysqldumpActionListener=Backup Database")
public final class MysqldumpActionListener implements ActionListener {

    private final Project project;
    private InputOutput io = IOProvider.getDefault().getIO("Backup Database", true);

    public MysqldumpActionListener(Project context) {
        this.project = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Project project;
        String dumpCommand,
                projectPath,
                databaseHost,
                databaseName,
                databaseUser,
                databasePassword;

        io.select();

        project = this.project;
        projectPath = getProjectDirectory(project);

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
            io.getOut().println("Database password: " + databasePassword);

            dumpCommand = "mysqldump -h " + databaseHost + " -u " + databaseUser + " -p" + databasePassword + " -c --add-drop-table --add-locks --quick --verbose --lock-tables " + databaseName + " -r " + projectPath + "/" + databaseName + ".dev.sql";

            try {
                Process proc = Runtime.getRuntime().exec(dumpCommand);

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    io.getOut().println(s);
                }

                while ((s = stdError.readLine()) != null) {
                    io.getErr().println(s);
                }

                io.getOut().println("Database " + databaseName + " was successfully dumped to " + projectPath);

            } catch (IOException ex) {

                io.getErr().println(ex.getMessage());
                ex.printStackTrace();
            }
        }

        io.getOut().close();
        io.getErr().close();
    }

    private String getProjectDirectory(final Project project) {

        try {

            FileObject projectDirectory;
            String projectPath;

            projectDirectory = (FileObject) project.getProjectDirectory();
            projectPath = projectDirectory.getPath();

            return projectPath;

        } catch (Exception e) {

            io.getErr().println(e.getMessage());

            return null;
        }

    }
}
