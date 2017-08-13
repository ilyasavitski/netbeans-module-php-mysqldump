/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump.utils;

import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.windows.InputOutput;

/**
 *
 * @author ilyasavitski
 */
public class ProjectUtils {

    private ProjectUtils() {
    }

    /**
     * Get current project path.
     * 
     * @param project
     * @param io
     * @return 
     */
    public static String getProjectDirectory(Project project, InputOutput io) {

        try {

            FileObject projectDirectory;
            String projectPath;

            projectDirectory = project.getProjectDirectory();
            projectPath = projectDirectory.getPath();

            return projectPath;

        } catch (Exception e) {

            io.getErr().println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
