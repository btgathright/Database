import java.util.*;
import java.sql.*;

public class bragging {
    public static void main(String args[]) {
        dbSetup setup = new dbSetup("smh3005", "226005119");
        DBController controller = new DBController(setup);

        // BUILDING GRAPH IN MEMORY
        HashMap<Integer, Node> games = new HashMap<Integer, Node>();
        HashMap<Integer, Node> teams = new HashMap<Integer, Node>();

        String all_games_query_string = "SELECT \"ID\", \"VTeam ID\", \"HTeam ID\", \"Winner ID\" FROM public.\"Game\"";
        Query temp_q = new Query(all_games_query_string);
        ResultSet rs = controller.query(temp_q);

        try {
            while (rs.next()) {
                int game_id     = Integer.parseInt(rs.getString(1));
                int vteam_id    = Integer.parseInt(rs.getString(2));
                int hteam_id    = Integer.parseInt(rs.getString(3));
                int winner_id   = Integer.parseInt(rs.getString(4));

                HashSet<Integer> team_arr = new HashSet<Integer>();
                team_arr.add(vteam_id);
                team_arr.add(hteam_id);

                Node g_node = new GameNode(game_id, team_arr, winner_id);
                if (!games.containsKey(game_id)) {
                    games.put(game_id, g_node);
                }

                if (teams.containsKey(hteam_id)) {
                    HashSet<Integer> l = teams.get(hteam_id).get_connection_ids();
                    l.add(game_id);
                } else {
                    HashSet<Integer> l = new HashSet<Integer>();
                    l.add(game_id);
                    Node tn = new TeamNode(hteam_id, l);
                    teams.put(hteam_id, tn);
                }

                if (teams.containsKey(vteam_id)) {
                    HashSet<Integer> l = teams.get(vteam_id).get_connection_ids();
                    l.add(game_id);
                } else {
                    HashSet<Integer> l = new HashSet<Integer>();
                    l.add(game_id);
                    Node tn = new TeamNode(vteam_id, l);
                    teams.put(vteam_id, tn);
                }
            }
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.toString()));
        }
        // END - BUILDING GRAPH IN MEMORY
        
        // TODO: Need to figure a way to get this programmatically..
        int team1_id = 5;
        int team2_id = 10;


        /***** Explanation ******
         * Simple BFS implementation. The twist is that I alternate between Game Nodes and 
         * Team Nodes (simple ids). With the entire graph stored in the two hash maps (teams and 
         * games), I use the ids to go to their respective connection list in the hash map. 
         * 
         * For a given team_id, you can access a Team Node which contains a hash map of game ids 
         * for all the games that team has played in. 
         * 
         * For a given game_id, you can access a game node, which contains a private
         * HashSet of the 2 teams that played in that game as well as a reference to the winner. 
         * We add the winner to the fringe if searching from the second team up, and the loser if 
         * searching from the first team down. 
         * 
         * Reason for using a custom Pair: Need to keep track of the different type of node you're pulling 
         * from the queue as there is a different procedure for teams than there is for games.
        */

        HashSet<Node> visited_nodes = new HashSet<Node>();
        Queue<Pair<Node, Boolean>> to_visit = new LinkedList<Pair<Node, Boolean>>();
        // 0 represents team nodes. 1 represents game nodes
        Node team1_node = teams.get(team1_id);
        to_visit.add(new Pair<Node, Boolean>(team1_node, false));
        while (to_visit.size() > 0) {
            Pair<Node, Boolean> p = to_visit.poll();
            if (p.second == false) { // Team node 

                if (p.first.get_id() == team2_id) {
                    System.out.println("FOUND THE TEAM!");
                    break;
                }

                visited_nodes.add(p.first);

                // Go add the related games to the search fringe
                HashSet<Integer> game_ids = teams.get(p.first.get_id()).get_connection_ids();
                for (Integer id : game_ids) {
                    Node g = games.get(id);
                    if (!visited_nodes.contains(g)) {
                        g.parent = p.first;
                        Pair<Node, Boolean> temp = new Pair<Node, Boolean>(g, true);
                        to_visit.add(temp);
                    }
                } 
            } else {
                // Go add the related games to the search fringe
                GameNode game = (GameNode)p.first;
                game.parent = p.first;
                if (!visited_nodes.contains(game)) {

                    // Very very dumb way to get the other id than the one stored as the winner... 
                    Integer loser = game.get_winner_id();
                    for (Integer team_id : game.get_connection_ids()) {
                        if (team_id != loser) {
                            loser = team_id;
                            break;
                        }
                    }

                    Pair<Node, Boolean> temp = new Pair<Node, Boolean>(teams.get(loser), false);
                    to_visit.add(temp); 
                }
            }
        }

        // Follow the new chain of parents 
        System.out.println("Path Found: Finishing generating your bragging rights...");
        Node losing_team = teams.get(team2_id);
        Stack<String> s = new Stack<String>();
        while(true) {
            Node gn = losing_team.parent;
            Node winning_team = gn.parent;

            String qString = String.format("SELECT \"Name\" FROM public.\"Team\" WHERE \"Team\".\"ID\" = '%d';", losing_team.get_id());
            Query q = new Query(qString);
            ResultSet res = controller.query(q);
            String losing_name;
            while (res.next()) {
                // Loop is entirely unnecessary... 
                losing_name = res.getString(1);
            }

            qString = String.format("SELECT \"Name\" FROM public.\"Team\" WHERE \"Team\".\"ID\" = '%d';", winning_team.get_id());
            Query q = new Query(qString);
            res = controller.query(q);
            String winning_name;
            while (rs.next()) {
                // Loop is entirely unnecessary... 
                winning_name = rs.getString(1);
            }

            qString = String.format("SELECT \"Date\" FROM public.\"Game\" WHERE \"Game\".\"ID\" = '%d';", gn.get_id());
            Query q = new Query(qString);
            res = controller.query(q);
            String game_date;
            while (rs.next()) {
                // Loop is entirely unnecessary... 
                winning_name = rs.getString(1);
            }

            s.push(String.format("%s beat %s on %s\n", winning_name, losing_name, game_date));
            if (winning_team.get_id() == team1_id) {
                break;
            }
            losing_team = winning_team;
        }

        StringBuilder sb = new StringBuilder();
        while (s.size() > 0) {
            sb.append(s.pop());
        }
        // Output of function will output sb.toString()
        System.out.println(sb.toString());
    }
}