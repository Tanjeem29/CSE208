import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        String cmd;
        AVLTree avl = new AVLTree();
        int x;

        while(in.hasNextLine()){
            cmd = in.next();
            if(cmd.equals("F")){
                x = in.nextInt();
                avl.find(x);
            }
            else if(cmd.equals("I")){
                x = in.nextInt();
                avl.insert(x);
            }
            else if(cmd.equals("D")){
                x = in.nextInt();
                avl.delete(x);
            }
            else{
                System.out.println("Unknown Command");
            }
        }


    }
}
