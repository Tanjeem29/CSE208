public class Pair {
    int idx;
    int value;
    public Pair(int i, int v){
        idx = i;
        value = v;
    }
    public Pair(Pair p){
        idx = p.idx;
        value = p.value;
    }
}
