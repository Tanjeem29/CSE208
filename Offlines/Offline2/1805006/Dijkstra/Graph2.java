import java.util.ArrayList;
import java.util.Collections;

public class Graph2{
    ArrayList<ArrayList<edge>> adj;
    public int V;
    public int E;
    public int[] visited;
    //double MSTCost;
    //double MSTCost2;
    public int[] p;
    public int[] d;
    ArrayList<edge> edgeList;
    //ArrayList<edge> MSTKruskal;

    public Graph2(int n){
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        visited = new int[n];
        d = new int[n];
        V = n;
        E = 0;

        edgeList = new ArrayList<>();
        //MSTKruskal = new ArrayList<>(V-1);
        //MSTCost = 0;
    }

    public void addEdge(int u, int v, int w){
        edge temp = new edge(u,v,w);
        adj.get(u).add(temp);
        //adj.get(v).add( new edge(v,u,w));
        edgeList.add(temp);
        E++;
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

class node{
    int id;
    int key;
    public node(int i, int k){
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

