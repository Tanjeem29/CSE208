import java.util.ArrayList;

public class HashTable {
    ArrayList<ArrayList<Pair>> a1;
    ArrayList<Pair> a2;
    final long C1 = 43;
    final long C2 = 47;
    int size;

    public HashTable(int S){
        size = S;
        a2 = new ArrayList<>(S);
        a1 = new ArrayList<>(S);
        for(int i= 0; i< S; i++){
            a1.add(new ArrayList<>());
            a2.add(new Pair("", -1)); // -1 == empty space;
        }
    }

    public long Hash1(String s, int ts){
        long h=0;
        long t = 1;
        for(int i = 0; i< s.length(); i++){
            h = h + ((long)s.charAt(i) - (long)'a' + 1) * t;
            t = t * 31;
            //System.out.println(h);
        }
        return h % ts;
    }

    public long Hash2(String s, int ts){
        long h=0;
        long t = 1;
        for(int i = 0; i< s.length(); i++){
            h = h * 33 + ((long)s.charAt(i) - (long)'a' + 1);
            //System.out.println(h);
        }
        return h % ts;
    }

    public long Hash3(String s, int ts){
        long h=0;
        long t = 1;
        for(int i = 0; i< s.length(); i++){
            h = h + ((long)s.charAt(i) - (long)'a' + 1) * t;
            t = t * 29;
            //System.out.println(h);
        }
        return h % ts;
    }

    public long auxHash(String s, int ts){
        long h=0;
        long t = 1;
        for(int i = 0; i< s.length(); i++){
            h = h + ((long)s.charAt(i) - (long)'a' + 1) * t;
            t = t * 29;
            //System.out.println(h);
        }
        return h % ts;
    }

    public long doubleHash(Pair p , int i, int fl, int ts){
        if(fl==1){
            return (Hash1(p.key, ts) + i * auxHash(p.key, ts)) % size;
        }
        if(fl==2){
            return (Hash2(p.key, ts) + i * auxHash(p.key, ts)) % size;
        }
        else return -1;
    }

    public long customHash(Pair p , int i, int fl, int ts){
        if(fl==1){
            return (Hash1(p.key,ts) + C1 * i * auxHash(p.key, ts) + C2 * i * i) % size;
        }
        if(fl==2){
            return (Hash2(p.key, ts) + C1 * i * auxHash(p.key, ts) + C2 * i * i) % size;
        }
        else return -1;
    }

    public long doubleHinsert(Pair p , int fl, int ts){
        //long collcount = 0;
        int i;
        int hval;
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(doubleHash(p, i, fl, ts));
            if(a2.get(hval).val == -1 || a2.get(hval).val == -2){ //-1 == empty, -2 == deleted
                a2.set(hval, p);
                return i;
            }
        }
        //System.out.println("Fail");
        return -1;
    }
    public long doubleHsearch(String s, int fl, int ts){
        int i;
        int hval;
        Pair p = new Pair(s, -1);
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(doubleHash(p, i, fl, ts));
            if(a2.get(hval).key.equals(s)){
                return i+1;
            }
            else if(a2.get(hval).val == -1){
                return -1;
            }

        }
        return -1;
    }

    public long CustomHinsert(Pair p , int fl, int ts){
        //long collcount = 0;
        int i;
        int hval;
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(customHash(p, i, fl, ts));
            if(a2.get(hval).val == -1 || a2.get(hval).val == -2){ //-1 == empty, -2 == deleted
                a2.set(hval, p);
                return i;
            }
        }
        //System.out.println("Fail");
        return -1;
    }
    public long customHsearch(String s, int fl, int ts){
        int i;
        int hval;
        Pair p = new Pair(s, -1);
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(customHash(p, i, fl, ts));
            if(a2.get(hval).key.equals(s)){
                return i+1;
            }
            else if(a2.get(hval).val == -1){
                return -1;
            }

        }
        return -1;
    }






    public long chainInsert(Pair p, int fl, int ts) {
        int i;
        int hval = 0;
        if (fl == 1) {
            hval = Math.toIntExact(Hash1(p.key, ts));
        }
        else if(fl == 2){
            hval = Math.toIntExact(Hash2(p.key, ts));
        }
        for (i = 0; i < a1.get(hval).size(); i++) {
            if (a1.get(hval).get(i).val == -1 || a1.get(hval).get(i).val == -2) { //-1 == empty, -2 == deleted
                a1.get(hval).set(i, p);
                //System.out.println("ERROR");
                if(i>0) return 1;
                return i;
            }
        }
        a1.get(hval).add(p);
        //System.out.println(i);
        if(i>0) return 1;
        return i;
    }

    public long chainSearch(String s, int fl, int ts){
        int i;
        int hval=0;
        Pair p = new Pair(s, -1);
        if(fl == 1){
            hval = Math.toIntExact(Hash1(p.key, ts));
        }
        else if (fl == 2){
            hval = Math.toIntExact(Hash2(p.key, ts));
        }
        for(i =0; i< a1.get(hval).size();i++){
            if(a1.get(hval).get(i).key.equals(s)){
                return i+1;
            }
        }
        return -1;

    }


    public int chainDelete(String s, int fl, int ts){
        int i;
        int hval = 0;
        Pair p = new Pair(s, -1);
        if(fl == 1){
            hval = Math.toIntExact(Hash1(p.key, ts));
        }
        else if (fl == 2){
            hval = Math.toIntExact(Hash2(p.key, ts));
        }
        for(i =0; i< a1.get(hval).size();i++){
            if(a1.get(hval).get(i).key.equals(s)){
                a1.get(hval).remove(i);
                return 1;
            }
        }
        return -1;
    }


    public int customHDelete(String s, int fl, int ts){
        int i;
        int hval;
        Pair p = new Pair(s, -1);
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(customHash(p, i, fl, ts));
            if(a2.get(hval).key.equals(s)){
                a2.set(hval, new Pair("", -2));
                return 1;
            }
            else if(a2.get(hval).val == -1){
                return -1;
            }
        }
        return -1;
    }

    public int doubleDelete(String s, int fl, int ts){
        int i;
        int hval;
        Pair p = new Pair(s, -1);
        for(i = 0; i< size; i++){
            hval = Math.toIntExact(doubleHash(p, i, fl, ts));
            if(a2.get(hval).key.equals(s)){
                a2.set(hval, new Pair("", -2));
                return 1;
            }
            else if(a2.get(hval).val == -1){
                return -1;
            }

        }
        return -1;
    }

//    public static void main(String[] args) {
//        String temp = "abcabcd";
//        HashTable h = new HashTable(10);
//        long ans = h.Hash1(temp, 10);
//        System.out.println(ans);
//        System.out.println("-----------------");
//        long ans2 = h.Hash2(temp, 10);
//        System.out.println(ans2);
//        System.out.println("-----------------");
//        long  ans3 = h.auxHash(temp,10);
//        System.out.println(ans3);
//        System.out.println("-----------------");
////        ArrayList<Pair> a = new ArrayList<>(10);
////        for(int i = 0; i<15; i++){
////            System.out.println(a.get(i));
////        }
//
//    }


}




