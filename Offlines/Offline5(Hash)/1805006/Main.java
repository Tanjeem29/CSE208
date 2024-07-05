import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int Nword;
        int TabL;
        int searchcnt;
        int sSize = 7;


        long doublecol1 = 0;
        long doublecol2 = 0;
        long probeD1 = 0;
        long probeD2 = 0;
        long failD1 = 0;
        long failD2 = 0;
        long customcol1 = 0;
        long customcol2 = 0;
        long probeC1 = 0;
        long probeC2 = 0;
        long failC1 = 0;
        long failC2 = 0;
        long chaincol1 = 0;
        long chaincol2 = 0;
        long probeCh1 = 0;
        long probeCh2 = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of Words: ");
        Nword = in.nextInt();
        System.out.println("Enter size of HashTable: ");
        TabL = in.nextInt();
        System.out.println("Enter number of words to be searched: ");
        searchcnt = in.nextInt();

        String[] str = Utility.GenDict(Nword, sSize);
        String[] pick = Utility.RandPick(str, Nword, searchcnt);



        long temp;



        HashTable HT1Chain = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT1Chain.chainInsert(new Pair(str[i], i), 1, TabL);
//            if(temp == -1) {
//                //System.out.println(str[i]);
//                failD1++;
//                continue;
//            }
            chaincol1+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT1Chain.chainSearch(pick[i],1, TabL);
            if(temp == -1){
                System.out.println("Not found");
                temp = TabL;
            }
            probeCh1 += temp;
        }

        System.out.println("----------------------------");
        System.out.println("ChainHashing using Hash1:");
        //System.out.println("# of Fails: " + failC1);
        System.out.println("# of collisions: " + chaincol1);
        System.out.println("# of average probes: " + ((double) probeCh1 / searchcnt));
        System.out.println("----------------------------");




        HashTable HT2Chain = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT2Chain.chainInsert(new Pair(str[i], i), 2, TabL);
//            if(temp == -1) {
//                //System.out.println(str[i]);
//                failD1++;
//                continue;
//            }
            chaincol2+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT2Chain.chainSearch(pick[i],2, TabL);
            if(temp == -1){
                System.out.println("Not found");
                temp = TabL;
            }
            probeCh2 += temp;
        }

        System.out.println("----------------------------");
        System.out.println("ChainHashing using Hash2:");
        //System.out.println("# of Fails: " + failC1);
        System.out.println("# of collisions: " + chaincol2);
        System.out.println("# of average probes: " + ((double) probeCh2 / searchcnt));
        System.out.println("----------------------------");





        HashTable HT1Double = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT1Double.doubleHinsert(new Pair(str[i], i), 1, TabL);
            if(temp == -1) {
                //System.out.println(str[i]);
                doublecol1 += TabL;
                failD1++;
                continue;
            }
            doublecol1+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT1Double.doubleHsearch(pick[i],1, TabL);
            if(temp == -1){
                //System.out.println("Not found");
                temp = TabL;
            }
            probeD1 += temp;
        }

        System.out.println("----------------------------");
        System.out.println("DoubleHashing using Hash1:");
        System.out.println("# of Fails: " + failD1);
        System.out.println("# of collisions: " + doublecol1);
        System.out.println("# of average probes: " + ((double) probeD1 / searchcnt));
        System.out.println("----------------------------");





        HashTable HT2Double = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT2Double.doubleHinsert(new Pair(str[i], i), 2, TabL);
            if(temp == -1) {
                //System.out.println(str[i]);
                doublecol2+=TabL;
                failD2++;

                continue;
            }
            doublecol2+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT2Double.doubleHsearch(pick[i],2, TabL);
            if(temp == -1){
                //System.out.println("Not found");
                temp = TabL;
            }
            probeD2 += temp;
        }
        System.out.println("----------------------------");
        System.out.println("DoubleHashing using Hash2:");
        System.out.println("# of Fails: " + failD2);
        System.out.println("# of collisions: " + doublecol2);
        System.out.println("# of average probes: " + ((double) probeD2 / searchcnt));
        System.out.println("----------------------------");



        HashTable HT1Custom = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT1Custom.CustomHinsert(new Pair(str[i], i), 1, TabL);
            if(temp == -1) {
                //System.out.println(str[i]);
                customcol1+=TabL;
                failC1++;
                continue;
            }
            customcol1+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT1Custom.customHsearch(pick[i],1, TabL);
            if(temp == -1){
                //System.out.println("Not found");
                temp = TabL;
            }
            probeC1 += temp;
        }
        System.out.println("----------------------------");
        System.out.println("CustomHashing using Hash1:");
        System.out.println("# of Fails: " + failC1);
        System.out.println("# of collisions: " + customcol1);
        System.out.println("# of average probes: " + ((double) probeC1 / searchcnt));
        System.out.println("----------------------------");





        HashTable HT2Custom = new HashTable(TabL);
        for(int i = 0; i< Nword; i++){
            temp = HT2Custom.CustomHinsert(new Pair(str[i], i), 2, TabL);
            if(temp == -1) {
                //System.out.println(str[i]);
                customcol2+=TabL;
                failC2++;
                continue;
            }
            customcol2+=temp;
        }
        for(int i = 0; i< searchcnt; i++){
            temp = HT2Custom.customHsearch(pick[i],2, TabL);
            if(temp == -1){
                //System.out.println("Not found");
                temp = TabL;
            }
            probeC2 += temp;
        }
        System.out.println("----------------------------");
        System.out.println("CustomHashing using Hash2:");
        System.out.println("# of Fails: " + failC2);
        System.out.println("# of collisions: " + customcol2);
        System.out.println("# of average probes: " + ((double) probeC2 / searchcnt));
        System.out.println("----------------------------");




//        String t = in.next();
//        Pair p = new Pair(t, 10000);
//        System.out.println(HT1Double.doubleHinsert(p,1,TabL));
//        System.out.println(HT1Double.doubleHsearch(t,1,TabL));
//        System.out.println(HT1Double.doubleDelete(t,1, TabL));
//        System.out.println(HT1Double.doubleHsearch(t,1,TabL));
//        System.out.println(HT1Double.doubleDelete(t,1, TabL));








    }

}
