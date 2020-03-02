import java.sql.*;
import java.util.*; 
import java.io.*;

public class Query {
    // This object just creates a query string using 2 constructors.
    // Pass an object of this type to a query call on a DBController object to perform a query. 
    // See the commmented main code at the bottom of this class for an example of the DBController and Query class workflow

    private String total_query_string;
    private String alternate_query_string;

    public Query(String s) {
        total_query_string = s;
    }

    // Basic Query
    public Query(String table, String column, String value) {
        this.total_query_string = 
            String.format("SELECT * FROM public.\"%s\" WHERE \"%s\" = '%s';", table, column, value);
        this.alternate_query_string = 
            String.format("SELECT * FROM public.\"%s\" WHERE \"%s\" = '%s';", table, column, value);
    }

    public Query(String table1, String table2, String column, String value, DBController controller) {
        String column_name_query = String.format("SELECT \"column_name\" FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'%s'", table1);
        Query q = new Query(column_name_query);

        ResultSet column_names_set = controller.query(q);

        List<String> names = new ArrayList<String>();
        try {
            while (column_names_set.next()) {
                names.add(String.format("\"%s\".\"%s\"", table1, column_names_set.getString("column_name")));
            }
        } catch (Exception e) {
            // Honestly an error should be thrown here. 
            names.add("*,");
        }
        String names_str = String.join(",", names);

        this.total_query_string = 
            String.format("SELECT %s FROM public.\"%s\" INNER JOIN \"%s\" ON (\"%s\".\"%s ID\" = \"%s\".\"ID\" AND \"%s\".\"%s\" = '%s');",
                names_str, table1, table2, table1, table2, table2, table2, column, value);

        this.alternate_query_string = 
            String.format("SELECT %s FROM public.\"%s\" INNER JOIN \"%s\" ON (\"%s\".\"%s ID\" = \"%s\".\"ID\" AND \"%s\".\"%s\" = '%s');",
                names_str, table1, table2, table2, table1, table1, table2, column, value);
    }

    public String get_query_string() {
        return this.total_query_string;
    }

    public String get_alternate_query_string() {
        return this.alternate_query_string;
    }

    // Example Code: 
    public static void main(String[] args) {
        String table1 = "Team";
        String table2 = "Player";
        String column = "Last Name";
        String value = "Hamilton";
        
        String table3 = "Team";
        String table4 = "Conference";
        String column2 = "Name";
        String value2 = "Southeastern Conference";
        
        dbSetup my = new dbSetup("smh3005", "226005119");
        DBController controller = new DBController(my);
        System.out.println("Connection successful");

        Query q1 = new Query(table1, table2, column, value, controller);
        Query q2 = new Query(table3, table4, column2, value2, controller);

        ResultSet rs = controller.query_intersection(q1, q2);

        // Example code of how to write to a file
        try {
            int n = rs.getMetaData().getColumnCount();
            StringBuilder sb = new StringBuilder();
            
            while (rs.next()) {
                for (int i = 1; i <= n; i++) {
                    sb.append(rs.getString(i));
                    sb.append(", ");
                }
                sb.append("\n");
            }
            FileWriter fw = new FileWriter("./output.csv");
            fw.write(sb.toString());
            fw.close();

            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println("There was an issue writing to file");
            System.out.println(String.format("Error: %s", e.toString()));
        }
    }
    //TODO: Add more constructors to deal with more complex scenarios?
}