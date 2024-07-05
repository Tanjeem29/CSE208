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
        G.init();

        //System.out.println("No. of Edges:");
        int E = fin.nextInt();
        int u, v;
        int d;

        for (int i = 0; i < E; i++) {
            u = fin.nextInt();
            v = fin.nextInt();
            d = fin.nextInt();
            G.addEdge(u-1, v-1, d);
        }

        Utility.FloydWarshall(G);
    }
}
