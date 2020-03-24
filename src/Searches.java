/*
Brandon Gathright
Seth Hamilton
Samiha Elahi
Johnny Hernandez
CSCE 315
3/23/2020
*/

import java.sql.*;
import java.util.*;

public class Searches 
{
    public static String simpleSearch(DBController controller, String team_name)
    {
        /***** Plan of action:
         * 1. Get the team name
         * 2. Store all the games that team has played in memory
         * 3. Store the rush table entries for each of those games in memory whose team is not 
         *    the team in question
         * 4. Find the max and print the name of the team 
         */
        
        String qString = String.format("SELECT \"ID\" FROM public.\"Team\" WHERE \"Name\" = '%s';", team_name);
        Query q = new Query(qString);
        ResultSet temp_rs1 = controller.query(q);
        long team_id = -1;
        while(temp_rs1.next()) {
            team_id = Long.parseLong(temp_rs1.getString(1));
        }

        if (team_id == -1) {
            return "Team Name couldn't be found in the database. Please check you spelling/capitalization";
        }
        
        // 2. Store all the games this team has played in mem 
        //    (Storing the game id and "opposing team" id in a pair)
        ArrayList<Pair<Long, Long>> games = new ArrayList<Pair<Long, Long>>();
        
        qString = String.format("SELECT \"ID\", \"HTeam ID\", \"VTeam ID\" FROM public.\"Game\" WHERE \"Game\".\"HTeam ID\" = '%d' OR \"Game\".\"VTeam ID\" = '%d';", team_id, team_id);
        q = new Query(qString);
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
            team_name = new String();
            while(temp_rs2.next()) {  
                team_name = temp_rs2.getString(1);
            }
            answer = String.format("%s had the most rushing yards against %s: %d yards", opposing_name, team_name, max_yards);
        
        } catch (Exception e) {
            System.out.println(String.format("Error: \"%s\"", e.toString()));
        }

