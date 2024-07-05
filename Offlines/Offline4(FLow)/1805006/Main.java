import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static int inf = 100000000;
    public static void main(String[] args) throws FileNotFoundException {

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int arr[][] = new int[6][6];
//        Graph G = new Graph(n);
//        for(int i = 0; i< n; i++){
//            for(int j = 0 ; j< n; j++){
//                arr[i][j] = in.nextInt();
//                if(arr[i][j]!=0){
//                    G.addEdge(i,j,arr[i][j]);
//                }
//            }
//        }
//        EdmondKarp EK = new EdmondKarp();
//        //System.out.println("t1");
//        System.out.println(EK.EdKarp(G,0,5));

        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("input.txt"));
        int N = Integer.parseInt(in.nextLine());

        int[] w = new int[N];
        int[] l = new int[N];
        int[] r = new int[N];
        int[][] g = new int[N][N];
        String[] names = new String[N];
        int maxwin = 0;
        int maxwinidx = -1;
        for(int i =0; i< N; i++){

            names[i] = in.next();
            w[i] = in.nextInt();
            l[i] = in.nextInt();
            r[i] = in.nextInt();
            for(int j = 0; j<N;j++){
                g[i][j] = in.nextInt();
            }
            if(maxwin<w[i]){
                maxwin = w[i];
                maxwinidx = i;
            }
        }
        int gn = (N*(N-1))/2;
        int tot = 1 + gn + N + 1;

        for(int i = 0; i< N ; i++){
            if(w[i] + r[i] < maxwin){
                System.out.println(names[i] + " " + "is eliminated");
                System.out.println("They can win at most: " + w[i] + " + " + r[i] + " = " + (w[i] + r[i]) + " games" );
                System.out.println(names[maxwinidx] + " has won a total of " + w[maxwinidx] + " games.");
                System.out.println("They play each other " + 0 + " times.");
                System.out.println("So, on average, each of the teams in this group win " + w[maxwinidx] + "/" + 1 + " = " + w[maxwinidx] + " games.\n\n");

                continue;
            }



            Graph G = makeG(g,w,l,r,N,i);
            EdmondKarp EK = new EdmondKarp();
            Graph G2 = EK.EdKarp(G,0, tot-1);
            boolean temp = isSat(G2);
            //G2.printG();
            //System.out.println(i + " : " + temp);
            if(temp == false){
                System.out.println(names[i] + " " + "is eliminated");
                Graph t = new Graph(G2);
                int[] ans = Bonus(t, 0, N);
                //for(int j = 0; j<N;j++) System.out.println(ans[j]);
                //System.out.println("TESTTTT");



                System.out.println("They can win at most: " + w[i] + " + " + r[i] + " = " + (w[i] + r[i]) + " games" );
                int count = 0;
                int winsum = 0;
                int gleft =0;
                int totwin=0;

                String teams = "";
                for(int k = 0; k < N; k++){
                    if(ans[k] != 0){
                        count++;
                        if(count == 1){
                            teams = teams + names[k];
                        }
                        else{
                            teams = teams + ", " + names[k];
                        }
                        //System.out.println(teams);
                        winsum+=w[k];
                        //System.out.println(w[k]);
                    }
                }
                //System.out.println(count);
                int[] tidx = new int[count];
                int c = 0;
                for(int k = 0; k < N; k++){
                    if(ans[k] != 0){
                        tidx[c++] = k;
                    }
                }
                for(int t1 = 0; t1 < count; t1++){
                    for(int t2 = t1+1; t2 < count; t2++){
                        gleft += g[tidx[t1]][tidx[t2]];
                    }
                }
                totwin = winsum + gleft;
                double avgwin = ((double) totwin) / count;
                System.out.println(teams + " have won a total of " + winsum + " games.");
                System.out.println("They play each other " + gleft + " times.");
                System.out.println("So, on average, each of the teams in this group win " + totwin + "/" + count + " = " + Math.round(avgwin*100.00)/100.00 + " (rounded up to " + (int)Math.ceil(avgwin) + ") games.\n\n");


            }
        }

//        for(int i = 0; i< N;i ++){
//            System.out.print(names[i] + " " + w[i] + " " + l[i] + " " + r[i]);
//            for(int j = 0; j< N; j++) System.out.print(" " + g[i][j]);
//            System.out.println();
//        }

    }

    public static int[] Bonus(Graph G, int s, int N){
        int[] visited = new int[G.V];
        LinkedList<Integer> Q = new LinkedList<Integer>();
        Q.add(s);
        visited[s]++;
        while(!Q.isEmpty()){
            int u = Q.pop();
            //System.out.println("t1");
            for(int v = 0; v < G.V; v++){
                if(visited[v] == 0){
                    for(int j = 0; j< G.adj.get(u).size(); j++){
                        if(G.adj.get(u).get(j).to == v && G.adj.get(u).get(j).weight > 0 ){
                            Q.add(v);
                            visited[v]++;
                        }
                    }
                }
            }
        }
        int[] ans = new int[N];
        int gn = (N * (N-1))/2;
        for(int i = 1+gn; i<G.V-1; i++){
            ans[i-1-gn] = visited[i];
        }
        return ans;


    }


    public static boolean isSat(Graph G){
        for(int i = 0; i< G.adj.get(0).size(); i++){
            if(G.adj.get(0).get(i).weight!= 0){
                return false;
            }
        }
        return true;
    }

    public static Graph makeG(int[][] g, int w[], int l[], int r[], int N, int x){
        int gn = (N*(N-1))/2;
        int idx;
        int tot = 1 + gn + N + 1;
        Graph G = new Graph(tot);
        for(int i = 0; i<N ;i++){
            for(int j = i+1; j< N; j++){

                idx = gn - ((N-i)*(N-i-1))/2 + (j-i-1) + 1;

                if(i == x || j == x){
                    G.addEdge(0,idx,0);
                }
                else{
                    G.addEdge(0,idx,g[i][j]);
                }
                G.addEdge(idx, gn + i+1, inf);
                G.addEdge(idx, gn + j+1, inf);
            }
        }
        for(int i = 0; i < N; i++){
            G.addEdge(gn + i+1, tot -1, w[x] + r[x] -w[i] );
        }
        return G;
    }
}




//6
//        0 16 13 0 0 0
//        0 0 10 12 0 0
//        0 4 0 0 14 0
//        0 0 9 0 0 20
//        0 0 0 7 0 4
//        0 0 0 0 0 0

//4
//        Atlanta 83 71 8 0 1 6 1
//        Philadelphia 80 79 3 1 0 0 2
//        New_York 78 78 6 6 0 0 0
//        Montreal 77 82 3 1 2 0 0
