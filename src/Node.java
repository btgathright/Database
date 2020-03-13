import java.util.*;

public class Node {
    
    public int get_id() {
        return this.id;
    }

    public HashSet<Integer> get_connection_ids() {
        return this.connection_ids;
    }

    public char get_type() {
        return type;
    }

    protected int id;
    protected char type;
    protected HashSet<Integer> connection_ids;
    public Node parent;
} 