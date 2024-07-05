import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Scanner fin = new Scanner(new File("input.txt"));

        //System.out.println("No. of Vertices:");
        int V = fin.nextInt();
        Graph2 G = new Graph2(V);

        //System.out.println("No. of Edges:");
        int E = fin.nextInt();
        int u, v;
        int d;

        for (int i = 0; i < E; i++) {
            u = fin.nextInt();
            v = fin.nextInt();
            d = fin.nextInt();
            G.addEdge(u, v, d);
        }
        int s = fin.nextInt();
        int dest = fin.nextInt();

        Utility2.dijkstra(G,s);
        System.out.println("Shortest Path Cost: " + G.d[dest]);
        Utility2.PrintSSP(G,s,dest);

//        for (int i =0;i<G.V; i++){
//            System.out.println(G.d[i]);
//        }
    }
}
