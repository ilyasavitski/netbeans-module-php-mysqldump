/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump.utils;

/**
 *
 * @author ilyasavitski
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * Check whether string is null or empty.
     *
     * @param string
     * @return true if null or empty, false otherwise.
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Check whether string contains all conditions.
     *
     * @param name target string
     * @param filters strings for filtering
     * @return true if target string pass all filters, false otherwise.
     */
    public static boolean containsAll(String name, String[] filters) {
        if (filters == null) {
            return false;
        }
        for (String filter : filters) {
            if (!name.contains(filter)) {
                return false;
            }
        }
        return true;
    }

}
