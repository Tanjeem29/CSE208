import java.util.LinkedList;

public class EdmondKarp {
    public  int inf = 100000000;

    public static boolean BFS(Graph G, int s, int t, int p[]){
        int visited[] = new int[G.V];
        LinkedList<Integer> Q = new LinkedList<Integer>();
        Q.add(s);
        visited[s]++;
        p[s] = -1;
        while(!Q.isEmpty()){
            int u = Q.pop();
            //System.out.println("t1");
            for(int v = 0; v < G.V; v++){
                if(visited[v] == 0){
                    for(int j = 0; j< G.adj.get(u).size(); j++){
                        if(G.adj.get(u).get(j).to == v && G.adj.get(u).get(j).weight > 0 ){
                            if(v == t){
                                p[v] = u;
                                return true;
                            }
                            Q.add(v);
                            p[v] = u;
                            visited[v]++;
                        }
                    }
                }
            }
        }

        return false;

    }

    public Graph EdKarp(Graph G, int s, int t){
        //System.out.println("t1");
        Graph G2 = new Graph(G);
        int p2[] = new int[G2.V];
        int mf = 0;
        while(BFS(G2, s, t, p2)){
            int f = inf;
            int u;
            for(int v = t; v!= s; v = p2[v]){
                u = p2[v];
                int k, f2=inf;
                for(k = 0; k < G2.adj.get(u).size(); k++){
                    if(G2.adj.get(u).get(k).to == v){
                        f2 = G2.adj.get(u).get(k).weight;
                        break;
                    }
                }
                f = Math.min(f,f2);
            }
            for(int v = t; v!= s; v = p2[v]){
                u = p2[v];
                for(int k = 0; k < G2.adj.get(u).size(); k++){
                    if(G2.adj.get(u).get(k).to == v){
                        G2.adj.get(u).get(k).weight -= f;
                        break;
                    }
                }
                for(int k = 0; k < G2.adj.get(v).size(); k++){
                    if(G2.adj.get(v).get(k).to == u){
                        G2.adj.get(v).get(k).weight += f;
                        break;
                    }
                }

            }
            mf += f;
        }
        //return mf;
        return G2;
    }
}
