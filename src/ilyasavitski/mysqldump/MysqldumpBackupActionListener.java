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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;


@ActionID(
        category = "Project",
        id = "ilyasavitski.mysqldump.MysqldumpBackupActionListener"
)
@ActionRegistration(
        displayName = "#CTL_MysqldumpBackupActionListener"
)
@ActionReference(path = "Projects/Actions", position = 0)
@Messages("CTL_MysqldumpBackupActionListener=Backup Database")
public final class MysqldumpBackupActionListener implements ActionListener {

    private final Project project;
    private InputOutput io = IOProvider.getDefault().getIO("Database", false);

    public MysqldumpBackupActionListener(Project project) {
        this.project = project;
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

            dumpCommand = "mysqldump --host=" + databaseHost + " --user=" + databaseUser + " --password=" + databasePassword + " -c --add-drop-table --add-locks --quick --lock-tables " + databaseName + " --result-file=" + projectPath + File.separator + databaseName + ".dev.sql";

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

}
