import java.util.LinkedList;

public class BinomialTree {
    node root;
    public BinomialTree(node p){
        root = p;
    }
    public BinomialTree(){
        root = null;
    }
    public static BinomialTree Merge(BinomialTree t1, BinomialTree t2){
        int r1 = t1.root.value, r2 = t2.root.value;

        if (r1 >r2){
            t1.root.children.add(t2.root);
            t2.root.parent = t1.root;
            return t1;
        }
        else{
            t2.root.children.add(t1.root);
            t1.root.parent = t2.root;
            return t2;
        }

    }

    public static void printTree(BinomialTree b1){

        System.out.print("Binomial Tree, B" + b1.root.children.size());
        node curr = b1.root;
        curr.level = 0;
        LinkedList<node> Q = new LinkedList<>();
        Q.add(curr);
        int prevlevel = -1;
        while(!Q.isEmpty()){
            curr = Q.pop();
            if(curr.parent!= null){
                curr.level = curr.parent.level +1;
            }
            if(prevlevel != curr.level){
                System.out.print("\nLevel " + curr.level + " :");
                prevlevel = curr.level;
            }
            for(int i = curr.children.size()-1; i>=0; i--){
                Q.add(curr.children.get(i));
                curr.children.get(i).level = curr.level + 1;
            }
            System.out.print(" " + curr.value);
        }
        System.out.println("");
    }


}
