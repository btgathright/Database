import java.sql.*;

public class Query {
    // This object just creates a query string using 2 constructors.
    // Pass an object of this type to a query call on a DBController object to perform a query. 
    // See the commmented main code at the bottom of this class for an example of the DBController and Query class workflow

    private String total_query_string;

    // Basic Query
    public Query(String table, String column, String value) {
        this.total_query_string = 
            String.format("SELECT * FROM public.\"%s\" WHERE \"%s\" = '%s';", table, column, value);
    }

    public Query(String table1, String table2, String column, String value) {
        this.total_query_string = 
            String.format("SELECT * FROM public.\"%s\" INNER JOIN \"%s\" ON (\"%s\".\"%s ID\" = \"%s\".\"ID\" AND \"%s\".\"%s\" = '%s');",
                table1, table2, table1, table2, table2, table2, column, value);
    }

    public String get_query_string() {
        return this.total_query_string;
    }

    // public static void main(String[] args) {
    //     String table1 = "Player";
    //     String table2 = "Team";
    //     String column = "Name";
    //     String value = "Texas A&M";

    //     dbSetup my = new dbSetup("smh3005", "*********");
    //     DBController controller = new DBController(my);
    //     System.out.println("Connection successful");
    //     Query q = new Query(table1, table2, column, value);
    //     String s = q.get_query_string();
    //     System.out.println(s);
        

    //     ResultSet rs = controller.query(q);
    //     if (rs != null) {
    //         try {
    //             while (rs.next()) {
    //                 // try {
    //                 //     System.out.println(rs.getString(i) + ", ");
    //                 // } catch (Exception e) {
    //                 //     System.out.println();
    //                 //     break;
    //                 // }// conf_name += result.getString("Name")+"\n";
                
    //                 System.out.println(rs.getString("First Name") + " " + rs.getString("Last Name"));
    //             }
    //         } catch (Exception e) {
    //             System.out.println("There was an issue parsing the result of the query");
    //         }
    //     }
    // }
    //TODO: Add more constructors to deal with more complex scenarios?
}