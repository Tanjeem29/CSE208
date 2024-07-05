public class Graph {

    public int[][] adj;
    public int V;
    public int E;
    public int MAX = 100000000;
    public Graph(int n){
        V = n;
        adj = new int[n][n];
    }
    public void init(){
        for(int i = 0; i < V ; i++){
            for(int j = 0; j < V ; j++){
                if(i == j) continue;
                adj[i][j] = MAX;
            }
        }
        //Utility.printarr(adj, V);
    }

    public void addEdge(int u, int v, int d){
        adj[u][v] = d;
    }

}
