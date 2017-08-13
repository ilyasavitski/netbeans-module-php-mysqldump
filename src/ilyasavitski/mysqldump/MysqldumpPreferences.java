/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;

/**
 *
 * @author ilyasavitski
 */
public final class MysqldumpPreferences {

    private static final String DATABASE_HOST = "localhost";
    private static final String DATABASE_NAME = "";
    private static final String DATABASE_USER = "user";
    private static final String DATABASE_PASSWORD = "password";

    private MysqldumpPreferences() {
    }

    public static String getDatabaseHost(Project project) {
        return getPreferences(project).get(DATABASE_HOST, null);
    }

    public static void setDatabaseHost(Project project, String databaseHost) {
        getPreferences(project).put(DATABASE_HOST, databaseHost);
    }
    
    public static String getDatabaseName(Project project) {
        return getPreferences(project).get(DATABASE_NAME, null);
    }

    public static void setDatabaseName(Project project, String databaseName) {
        getPreferences(project).put(DATABASE_NAME, databaseName);
    }
    
    public static String getDatabaseUser(Project project) {
        return getPreferences(project).get(DATABASE_USER, null);
    }

    public static void setDatabaseUser(Project project, String databaseUser) {
        getPreferences(project).put(DATABASE_USER, databaseUser);
    }
    
    public static String getDatabasePassword(Project project) {
        return getPreferences(project).get(DATABASE_PASSWORD, null);
    }

    public static void setDatabasePassword(Project project, String databasePassword) {
        getPreferences(project).put(DATABASE_PASSWORD, databasePassword);
    }

    private static void flush(Project project, boolean isShared) throws BackingStoreException {
        getPreferences(project, isShared).flush();
    }

    private static Preferences getPreferences(Project project) {
        return getPreferences(project, true);
    }

    private static Preferences getPreferences(Project project, boolean b) {
        return ProjectUtils.getPreferences(project, MysqldumpPreferences.class, b);
    }

}
