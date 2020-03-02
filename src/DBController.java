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
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet result = stmt.executeQuery(s);
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Query failed (Check your column name).");
        }
        // protocol is to return null. API users should check for this 
        // before attempting to use results
        return null;
    }
}