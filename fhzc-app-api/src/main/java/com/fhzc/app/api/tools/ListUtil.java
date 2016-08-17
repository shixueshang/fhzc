package com.fhzc.app.api.tools;

import java.util.List;

/**
 * Created by freeman on 16/8/17.
 */
public class ListUtil {

    public static String listToString(List list, String separator) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }

}
