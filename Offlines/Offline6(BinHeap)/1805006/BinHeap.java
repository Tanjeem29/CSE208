import java.util.*;

public class BinHeap {
    ArrayList<BinomialTree> arr;
    HashMap<Integer, node> HM;

    public BinHeap(int n){
        arr = new ArrayList<>();
        HM = new HashMap<>();
    }


    public static BinHeap Union(BinHeap h1, BinHeap h2){
        int s1 = h1.arr.size(), s2 = h2.arr.size();
        BinHeap big, small;
        int s = Math.max(s1,s2);
        if(s1 == s){
            big = h1;
            small = h2;
        }
        else{
            big = h2;
            small = h1;
        }

        for(int i = small.arr.size(); i<big.arr.size(); i++ ){
            small.arr.add(null);
        }


        BinHeap ans = new BinHeap(0);
        BinomialTree carry = null;
        for(int i = 0; i< s; i++){

            if(small.arr.get(i) == null){
                if(carry == null){
                    ans.arr.add(big.arr.get(i));
                }
                else{
                    if(big.arr.get(i) == null){
                        ans.arr.add(carry);
                        carry = null;
                    }
                    else if(big.arr.get(i)!= null){
                        carry = BinomialTree.Merge(carry, big.arr.get(i));
                        ans.arr.add(null);
                    }
                }
            }
            else if(small.arr.get(i) != null){
                if(carry == null){
                    if(big.arr.get(i) == null){
                        ans.arr.add(small.arr.get(i));
                    }
                    else if(big.arr.get(i) != null){
                        ans.arr.add(null);
                        carry = BinomialTree.Merge(small.arr.get(i), big.arr.get(i));
                    }
                }
                else if(carry != null){
                    if(big.arr.get(i) == null){
                        ans.arr.add(null);
                        carry = BinomialTree.Merge(small.arr.get(i), carry);
                    }
                    else if(big.arr.get(i) != null){
                        ans.arr.add(carry);
                        carry = BinomialTree.Merge(small.arr.get(i), big.arr.get(i));
                    }
                }
            }
        }
        if (carry!=null)
            ans.arr.add(carry);
        ans.HM.putAll(h1.HM);
        ans.HM.putAll(h2.HM);
        return ans;

    }

    public void insert( int x){
        node n = new node(x);
        BinomialTree t = new BinomialTree(n);

        BinHeap temp = new BinHeap(0);
        temp.arr.add(t);
        BinHeap ans =  BinHeap.Union(temp, this);
        this.arr = ans.arr;
        this.HM.put(x,n);
    }

    public Pair findMax(){

        if(arr.size()==0) return new Pair(-1,-1);
        int idx=0, max = -1;
        for(int i = 0; i< arr.size(); i++){
            if(arr.get(i) == null ) continue;
            if(arr.get(i).root.value>max){
                max = arr.get(i).root.value;
                idx = i;
            }
        }
        return new Pair(idx,max);

    }

    public Pair extractMax(){
        Pair p = new Pair(findMax());
        BinomialTree MaxT = arr.get(p.idx);
        BinHeap NewHeap = new BinHeap(0);


        for(int i = 0; i< MaxT.root.children.size(); i++){
            NewHeap.arr.add(null);
        }
        for(int i = 0; i< MaxT.root.children.size(); i++){
            MaxT.root.children.get(i).parent = null;
            BinomialTree temp = new BinomialTree(MaxT.root.children.get(i));
            NewHeap.arr.add( temp.root.children.size(),temp);
        }
        arr.set(p.idx, null);
        BinHeap ans = Union(NewHeap, this);
        this.arr = ans.arr;
        this.HM.remove(p.value);
        return p;

    }

    public void increaseKey(int prevVal, int newVal){
        node n = HM.get(prevVal);
        n.value = newVal;
        node p;
        HM.remove(prevVal);
        HM.put(newVal, n);
        if(n.parent == null) return;

        while(n.value>n.parent.value){
            p = n.parent;
            HM.remove(n.value);
            HM.remove(p.value);
            int temp = n.value;
            n.value = p.value;
            p.value = temp;
            HM.put(n.value, n);
            HM.put(p.value,p);
            if(p.parent == null) break;
            n = p;
        }
    }

    public void delete(int x){
        increaseKey(x, findMax().value + 10);
        extractMax();
    }

    public static void printHeap(BinHeap h1){
        System.out.println("Printing Binomial Heap...");
        System.out.println("---------------------------");
        for(int i = 0; i< h1.arr.size(); i++){
            if(h1.arr.get(i)!= null){
                BinomialTree.printTree(h1.arr.get(i));
            }
        }
        System.out.println("---------------------------");
    }

}
