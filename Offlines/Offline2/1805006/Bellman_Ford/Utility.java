public class Utility {
    public static int MAX = Integer.MAX_VALUE;
    public static boolean BellFord(Graph G, int s){
        InitSS(G,s);
        int from;
        int to;
        int w;
        edge e;
        for(int j = 0; j< G.V-1; j++){
            for(int i = 0; i< G.E; i++){
                e = G.edgeList.get(i);
                from = e.getFrom();
                to = e.getTo();
                w = e.getWeight();
                G.relax(from, to ,w);
            }
        }
        for(int i = 0; i< G.E; i++) {
            e = G.edgeList.get(i);
            from = e.getFrom();
            to = e.getTo();
            w = e.getWeight();
            if(G.d[to] > G.d[from] + w){
                return false;
            }
        }

        return true;
    }

    public static void PrintSSP(Graph G, int s, int dest){
        if (s == dest){
            System.out.print(s);
            return;
        }
        if(dest == -1){
            System.out.println("ERROR!! No path");
            return;
        }
        PrintSSP(G,s,G.p[dest]);
        System.out.print("-> " + dest);
    }

    public static void InitSS(Graph G, int s){
        for(int i = 0; i< G.V; i++){
            G.p[i] = -1;
            G.d[i] = MAX;
        }
        G.d[s] = 0;
    }


}