        return answer;
    }

    public static String chainSearchTeam(DBController controller, String value1, String value2)
    {
        // BUILDING GRAPH IN MEMORY
        HashMap<Long, ArrayList<Pair<Long, String>>> team_to_games = new HashMap<Long, ArrayList<Pair<Long, String>>>();
        HashMap<Long, Long> game_to_winner = new HashMap<Long, Long>();
        HashMap<Long, Pair<Long, Long>> game_to_teams = new HashMap<Long, Pair<Long, Long>>();
        HashMap<Long, String> team_names = new HashMap<Long, String>();
        HashMap<Long, String> game_dates = new HashMap<Long, String>();
        
        String qString = String.format("SELECT * FROM public.\"Game\" INNER JOIN public.\"Team\" ON (\"Game\".\"HTeam ID\" = \"Team\".\"ID\" OR \"Game\".\"VTeam ID\" = \"Team\".\"ID\");");
        Query q = new Query(qString);
        ResultSet res = controller.query(q);
        try {
            while(res.next()) {
                long game_id = Long.parseLong(res.getString(1));
                String game_date = res.getString("Date");
                game_dates.put(game_id, game_date);

                long hteam_id = Long.parseLong(res.getString(8));
                String hteam_name = res.getString("Name");
                team_names.put(hteam_id, hteam_name);

                res.next();

                long vteam_id = Long.parseLong(res.getString(8));
                String vteam_name = res.getString("Name");
                team_names.put(vteam_id, vteam_name);

                ArrayList<Pair<Long, String>> temp = team_to_games.get(hteam_id);
                if (temp == null) {
                    team_to_games.put(hteam_id, new ArrayList<Pair<Long, String>>());
                    temp = team_to_games.get(hteam_id);                    
                }
                temp.add(new Pair<Long, String>(game_id, game_date));

                temp = team_to_games.get(vteam_id);
                if (temp == null) {
                    team_to_games.put(vteam_id, new ArrayList<Pair<Long, String>>());
                    temp = team_to_games.get(vteam_id);                    
                }
                temp.add(new Pair<Long, String>(game_id, game_date));
            
                Pair<Long, Long> teams = game_to_teams.get(game_id);
                if (temp == null) {
                    game_to_teams.put(game_id, new Pair<Long, Long>(hteam_id, vteam_id));
                }
                
                long winner = Long.parseLong(res.getString("Winner ID"));
                game_to_winner.put(game_id, winner);
            }
        } catch (Exception e) {
            System.out.println("Error2: " + e.toString());
        }
        // END - BUILDING GRAPH IN MEMORY
        
        // TODO: Need to figure a way to get this programmatically..
        long team1_id = 5;
        long team2_id = 10;

        HashMap<Long, Pair<Long, String>> teams = new HashMap<Long, Pair<Long, String>>();
        HashSet<Long> visited_teams = new HashSet<Long>();
        HashSet<Long> visited_games = new HashSet<Long>();
        Queue<Long> frontier = new LinkedList<Long>();
        // 0 represents team nodes. 1 represents game nodes
        frontier.add(team1_id);
        while (frontier.size() > 0) {
            long team_id = frontier.poll();
            for (var game : team_to_games.get(team_id)) {
                if (visited_games.contains(game.first)) 
                    continue;

                long winner = game_to_winner.get(game.first);
                if (visited_teams.contains(winner) || winner != team_id) 
                    continue;

                frontier.add(winner);
                teams.put(winner, new Pair<Long, String>(team_id, game_dates.get(game.first)));
                
                visited_teams.add(winner);
                visited_games.add(game.first);
            }
        }

        Stack<String> msg_stack = new Stack<String>();
        while (team2_id != team1_id) {
            Pair<Long, String> parent = teams.get(team2_id);
            long other_team_id = parent.first;

            String name = team_names.get(team2_id);
            String other_name = team_names.get(other_team_id);
           
            msg_stack.push(String.format("%s and %s played a game against each other on %s", other_name, name, parent.second));

            team2_id = parent.first;
        }

        StringBuilder sb = new StringBuilder();
        while (msg_stack.size() > 0) {
            sb.append(msg_stack.pop());
        }
        // Output of function will output sb.toString()
        return sb.toString();
    }

    public static String chainSearchPlayer(DBController controller, String value1, String value2)
    {
        HashMap<Long, ArrayList<Pair<Long, String>>> team_to_players = new HashMap<Long, ArrayList<Pair<Long, String>>>();
        HashMap<Long, ArrayList<Pair<Long, String>>> player_to_teams = new HashMap<Long, ArrayList<Pair<Long, String>>>();
        HashMap<String, ArrayList<Pair<Long, String>>> hometown_to_players = new HashMap<String, ArrayList<Pair<Long, String>>>();
        HashMap<Long, String> player_to_hometown = new HashMap<Long, String>();

        HashMap<Long, String> player_names = new HashMap<Long, String>();
        HashMap<Long, String> team_names = new HashMap<Long, String>();
        
        String qString = String.format("SELECT * FROM public.\"Team\" INNER JOIN public.\"Player\" ON (\"Player\".\"Team ID\" = \"Team\".\"ID\");");
        Query q = new Query(qString);
        ResultSet res = controller.query(q);
        try {
            while(res.next()) {
                long team_id = Long.parseLong(res.getString(1));
                String team_name = res.getString(2);
                
                if (!team_names.containsKey(team_id)) {
                    team_names.put(team_id, team_name);
                }
                
                long player_id = Long.parseLong(res.getString(4));
                String player_name = res.getString("First Name") + " " + res.getString("Last Name");
                
                if (!player_names.containsKey(player_id)) {
                    player_names.put(player_id, player_name);
                }
                
                String hometown = res.getString("Home Town");
                
                ArrayList<Pair<Long, String>> temp = team_to_players.get(team_id);
                if (temp == null) {
                    team_to_players.put(team_id, new ArrayList<Pair<Long, String>>());
                    temp = team_to_players.get(team_id);                    
                }
                temp.add(new Pair<Long, String>(player_id, player_name));
                
                temp = hometown_to_players.get(hometown);
                if (temp == null) {
                    hometown_to_players.put(hometown, new ArrayList<Pair<Long, String>>());
                    temp = hometown_to_players.get(hometown);                    
                }
                temp.add(new Pair<Long, String>(player_id, player_name));
                
                player_to_hometown.put(player_id, hometown);
                
                temp = player_to_teams.get(player_id);
                if (temp == null) {
                    player_to_teams.put(player_id, new ArrayList<Pair<Long, String>>());
                    temp = player_to_teams.get(player_id);                    
                }
                temp.add(new Pair<Long, String>(team_id, team_name));
            }
        } catch (Exception e) {
            System.out.println("Error1: " + e.toString());
        }
        
        HashMap<Long, ArrayList<Pair<Long, String>>> team_to_games = new HashMap<Long, ArrayList<Pair<Long, String>>>();
        HashMap<Long, ArrayList<Pair<Long, String>>> game_to_teams = new HashMap<Long, ArrayList<Pair<Long, String>>>();
        HashMap<Long, String> game_dates = new HashMap<Long, String>();
        
        qString = String.format("SELECT * FROM public.\"Game\" INNER JOIN public.\"Team\" ON (\"Game\".\"HTeam ID\" = \"Team\".\"ID\" OR \"Game\".\"VTeam ID\" = \"Team\".\"ID\");");
        q = new Query(qString);
        res = controller.query(q);
        int z = 0;
        try {
            while(res.next()) {
                long game_id = Long.parseLong(res.getString(1));
                String game_date = res.getString("Date");
                game_dates.put(game_id, game_date);

                long hteam_id = Long.parseLong(res.getString(8));
                String hteam_name = res.getString("Name");

                res.next();

                long vteam_id = Long.parseLong(res.getString(8));
                String vteam_name = res.getString("Name");

                ArrayList<Pair<Long, String>> temp = team_to_games.get(hteam_id);
                if (temp == null) {
                    team_to_games.put(hteam_id, new ArrayList<Pair<Long, String>>());
                    temp = team_to_games.get(hteam_id);                    
                }
                temp.add(new Pair<Long, String>(game_id, game_date));

                temp = team_to_games.get(vteam_id);
                if (temp == null) {
                    team_to_games.put(vteam_id, new ArrayList<Pair<Long, String>>());
                    temp = team_to_games.get(vteam_id);                    
                }
                temp.add(new Pair<Long, String>(game_id, game_date));
            
                temp = game_to_teams.get(game_id);
                if (temp == null) {
                    game_to_teams.put(game_id, new ArrayList<Pair<Long, String>>());
                    temp = game_to_teams.get(game_id);                    
                }
                temp.add(new Pair<Long, String>(hteam_id, hteam_name));
                temp.add(new Pair<Long, String>(vteam_id, vteam_name));
            }
        } catch (Exception e) {
            System.out.println(z);
            System.out.println("Error2: " + e.toString());
        }
        // END - Load search space into memory

        HashMap<Long, Triplet<Long, String, Character>> players = new HashMap<Long, Triplet<Long, String, Character>>();
        HashSet<Long> visited_players = new HashSet<Long>();
        HashSet<String> visited_hometowns = new HashSet<String>();
        HashSet<Long> visited_teams = new HashSet<Long>();
        HashSet<Long> visited_games = new HashSet<Long>();

        long player1_id = 72735;
        boolean found = false;
        Set<Long> keys = player_names.keySet();
        for(Long key: keys){
            if (player_names.get(key).equals(value1)) {
                player1_id = key;
                found = true;
                break;
            }
        }
        if (!found) {
            return "Player 1's name couldn't be found in the database. Please check your spelling/capitalization and try again";
        }

        long player2_id = 81735;
        found = false;
        keys = player_names.keySet();
        for(Long key: keys){
            if (player_names.get(key).equals(value2)) {
                player2_id = key;
                found = true;
                break;
            }
        }
        if (!found) {
            return "Player 2's name couldn't be found in the database. Please check your spelling/capitalization and try again";
        }

        Queue<Long> frontier = new LinkedList<Long>();
        frontier.add(player1_id);
        
        while (!frontier.isEmpty()) {
            long player_id = frontier.poll();

            if (player_id == player2_id) {
                break;
            }

            if (visited_players.contains(player_id)) {
                continue;
            }
            visited_players.add(player_id);

            // Get this player's hometown and team id
            String hometown = player_to_hometown.get(player_id);
            long team_id = player_to_teams.get(player_id).get(0).first;

            if (hometown != null && !visited_hometowns.contains(hometown)) {
                for (var x : hometown_to_players.get(hometown)) {
                    if (!visited_players.contains(x.first)) {
                        players.put(x.first, new Triplet<Long, String, Character>(player_id, "", 'h'));
                        frontier.add(x.first);
                    }
                } 
                visited_hometowns.add(hometown);
            }     
            
            if (!visited_teams.contains(team_id)) {
                for (var i : team_to_players.get(team_id)) {
                    if (!visited_players.contains(i.first)) {
                        players.put(i.first, new Triplet<Long, String, Character>(player_id, "", 't'));
                        frontier.add(i.first);
                    }
                }
                visited_teams.add(team_id);
            }

            for (var game : team_to_games.get(team_id)) {
                if (visited_games.contains(game.first)) 
                    continue;

                for (var team : game_to_teams.get(game.first)) {
                    if (team.first == team_id || visited_teams.contains(team.first)) 
                        continue; 

                    for (var p : team_to_players.get(team.first)) {
                        if (!visited_players.contains(p.first)) {
                            players.put(p.first, new Triplet<Long, String, Character>(player_id, game_dates.get(game.first), 'g'));
                            frontier.add(p.first);
                        }
                    }
                    visited_teams.add(team.first);
                }
                visited_games.add(game.first);
            }
        }

        Stack<String> msg_stack = new Stack<String>();
        while (player2_id != player1_id) {
            Triplet<Long, String, Character> entry = players.get(player2_id);
            long other_player_id = entry.first;

            String name = player_names.get(player2_id);
            String other_name = player_names.get(other_player_id);
            String team_name = team_names.get(player_to_teams.get(player2_id).get(0).first);
           

            if (entry.third == 'h') {
                msg_stack.push(String.format("%s and %s came from the same hometown: %s", other_name, name, player_to_hometown.get(player2_id)));
            } else if (entry.third == 't') {
                msg_stack.push(String.format("%s and %s both played for %s", other_name, name, team_name));
            } else if (entry.third == 'g') {
                msg_stack.push(String.format("%s and %s played a game against each other on %s", other_name, name, entry.second));
            }

            player2_id = entry.first;
        }

        StringBuilder sb = new StringBuilder();
        while (!msg_stack.isEmpty()) {
            sb.append(msg_stack.pop());
            sb.append("\n");
        }

        return sb.toString();

    } //End function

} //End class