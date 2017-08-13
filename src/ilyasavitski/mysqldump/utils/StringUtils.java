/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ilyasavitski.mysqldump.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.netbeans.api.annotations.common.NonNull;

/**
 *
 * @author s4va
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
     * Implode list.
     *
     * @param list list
     * @param delimiter delimiter
     * @return imploded string with delimiter
     */
    public static String implode(@NonNull List<String> list, @NonNull String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String string : list) {
            if (!isFirst) {
                sb.append(delimiter);
            }
            sb.append(string);
            isFirst = false;
        }
        return sb.toString();
    }

    /**
     * Explode string with delimiter.
     *
     * @param target string
     * @param delimiter delimiter
     * @return exploded list with delimiter
     */
    public static List<String> explode(@NonNull String target, String delimiter) {
        if (target.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<String>(Arrays.asList(target.split(delimiter)));
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
