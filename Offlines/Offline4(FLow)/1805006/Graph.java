import java.util.ArrayList;

public class Graph {
    ArrayList<ArrayList<edge>> adj;
    public int V;
    public int E;
    //public int[] visited;
    //public int[] p;

    public Graph(int n){
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
//        p = new int[n];
//        for(int i = 0; i<n ;i++){
//            p[i] = -1;
//        }
        //visited = new int[n];
        V = n;
        E = 0;
    }

    public Graph(Graph G){
        adj = new ArrayList<>();
        for(int i = 0; i < G.V; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i< G.adj.size();i++){
            for(int j = 0; j<G.adj.get(i).size();j++){
                adj.get(i).add(new edge(G.adj.get(i).get(j)));
            }
        }
        V = G.V;
        E = G.E;
    }
    public void addEdge(int u, int v, int w){
        edge temp = new edge(u,v,w);
        edge temp2 = new edge(v,u,0);
        adj.get(u).add(temp);
        adj.get(v).add(temp2);
        E++;
    }
    public void printG(){
        for(int i = 0; i < adj.size(); i++){
            System.out.println("From Vertex:" + i);
            for(int j = 0; j< adj.get(i).size();j++){
                System.out.print(adj.get(i).get(j) + ", ");
            }
            System.out.println();
        }
    }

}



class edge {
    int from;
    int to;
    int weight;
    public edge(int u, int v, int w){
        from = u;
        to = v;
        weight = w;
    }

//    public int compareTo(edge e) {
//
//        return Double.compare(this.weight, e.weight);
//    }
    public edge(edge e){
        from = e.from;
        to = e.to;
        weight = e.weight;
    }

    @Override
    public String toString() {
        return "(" + from + "," + to + ", Weight "+ weight +")";
    }

//    public int getFrom(){
//        return from;
//    }
//    public int getTo(){
//        return to;
//    }
//    public double getWeight() {
//        return weight;
//    }

}
