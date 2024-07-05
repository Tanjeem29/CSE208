import java.util.Collections;

public class Utility{

    public static void Kruskal(Graph G){
        DisjointSet DS = new DisjointSet(0);
        for(int i =0; i< G.V; i++){
            DS.MakeSet(i);
        }
        Collections.sort(G.edgeList);   //O(VlgV)
        int u, v;
        double w;
        edge e;
        for(int i =0; i< G.edgeList.size(); i++){
            e = G.edgeList.get(i);
//            System.out.println("---------\n" + "---------\n" + e.getFrom() + " " + DS.p.get(e.getFrom()) + " " + DS.r.get(e.getFrom()));
//            System.out.println(e.getTo() + " " + DS.p.get(e.getTo()) + " " + DS.r.get(e.getTo()) + "\n---------\n");
            if(DS.Find(e.getTo()) != DS.Find(e.getFrom())){
                G.MSTKruskal.add(e);
                G.MSTCost += e.getWeight();
                DS.Union(e.getFrom(), e.getTo());
//                System.out.println("---------\n" + e.getFrom() + " " + DS.p.get(e.getFrom()) + " " + DS.r.get(e.getFrom()));
//                System.out.println(e.getTo() + " " + DS.p.get(e.getTo()) + " " + DS.r.get(e.getTo()) + "\n---------\n" + "---------\n");
                if(G.MSTKruskal.size()==G.V-1) break;

            }
        }
//        for(int i =0; i<DS.p.size() ;i++){
//            System.out.println(DS.p.get(i) + " " + DS.r.get(i));
//        }
    }

    public static void printMST(Graph G, int fl){

        if(fl == 1) {
            //System.out.println("Cost of the Minimum Spanning Tree is: " + G.MSTCost);
            System.out.print("List of Edges selected by Kruskal's: {");
            for (int i = 0; i < G.MSTKruskal.size(); i++) {
                System.out.print(G.MSTKruskal.get(i));
                if (i != G.MSTKruskal.size() - 1) System.out.print(",");
            }
            System.out.println("}");

        }
        if(fl == 2){
            System.out.println("Cost of the Minimum Spanning Tree is: " + G.MSTCost2);
            System.out.print("List of Edges selected by Prim's: {");
            for (int i = 0; i < G.V; i++) {
                if (G.p[i] != -1) {
                    System.out.print("(" + i + "," + G.p[i] + ")");
                    System.out.print(",");
                }
            }
            System.out.println("\b}");
        }


    }


    public static void Prim(Graph G, int s){
        int MAX = Integer.MAX_VALUE;
        G.MSTCost2 = 0;
        PriorityQ pq = new PriorityQ(G.V);
        G.p = new int[G.V];
        for(int i = 0; i<G.V; i++){
            G.p[i] = -1;
            pq.insert(i, MAX);
        }
        pq.updateKey(s, 0);
        node curr;
        while(!pq.isEmpty()){
            curr = pq.extractMin();
            //System.out.println(curr);
            G.MSTCost2 += curr.key;
            for(int i = 0; i< G.adj.get(curr.id).size(); i++){
                edge e = G.adj.get(curr.id).get(i);
                int toID = e.to;
                int pqserial = pq.pqs[toID];
                if(pqserial!=-1 && e.getWeight() < pq.a[pqserial].key){
                    G.p[toID] = e.from;
                    pq.updateKey(toID, e.getWeight());
                }
            }
        }
    }
}

