/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
3/23/2020
 */

import java.util.*;
import java.sql.*;

class DBHelper {
    public static String[] get_column_names(DBController controller, String table) {
        String column_name_query = String.format("SELECT \"column_name\" FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'%s'", table);
        Query q = new Query(column_name_query);

        ResultSet column_names_set = controller.query(q);
        List<String> names = new ArrayList<String>();
        try {
            while (column_names_set.next()) {
                names.add(String.format("\"%s\".\"%s\"", table, column_names_set.getString("column_name")));
            }
        } catch (Exception e) {
            // Honestly an error should be thrown here. 
            names.add("*");
        }
        return names.stream().toArray(String[]::new);
    }
    public static String[] get_table_names(DBController controller) {
        String[] res = {"Conference", "Drive", "Game", "Game-Stats", "Kickoff", "Kickoff-Return", "Pass", "Play", 
            "Player", "Player-Game-Statistics", "Punt", "Punt-Return", "Recpetion", "Rush", "Stadium", 
            "Team", "Team-Game-Statistics"};
        
        return res;
    }
}