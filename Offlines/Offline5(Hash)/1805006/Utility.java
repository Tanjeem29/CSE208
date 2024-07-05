import java.util.HashMap;
import java.util.Random;

public class Utility{
    public static String[] GenDict(int N, int n){
        String[] Ans = new String[N];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0 ; i < N ;i++){
            String temp = GenWord(n);
            if(map.containsKey(temp)){
                i--;
                continue;
            }
            else{
                map.put(temp, i);
                Ans[i] = temp;
            }
        }
        return Ans;
    }


    public static String GenWord(int n){
        Random rand  = new Random();
        StringBuilder ans = new StringBuilder();
        int j;
        for(int i = 0 ;i< n; i++){

            j = rand.nextInt(26);
            ans.append((char)('a' + j));
        }
        return ans.toString();
    }

    public static String[] RandPick(String[] from, int N, int n){
        String[] to = new String[n];
        Random rand = new Random();
        int temp;
        for(int i = 0; i<n ;i++){
            to[i] = from[rand.nextInt(N)];
        }
        return to;
    }

    public static void main(String[] args) {
//        for(int i = 0; i< 100; i++){
//            System.out.println(Utility.GenWord(7));
//        }
//        int N=10000;
//        String[] t = GenDict(N,7);
//        for(int i = 0; i< N; i ++){
//            int count = 0;
//            for(int j = 0; j< N; j++){
//                if(t[i].equals(t[j])){
//                    count++;
//                }
//            }
//            if(count !=1) System.out.println("Error");
//        }




//        int N = 15;
//        int n =5;
//        String[] Boro = GenDict(N, 7);
//        String[] Choto = RandPick(Boro, N, n);
//
//        System.out.println("-----------------------\n Boro");
//        for(int i = 0; i< N; i++)
//            System.out.println(Boro[i]);
//
//        System.out.println("-----------------------\n Choto");
//        for(int i= 0; i< n; i++)
//            System.out.println(Choto[i]);



        //System.out.println((int)'a');
    }
}