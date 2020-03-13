import java.sql.*;
import java.util.*;
import java.util.regex.*;  

public class add_winner {
    public static void main(String args[]) {
        dbSetup setup = new dbSetup("smh3005", "226005119");
        DBController controller = new DBController(setup);

        String custom_q = new String("SELECT \"Game ID\", ARRAY_AGG(\"Team ID\"), ARRAY_AGG(\"Points\") FROM public.\"Team-Game-Statistics\" GROUP BY \"Team-Game-Statistics\".\"Game ID\"");

        Query q = new Query(custom_q);
        ResultSet rs = controller.query(q);


        Pattern pattern = Pattern.compile("(\\d+)");

        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            int n = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= n; i++) {
                    Matcher matcher = pattern.matcher(rs.getString(i));
                    while(matcher.find()) {
                        result.add(Integer.parseInt(matcher.group()));
                        System.out.print(matcher.group() + " | ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("There was an issue writing to file");
            System.out.println(String.format("Error: %s", e.toString()));
        }

        int i = 0;
        // int arr[] = result.toArray();
        while (i < result.size()) {
            int game_id = result.get(i);
            int team1_id = result.get(i+1);
            int team2_id = result.get(i+2);

            int team1_score = result.get(i+3);
            int team2_score = result.get(i+4);

            int winner_id = team1_id;
            if (team2_score > team1_score) {
                winner_id = team2_id;
            }

            String query_str = String.format(
                "UPDATE public.\"Game\" SET \"Winner ID\" = '%d' WHERE \"ID\" = '%d'", winner_id, game_id
            );
            Query q = new Query(query_str);
            controller.query(q);
        }
        
    }//end main

  }//end Class