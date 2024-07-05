public class node {
    int value;
    int ch; // 0 for left, 1 for right
    int height;
    node parent;
    node left;
    node right;
    public node (int x){
        height = 0;
        value = x;
        parent  = null;
        left = null;
        right = null;
        ch = -1;
    }
    public static int getHeight(node n){
        if(n==null) return -1;
        return n.height;
    }
    public void addLeft(node n){
        this.left = n;
//        this.height = Math.max(getHeight(left), getHeight(right)) + 1;
        correctHeight(this);

        if(n==null) return;

        n.parent = this;
        n.ch = 0;
    }



    public static void correctHeight(node n){
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
        if(n.parent == null) return;
        correctHeight(n.parent);
    }


    public void addRight(node n){
        this.right = n;
//        this.height = Math.max(getHeight(left), getHeight(right)) + 1;
        correctHeight(this);

        if(n==null) return;

        n.parent = this;
        n.ch = 1;
    }
    public static int balance(node n){ //if >1, left skewed; if <1 right skewed
        return getHeight(n.left) - getHeight(n.right);
    }

    @Override
    public String toString() {

        String p;
        String v;
        String l;
        String r;
        String c;
        String h;
        if(parent == null) p = "null";
        else p = " " + parent.value;

        if(left == null) l = "null";
        else l = " " + left.value;

        if(right == null) r = "null";
        else r = " " + right.value;

        return "node{" +
                "value=" + value +
                ", ch=" + ch +
                ", height=" + height +
                ", parent=" + p +
                ", left=" + l +
                ", right=" + r +
                '}';
    }
}

