import java.util.ArrayList;

public class DisjointSet {
    ArrayList<Integer> r;
    ArrayList<Integer> p;
    int n;
    public DisjointSet(int num){
        n =num;
        r = new ArrayList<>(n);
        p = new ArrayList<>(n);
    }

    public void MakeSet(int x){
        r.add(0);
        p.add(x);
        n++;
    }

    public int Find(int x){
        if(p.get(x)!= x){
            p.set(x, Find(p.get(x)));
        }
        return p.get(x);
    }

    public void Union(int x, int y){
        int repX = Find(x);
        int repY = Find(y);
        if(repX == repY) return;
        if(r.get(repX)>r.get(repY)){
            p.set(repY, repX);
        }
        else if(r.get(repY)>r.get(repX)){
            p.set(repX, repY);
        }
        else{
            p.set(repY, repX);
            r.set(repX, r.get(repX)+1 );
        }
    }
}

