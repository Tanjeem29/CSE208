public class PriorityQ {
    node[] a;
    int [] pqs; //Priority Queue Serial, index = id, value = pqs;
    int s;
    int capacity;

    public void swap(int i, int j){
//        int temp = pqs[a[i].id];
//        pqs[a[i].id] = pqs[a[j].id];
//        pqs[a[j].id] = temp;

        pqs[a[i].id] =j;
        pqs[a[j].id] = i;


        node t = a[i];
        a[i] = a[j];
        a[j] = t;

    }

    public PriorityQ(int n){
        capacity = n;
        a = new node[n];
        for(int i = 0; i<n;i++) a[i] = new node();
        pqs = new int[n];
        s = 0;
    }
    int size(){
        return s;
    }

    node getMin(){
        if(s>0){
            return a[1];
        }
        else return new node(-1,-1);
    }

    void Min_Heapify_Up(int i){
        while(i/2>0){
            if(a[i].key < a[i/2].key){
                swap(i,i/2);
                i = i/2;
            }
            else{
                break;
            }
        }
    }

    void Min_Heapify_Down(int i){
        while(true){
            if(2*i+1 > s){
                if(2*i > s){
                    break;
                }
                else{
                    if(a[2*i].key<a[i].key){
                            swap(2*i,i);
                            i = 2*i;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                if(a[i].key<a[2*i].key && a[i].key<a[2*i+1].key){
                    break;
                }
                else if(a[i].key<a[2*i].key && a[i].key>a[2*i+1].key){
                    swap(i, 2*i+1);
                    i = 2*i + 1;
                }
                else if(a[i].key>a[2*i].key && a[i].key<a[2*i+1].key){
                    swap(i, 2*i);
                    i = 2*i;
                }
                else{
                    if(a[2*i].key<a[2*i+1].key){
                        swap(i,2*i);
                        i = 2*i;
                    }
                    else{
                        swap(i, 2*i +1);
                        i = 2*i +1;
                    }
                }
            }
        }
    }

    void resizeUp(){
        capacity = capacity * 2;
        node[] a2 = new node[capacity];
        int[] pqs2 = new int[capacity];
        for(int i = 0; i< s; i++){
            a2[i] = new node();
            a2[i].key = a[i].key;
            a2[i].id = a[i].id;
            pqs2[i] = pqs[i];
        }
        a = a2;
        pqs = pqs2;
    }

    void insert(int id, int x){
        s++;
        if(s==capacity){
            resizeUp();
            //System.out.println(s);
        }
        //System.out.println(s);
        a[s] = new node();
        a[s].key = x;
        a[s].id = id;
        pqs[id] = s;
        Min_Heapify_Up(s);
    }
    node extractMin(){
        node t = a[1];
        pqs[a[1].id] = -1;
        a[1] = a[s--];
        pqs[a[1].id] = 1;
        Min_Heapify_Down(1);
        return t;
    }
    node seeMin(){
        return a[1];
    }

    void updateKey(int id, double nkey){
        int pqserial = pqs[id];
        a[pqserial].key = nkey;
        Min_Heapify_Up(pqserial);
    }

    void deleteKey(int id){
        int pqserial = pqs[id];
        a[pqserial] = a[s--];
        pqs[a[pqserial].id] = pqserial;
        Min_Heapify_Down(pqserial);
    }

    boolean isEmpty(){
        return (size() == 0);
    }

}
