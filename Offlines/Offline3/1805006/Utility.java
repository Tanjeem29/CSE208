public class Utility {
    public static void FloydWarshall(Graph G){
        int[][] p = new int[G.V][G.V];
        int[][] arr = new int[G.V][G.V];
        for(int i = 0; i < G.V ; i++){
            for(int j = 0; j < G.V ; j++){
                arr[i][j] = G.adj[i][j];
            }
        }

        for(int k = 0; k< G.V; k++){
            for(int i = 0; i< G.V; i++){
                for(int j = 0; j < G.V; j++){
                    if(arr[i][j] > arr[i][k] + arr[k][j]){
                        //System.out.println(arr[i][j] + " " + arr[i][k] + " " + arr[k][j] + " " );
                        //Utility.printarr(arr, G.V);
                        arr[i][j] = arr[i][k] + arr[k][j];
                        p[i][j] = k;
                    }
                }
            }
        }

        printarr(arr, G.V);
        //printpath(0,3, p);
    }

    public static void printarr(int[][] a, int n){
        int max = 100000000;
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n ; j++){
                if(a[i][j] == max){
                    System.out.print("INF\t");
                }
                else{
                    System.out.print(a[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void printpath(int u, int v, int a[][]){
        if(a[u][v]!= 0){
            printpath(u, a[u][v] , a);
            System.out.print("- v" + (a[u][v]+1));
            printpath(a[u][v], v, a);
            return;
        }
        else {
            //System.out.print("- v" + u);
            return;
        }
    }
}
