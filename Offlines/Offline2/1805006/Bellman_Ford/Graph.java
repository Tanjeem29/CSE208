import java.util.ArrayList;

public class Graph {
    ArrayList<ArrayList<edge>> adj;
    public int V;
    public int E;
    public int[] p;
    public int[] d;
    public ArrayList<edge> edgeList;
    static int MAX = Integer.MAX_VALUE;
    public Graph(int v){
//        adj = new ArrayList<>();
//        for(int i = 0; i < n; i++){
//            adj.add(new ArrayList<>());
//        }
//
        V = v;
        E = 0;
        p = new int[v];
        d = new int[v];
        edgeList = new ArrayList<>();

    }

    public void addEdge(int u, int v, int w){
        edge temp = new edge(u,v,w);
//        adj.get(u).add(temp);
//        adj.get(v).add( new edge(v,u,w));
        edgeList.add(temp);
        E++;
    }

    public void relax(int u, int v, int w){
        //System.out.println(MAX + " " + u + " " + d[u] + " " + v + " " + d[v] + " " + w);
        if(d[u]!= MAX && d[u] + w < d[v]){
            d[v] = d[u] + w;
            p[v] = u;
        }
    }
}

class edge implements Comparable<edge>{
    int from;
    int to;
    int weight;
    public edge(int u, int v, int w){
        from = u;
        to = v;
        weight = w;
    }

    public int compareTo(edge e) {
        return Integer.compare(this.weight, e.weight);
    }


    @Override
    public String toString() {
        return "(" + from + "," + to + ")";
    }

    public int getFrom(){
        return from;
    }
    public int getTo(){
        return to;
    }
    public int getWeight() {
        return weight;
    }

}
