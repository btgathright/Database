import java.sql.*;
import javax.swing.*;
// import java.awt.GridLayout;
// import java.awt.Dimension;


public class DBController {

    public Connection conn = null;

    public DBController(dbSetup my) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/team14_cfb",
                my.user, my.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": " + e.getMessage());
            System.exit(0);
        }
        // If you get to this point, you've successfully connected...
    }

    public ResultSet query(Query q) {
        String s = q.get_query_string();
        System.out.println(String.format("Query: %s", s));
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet result = stmt.executeQuery(s);
            return result;
        } catch (Exception e) {
            try {
                s = q.get_alternate_query_string();
                System.out.println(String.format("Alternate Query: %s", s));
                Statement stmt = this.conn.createStatement();
                ResultSet result = stmt.executeQuery(s);
                return result;
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null,"Query failed (Check your column name).");
                System.out.println(String.format("Error: %s", e2.toString()));
            }
        }
        // protocol is to return null. API users should check for this 
        // before attempting to use results
        return null;
    }

    public ResultSet query_advanced(Query q1, Query q2, String type) {
        String q1_str = q1.get_query_string();
        String q2_str = q2.get_query_string();
        String total = q1_str.substring(0, q1_str.length()-1) + " " + type + " " + q2_str;
        System.out.println(String.format("Query Attempt 1: %s", total));
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet result = stmt.executeQuery(total);
            return result;
        } catch (Exception e) {
            try {
                q1_str = q1.get_alternate_query_string();
                q2_str = q2.get_query_string();
                total = q1_str.substring(0, q1_str.length()-1) + " " + type + " " + q2_str;
                System.out.println(String.format("Query Attempt 2: %s", total));
                Statement stmt = this.conn.createStatement();
                ResultSet result = stmt.executeQuery(total);
                return result;
            } catch (Exception e2) {
                try {
                    q1_str = q1.get_query_string();
                    q2_str = q2.get_alternate_query_string();
                    total = q1_str.substring(0, q1_str.length()-1) + " " + type + " " + q2_str;
                    System.out.println(String.format("Query Attempt 3: %s", total));
                    Statement stmt = this.conn.createStatement();
                    ResultSet result = stmt.executeQuery(total);
                    return result;
                } catch (Exception e3) {
                    try {
                        q1_str = q1.get_alternate_query_string();
                        q2_str = q2.get_alternate_query_string();
                        total = q1_str.substring(0, q1_str.length()-1) + " " + type + " " + q2_str;
                        System.out.println(String.format("Query Attempt 4: %s", total));
                        Statement stmt = this.conn.createStatement();
                        ResultSet result = stmt.executeQuery(total);
                        return result;
                    } catch (Exception e4) {
                        JOptionPane.showMessageDialog(null,"Query failed (Check your column name).");
                    }
                }
            }
        }
        // protocol is to return null. API users should check for this 
        // before attempting to use results
        return null;
    }
}