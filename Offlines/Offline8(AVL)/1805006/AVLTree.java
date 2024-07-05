public class AVLTree {
    static int isbalanced;
    node root;
    public AVLTree(node x){
        isbalanced = 1;
        root = x;
    }
    public AVLTree(){
        isbalanced = 1;
        root = null;
    }


    public void rightRotate(node z){
        node y = z.left;
        node t = z.left.right;
        if(z.parent == null) {
            this.root = y;
            y.ch = -1;
            y.parent = null;
            z.addLeft(t);
            y.addRight(z);
        }
        else{
            node p = z.parent;
            int fl = z.ch;
            z.addLeft(t);
            y.addRight(z);
            if(fl == 0){ //z was a left child
                p.addLeft(y);
            }
            else{
                p.addRight(y);
            }
        }
    }

    public void leftRotate(node y){
        node z = y.right;
        node t = y.right.left;
        if(y.parent == null) {
            this.root = z;
            z.ch = -1;
            z.parent = null;
            y.addRight(t);
            z.addLeft(y);
        }
        else{
            node p = y.parent;
            int fl = y.ch;
            y.addRight(t);
            z.addLeft(y);
            if(fl == 0){ //y was a left child
                p.addLeft(z);
            }
            else{
                p.addRight(z);
            }
        }
    }

//    public static  void printAVL(node n){
//        System.out.print(n.value);
//        if(n.left == null && n.right == null) {
//            return;
//        }
//        if(n.left == null){
//            System.out.print("()(");
//            printAVL(n.right);
//            System.out.print(")");
//            return;
//        }
//        if(n.right == null){
//            System.out.print("(");
//            printAVL(n.left);
//            System.out.print(")()");
//            return;
//        }
//        else{
//            System.out.print("(");
//            printAVL(n.left);
//            System.out.print(")(");
//            printAVL(n.right);
//            System.out.print(")");
//        }
//
//    }

    public node findUtil(int x){
        node curr = root;
        if(curr == null) return null;
        while(true){
            if(curr.value == x){
                return curr;
            }
            if(curr.value < x){
                if(curr.right == null){
                    return null;
                }
                else{
                    curr = curr.right;
                }
            }
            else if(curr.value > x){
                if(curr.left == null){
                    return null;
                }
                else{
                    curr = curr.left;
                }
            }
        }
    }

    public boolean find(int x){
        node n = findUtil(x);
        boolean ans;
        if(n == null) ans =  false;
        //System.out.println(n);
        else ans =  true;

        System.out.println(ans);
        return ans;
    }




    public static void printTree(node n){
        if(n==null) return;
        System.out.print(n.value);
        if(n.left == null && n.right == null) {
            return;
        }
        System.out.print("(");
        printTree(n.left);
        System.out.print(")(");
        printTree(n.right);
        System.out.print(")");
    }



    public void insert(int x){
        isbalanced = 1;
        node n = new node(x);
        node curr = root;
        if (curr == null){
            root = n;
            printTree(root);
            System.out.println();
            return;
        }
        while(true){
            if(curr.value < x){
                if(curr.right == null){
                    curr.addRight(n);
                    break;
                }
                else{
                    curr = curr.right;
                }
            }
            else if(curr.value > x){
                if(curr.left == null){
                    curr.addLeft(n);
                    break;
                }
                else{
                    curr = curr.left;
                }
            }
        }

//        if(n.parent == null){
//            return;
//        }
        checkUnbalance(n.parent.parent, n.parent, n);

        if(isbalanced == 0){
            System.out.println("Height Invariant Violated");
            System.out.print("After rebalancing: ");
            isbalanced = 1;
        }
        printTree(root);
        System.out.println();

    }

    public void checkUnbalance(node z, node y, node x){
        if(z == null){

            return;
        }
        int bal = node.balance(z);
        if(bal<-1 || bal >1){
            isbalanced = 0;
            fixUnbalance(z,y,x,bal);
            return;
        }
        checkUnbalance(z.parent, y.parent, x.parent);
    }



    public void fixUnbalance (node z, node y, node x, int balance){
        if(balance > 1){
            if(x.ch == 0){
                rightRotate(z);
                //System.out.println();
            }
            else{
                leftRotate(y);
                rightRotate(z);
            }
        }
        else{
            if(x.ch == 1){
                leftRotate(z);
                //System.out.println();
            }
            else{
                rightRotate(y);
                leftRotate(z);
            }
        }
    }


    public void delete(int val){
        isbalanced = 1;
        node dp = deleteutil(findUtil(val));
        //System.out.println(dp);
        node curr = dp;
        while(curr!=null){
            curr.height = 1 + Math.max(node.getHeight(curr.left), node.getHeight(curr.right));
            int bal = node.balance(curr);
            node x,y;
            if(bal>1){
                isbalanced = 0;
                y = curr.left;
                if(node.balance(y)>=0){
                    x = y.left;
                    rightRotate(curr);
                }
                else{
                    leftRotate(y);
                    rightRotate(curr);
                }
            }
            if(bal<-1){
                isbalanced = 0;
                y = curr.right;
                if(node.balance(y)<=0){
                    x = y.right;
                    leftRotate(curr);
                }
                else{
                    rightRotate(y);
                    leftRotate(curr);
                }
            }
            curr = curr.parent;
        }

        if(isbalanced == 0){
            System.out.println("Height Invariant Violated");
            System.out.print("After rebalancing: ");
            isbalanced = 1;
        }
        printTree(root);
        System.out.println();
    }



    public node deleteutil(node n){
        if(n.left == null && n.right == null){
            if(n.parent == null){
                //System.out.println("1: " + n.value);
                //System.out.println(n);
                root = null;
                return null;
            }
            else{
                //System.out.println("2: " + n.value);
                node temp = n.parent;
                if (n.ch == 0){
                    temp.left = null;
                }
                else{
                    temp.right = null;
                }
                //n.parent = null;
                return temp;
            }
        }
        node n2;
        if(n.right == null){
            n2 = n.left;
        }
        else{
            n2 = getMinItem(n.right);
        }
        n.value = n2.value;
        return deleteutil(n2);
    }

    node getMinItem(node n){
        node curr = n;
//        if(curr == null){
//            System.out.println("Empty BST. Returning -1");
//            return -1;
//        }
        while(true){
            if(curr.left == null){
                return curr;
            }
            curr = curr.left;
        }
    }



    public static void main(String[] args) {

//        node n10 = new node(10);
//        node n20 = new node(20);
//        node n30 = new node(30);
//        node n40 = new node(40);
//        node n25 = new node(25);
//        node n60 = new node(20);
//        AVLTree avl = new AVLTree(n20);
//        avl.root.addLeft(n10);
//        avl.root.addRight(n30);
//        avl.root.right.addRight(n40);
//        avl.root.right.addLeft(n25);
//        System.out.println();
//        printTree(avl.root);
//        System.out.println();
//
//        avl.leftRotate(avl.root);
//        printTree(avl.root);
//        System.out.println();
//
//        avl.rightRotate(avl.root);
//        printTree(avl.root);
//        System.out.println();
//        avl.insert(50);
        AVLTree avl2 = new AVLTree();
        System.out.println(avl2.find(1));
        avl2.insert(10);
        avl2.insert(20);
        avl2.insert(30);
        System.out.println(avl2.find(30));
        avl2.insert(40);
        avl2.insert(50);
        avl2.insert(60);

//        System.out.println(avl2.root);
//        System.out.println(avl2.root.left);
//        System.out.println(avl2.root.left.left);
//        System.out.println(avl2.root.left.right);
//        System.out.println(avl2.root.right);
//        System.out.println(avl2.root.right.right);
        System.out.println(avl2.find(45));
//        System.out.println(avl2.root);
//        System.out.println(avl2.root.left);
//        System.out.println(avl2.root.left.left);
//        System.out.println(avl2.root.left.right);
//        System.out.println(avl2.root.right);
//        System.out.println(avl2.root.right.right);
        printTree(avl2.root);
        System.out.println(avl2.find(50));
//        System.out.println(avl2.root);
//        System.out.println(avl2.root.left);
//        System.out.println(avl2.root.left.left);
//        System.out.println(avl2.root.left.right);
//        System.out.println(avl2.root.right);
//        System.out.println(avl2.root.right.right);
        avl2.delete(50);
        avl2.delete(60);
        avl2.insert(0);
//        System.out.println(avl2.root);
//        System.out.println(avl2.root.left);
//        System.out.println(avl2.root.left.left);
//        System.out.println(avl2.root.left.right);
//        System.out.println(avl2.root.right);
        //System.out.println(avl2.root.right.right);
        //printTree(avl2.root);

//        System.out.println();
//        System.out.println(avl.root);
//        System.out.println(avl.root.left);
//        System.out.println(avl.root.right);
//        System.out.println(avl.root.right.left);
//        System.out.println(avl.root.right.right);
//        System.out.println();
//        System.out.println(n10);
//        System.out.println(n20);
//        System.out.println(n30);
//        System.out.println(n40);
//        System.out.println(n25);
//        System.out.println();
//
//
//        avl.leftRotate(avl.root);
//
//        printTree(avl.root);
//        System.out.println();
//
//        System.out.println(avl.root);
//        System.out.println(avl.root.left);
//        System.out.println(avl.root.left.left);
//        System.out.println(avl.root.left.right);
//        System.out.println(avl.root.right);
//        System.out.println(n10);
//        System.out.println(n20);
//        System.out.println(n30);
//        System.out.println(n40);
//        System.out.println(n25);
//
//        avl.rightRotate(avl.root);
//        printTree(avl.root);
//        System.out.println();
//
//        System.out.println(avl.root);
//        System.out.println(avl.root.left);
//        System.out.println(avl.root.right);
//        System.out.println(avl.root.right.left);
//        System.out.println(avl.root.right.right);
//        System.out.println(n10);
//        System.out.println(n20);
//        System.out.println(n30);
//        System.out.println(n40);
//        System.out.println(n25);
    }
}


