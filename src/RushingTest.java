import java.util.*;
import java.sql.*;

public class RushingTest {
    public static void main(String args[]) {
        /***** Plan of action:
         * 1. Get the team name
         * 2. Store all the games that team has played in memory
         * 3. Store the rush table entries for each of those games in memory whose team is not 
         *    the team in question
         * 4. Find the max and print the name of the team 
         */
        dbSetup setup = new dbSetup("smh3005", "226005119");
        DBController controller = new DBController(setup);
        long team_id = 697;
        
        // 2. Store all the games this team has played in mem 
        //    (Storing the game id and "opposing team" id in a pair)
        ArrayList<Pair<Long, Long>> games = new ArrayList<Pair<Long, Long>>();
        
        String qString = String.format("SELECT \"ID\", \"HTeam ID\", \"VTeam ID\" FROM public.\"Game\" WHERE \"Game\".\"HTeam ID\" = '%d' OR \"Game\".\"VTeam ID\" = '%d';", team_id, team_id);
        Query q = new Query(qString);
        ResultSet rs = controller.query(q);
        
        try {
            while (rs.next()) {
                Long id = Long.parseLong(rs.getString(1));
                Long hteam_id = Long.parseLong(rs.getString(2));
                Long vteam_id = Long.parseLong(rs.getString(3));
                if (hteam_id == team_id) {
                    // Use vteam_id
                    System.out.println(id + " | " + vteam_id);
                    games.add(new Pair<Long, Long>(id, vteam_id));
                } else {
                    // Use hteam_id
                    System.out.println(id + " | " + hteam_id);
                    games.add(new Pair<Long, Long>(id, hteam_id));
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: \"%s\"", e.toString()));
        }
        
        // 3 & 4 Search the rush entries for the maximum
        long max_yards = 0;
        long max_yards_team_id = 0;
        try {
            for (Pair<Long, Long> p : games) {
                qString = String.format("SELECT \"Team ID\", \"Yards\" FROM public.\"Rush\" WHERE (\"Game ID\" = '%d' AND \"Team ID\" = '%d');", p.first, p.second);
                q = new Query(qString);
                ResultSet temp_rs = controller.query(q);
                while (temp_rs.next()) {                    
                    Long id = Long.parseLong(temp_rs.getString(1));
                    Long yards = Long.parseLong(temp_rs.getString(2));
                    
                    if (yards > max_yards) {
                        max_yards = yards;
                        max_yards_team_id = id;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: \"%s\"", e.toString()));
        }

        String answer = new String();
        try {
            qString = String.format("SELECT \"Name\" FROM public.\"Team\" WHERE \"ID\" = '%d';", max_yards_team_id);
            q = new Query(qString);
            ResultSet temp_rs1 = controller.query(q);
            String opposing_name = new String();
            while(temp_rs1.next()) {
                opposing_name = temp_rs1.getString(1);
            }

            qString = String.format("SELECT \"Name\" FROM public.\"Team\" WHERE \"ID\" = '%d';", team_id);
            q = new Query(qString);
            ResultSet temp_rs2 = controller.query(q);
            String team_name = new String();
            while(temp_rs2.next()) {  
                team_name = temp_rs2.getString(1);
            }
            answer = String.format("%s had the most rushing yards against %s: %d yards", opposing_name, team_name, max_yards);
        
        } catch (Exception e) {
            System.out.println(String.format("Error: \"%s\"", e.toString()));
        }

        System.out.println(answer);
    }
}