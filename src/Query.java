public class Query {
    // This object just creates a query string using 2 constructors.
    // Pass an object of this type to a query call on a DBController object to perform a query. 
    // See the commmented main code at the bottom of this class for an example of the DBController and Query class workflow

    private String total_query_string;
    private String alternate_query_string;

    public Query(String s) {
        total_query_string = s;
        alternate_query_string = s;
    }

    // Basic Query
    public Query(String table, String column, String value) {
        this.total_query_string = 
            String.format("SELECT * FROM public.\"%s\" WHERE %s = '%s';", table, column, value);
        this.alternate_query_string = 
            String.format("SELECT * FROM public.\"%s\" WHERE %s = '%s';", table, column, value);

        System.out.println(this.total_query_string);
    }

    public Query(String table1, String table2, String column, String value, DBController controller) {
        
        String[] names = DBHelper.get_column_names(controller, table1);
        String names_str = String.join(",", names);

        this.total_query_string = 
            String.format("SELECT %s FROM public.\"%s\" INNER JOIN \"%s\" ON (\"%s\".\"%s ID\" = \"%s\".\"ID\" AND %s = '%s');",
                names_str, table1, table2, table1, table2, table2, column, value);

        this.alternate_query_string = 
            String.format("SELECT %s FROM public.\"%s\" INNER JOIN \"%s\" ON (\"%s\".\"%s ID\" = \"%s\".\"ID\" AND %s = '%s');",
                names_str, table1, table2, table2, table1, table1, column, value);
    }

    public String get_query_string() {
        return this.total_query_string;
    }

    public String get_alternate_query_string() {
        return this.alternate_query_string;
    }

    // Example Code: 
    // public static void main(String[] args) {
        /************Sample Double Query 1*************/
        // String table1 = "Player";
        // String table2 = "Punt-Return";
        // String column = "Yards";
        // String value = "20";
        
        // String table3 = "Player";
        // String table4 = "Punt";
        // String column2 = "Yards";
        // String value2 = "34";

        /************Sample Double Query 2*************/
        // String table1 = "Player";
        // String table2 = "Team";
        // String column = "Name";
        // String value = "Texas A&M";
        
        // String table3 = "Player";
        // String table4 = "Punt";
        // String column2 = "Yards";
        // String value2 = "34";

        /************Sample Double Query 3*************/
        // String table1 = "Team";
        // String table2 = "Player";
        // String column = "First Name";
        // String value = "John";
        
        // String table3 = "Team";
        // String table4 = "Drive";
        // String column2 = "Yards";
        // String value2 = "5";

    //     /************Sample Single Query*************/
    //     String table1 = "Team";
    //     String table2 = "Player";
    //     String column = "First Name";
    //     String value = "John";
        
    //     String table3 = "Team";
    //     String table4 = "Drive";
    //     String column2 = "Yards";
    //     String value2 = "5";
        
    //     dbSetup my = new dbSetup("smh3005", "*********");
    //     DBController controller = new DBController(my);
    //     System.out.println("Connection successful");
        
    //     String[] names = DBHelper.get_table_names(controller);
    //     String names_str = String.join(", ", names);
    //     System.out.println(names_str);
        
    //     Query q1 = new Query(table1, table2, column, value, controller);
    //     Query q2 = new Query(table3, table4, column2, value2, controller);

        
    //     ResultSet rs = controller.query_advanced(q1, q2, "UNION");
    //     System.out.println("Here!");

    //     // Example code of how to write to a file
        // try {
        //     int n = rs.getMetaData().getColumnCount();
        //     StringBuilder sb = new StringBuilder();
            
        //     while (rs.next()) {
        //         for (int i = 1; i <= n; i++) {
        //             sb.append(rs.getString(i));
        //             sb.append(", ");
        //         }
        //         sb.append("\n");
        //     }
        //     FileWriter fw = new FileWriter("./output.csv");
        //     fw.write(sb.toString());
        //     fw.close();

        //     System.out.println(sb.toString());
        // } catch (Exception e) {
        //     System.out.println("There was an issue writing to file");
        //     System.out.println(String.format("Error: %s", e.toString()));
        // }
    // }
    //TODO: Add more constructors to deal with more complex scenarios?
}