import java.util.ArrayList;

public class node {
    int value;
    int level;
    node parent;
    ArrayList<node> children;

    public node(int v, node p){
        value = v;
        parent = p;
        children = new ArrayList<>();
        level =p.level + 1;
    }

    public node(int v){
        value = v;
        parent = null;
        children = new ArrayList<>();
        level = -1;
    }

    @Override
    public String toString() {
        return "node{" +
                "value=" + value +
                '}';
    }
}
