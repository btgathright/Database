import java.util.*;

public class TeamNode extends Node {
    public TeamNode(int id, HashSet<Integer> game_ids) {
        this.id = id;
        this.connection_ids = game_ids;
        this.type = 't';
        this.parent = null;
    }
} 