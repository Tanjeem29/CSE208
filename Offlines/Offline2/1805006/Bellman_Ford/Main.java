import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Scanner fin = new Scanner(new File("input.txt"));

        //System.out.println("No. of Vertices:");
        int V = fin.nextInt();
        Graph G = new Graph(V);

        //System.out.println("No. of Edges:");
        int E = fin.nextInt();
        int u,v;
        int d;

        for(int i =0;i < E; i++){
            u = fin.nextInt();
            v = fin.nextInt();
            d = fin.nextInt();
            G.addEdge(u,v,d);
        }
        int s = fin.nextInt();
        int dest = fin.nextInt();

        boolean b = Utility.BellFord(G,s);

//        for(int i = 0; i< G.E; i++){
//            System.out.println(G.edgeList.get(i) + " " + G.edgeList.get(i).getWeight());
//        }


        if(!b){
            System.out.println("The graph contains a negative cycle");
        }
        else{
            System.out.println("The graph does not contain a negative cycle");
            System.out.println("Shortest Path Cost: " + G.d[dest]);
            Utility.PrintSSP(G,s,dest);
        }
    }
}
