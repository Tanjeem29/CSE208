public class Pair {
    String key;
    int val;
    public Pair(String s, int i){
        key = new String(s);
        val = i;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key='" + key + '\'' +
                ", val=" + val +
                '}';
    }
}
