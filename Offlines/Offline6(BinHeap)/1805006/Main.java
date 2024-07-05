import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        String cmd;
        BinHeap MHEAP = new BinHeap(0);
        int x, y;

        while(in.hasNextLine()){
            cmd = in.next();
            if(cmd.equals("FIN")){
                System.out.println("FindMax returned " + MHEAP.findMax().value);
            }
            else if(cmd.equals("EXT")){
                System.out.println("ExtractMax returned " + MHEAP.extractMax().value);
            }
            else if(cmd.equals("INS")){
                x = in.nextInt();
                System.out.println("Inserted " + x);
                MHEAP.insert(x);
            }
            else if(cmd.equals("INC")){
                x = in.nextInt();
                y = in.nextInt();
                System.out.println("Increased " + x +  ". The updated value is " + y + ".");
                MHEAP.increaseKey(x,y);
            }
            else if(cmd.equals("PRI")){
                BinHeap.printHeap(MHEAP);
            }
        }
    }
}
