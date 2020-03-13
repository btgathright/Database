import java.util.*;

public class GameNode extends Node {
    public GameNode(int id, HashSet<Integer> team_ids, int winner_id) {
        this.id = id;
        this.connection_ids = team_ids;
        this.type = 'g';
        this.winner_id = winner_id;
        this.parent = null;
    }

    public int get_winner_id() {
        return this.winner_id;
    }

    private int winner_id;
} 