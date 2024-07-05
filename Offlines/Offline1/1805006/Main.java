import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        //Scanner in = new Scanner(System.in);
        Scanner fin = new Scanner(new File("mst.in"));

        //System.out.println("No. of Vertices:");
        int V = fin.nextInt();
        Graph G = new Graph(V);

        //System.out.println("No. of Edges:");
        int E = fin.nextInt();
        int u,v;
        double d;

        for(int i =0;i < E; i++){
            u = fin.nextInt();
            v = fin.nextInt();
            d = fin.nextDouble();
            G.addEdge(u,v,d);
        }

        Utility.Prim(G,0);
        Utility.printMST(G,2);
        Utility.Kruskal(G);
        Utility.printMST(G,1);




    }
}

//6
//9
//        0 1 1
//        0 3 3
//        1 3 5
//        1 4 1
//        3 4 1
//        1 2 6
//        2 4 5
//        4 5 4
//        2 5 2


//2
//        1
//        0 1 1