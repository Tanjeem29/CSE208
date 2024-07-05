public class Utility2 {
    public static void dijkstra(Graph2 G, int s){
        int MAX = Integer.MAX_VALUE;
        PriorityQ pq = new PriorityQ(G.V);
        G.p = new int[G.V];
        for(int i = 0; i<G.V; i++){
            G.p[i] = -1;
            pq.insert(i, MAX);
        }
        pq.updateKey(s, 0);
        node curr;
//        for(int i = 0; i<G.V;i++){
//            System.out.println(G.p[i]);
//        }
        while(!pq.isEmpty()){
//            for(int i = 0; i<G.V;i++){
//                System.out.println(i + " " + G.p[i]);
//            }
//            System.out.println("\n\n");
            int minkey = pq.seeMin().key;
            //System.out.println(pq.seeMin().id + " "+ minkey);
            curr = pq.extractMin();
            G.d[curr.id] = curr.key;



            //System.out.println(curr);
            //G.MSTCost2 += curr.key;
            for(int i = 0; i< G.adj.get(curr.id).size(); i++){
                edge e = G.adj.get(curr.id).get(i);
                int toID = e.to;
                //int fromID = e.from;
                int pqserial = pq.pqs[toID];
                //int pqsfrom = pq.pqs[fromID];
                if(pqserial!=-1 && minkey + e.getWeight() < pq.a[pqserial].key){
                    G.p[toID] = e.from;
                    pq.updateKey(toID, minkey + e.getWeight());
                }
            }
        }
    }

    public static void PrintSSP(Graph2 G, int s, int dest){
        if (s == dest){
            System.out.print(s);
            return;
        }
        if(dest == -1){
            System.out.println("No path");
            return;
        }
        PrintSSP(G,s,G.p[dest]);
        System.out.print("-> " + dest);
    }

//    public static void InitSS(Graph2 G, int s){
//        for(int i = 0; i< G.V; i++){
//            G.p[i] = -1;
//            G.d[i] = MAX;
//        }
//        G.d[s] = 0;
//    }

}
