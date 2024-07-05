import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    ArrayList<ArrayList<edge>> adj;
    int V;
    int E;
    int[] visited;
    double MSTCost;
    double MSTCost2;
    int[] p;
    ArrayList<edge> edgeList;
    ArrayList<edge> MSTKruskal;

    public Graph(int n){
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        visited = new int[n];
        V = n;
        E = 0;

        edgeList = new ArrayList<>();
        MSTKruskal = new ArrayList<>(V-1);
        MSTCost = 0;
    }

    public void addEdge(int u, int v, double w){
        edge temp = new edge(u,v,w);
        adj.get(u).add(temp);
        adj.get(v).add( new edge(v,u,w));
        edgeList.add(temp);
        E++;
    }
}

class edge implements Comparable<edge>{
    int from;
    int to;
    double weight;
    public edge(int u, int v, double w){
        from = u;
        to = v;
        weight = w;
    }

    public int compareTo(edge e) {

        return Double.compare(this.weight, e.weight);
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
    public double getWeight() {
        return weight;
    }

}

class node{
    int id;
    double key;
    public node(int i, double k){
        id = i;
        key = k;
    }
    public node(){
        id = 0;
        key = 0;
    }

    @Override
    public String toString() {
        return "id=" + id + ", key=" + key;
    }
}

