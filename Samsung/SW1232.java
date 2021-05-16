import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tree {
    String data;
    int left = -1;
    int right = -1;

    Tree(String data) {
        this.data = data;
    }
}

public class SW1232 {
    static Tree[] t;
    static BufferedReader br;
    static StringTokenizer stz;
    static int N, output;

    static double inorder_traverse(int leaf) {
        // System.out.println(leaf);
        double l, r, res = 0;
        if(t[leaf].left != -1 && t[leaf].right != -1) {
            l = inorder_traverse(t[leaf].left);
            r = inorder_traverse(t[leaf].right);
            
            if(t[leaf].data.equals("*")) {
                res = l * r;
            } else if(t[leaf].data.equals("/")) {
                res = l / r;
            } else if(t[leaf].data.equals("-")) {
                res = l - r;
            } else if(t[leaf].data.equals("+")) {
                res = l + r;
            }
            return res; 
        } else {
            return Double.parseDouble(t[leaf].data);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1232_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));
            
        for(int test_case=1; test_case<=10; test_case++) {
            N = Integer.parseInt(br.readLine());
            t = new Tree[N+1];
            output=0;

            for(int i=0; i<N; i++) {
                stz = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(stz.nextToken());
                Tree tree = new Tree(stz.nextToken());
                t[index] = tree;
                if(stz.hasMoreTokens()) {
                    tree.left = Integer.parseInt(stz.nextToken());
                    tree.right = Integer.parseInt(stz.nextToken());
                }
            }

            output = (int)inorder_traverse(1);

            System.out.println("#"+test_case+" "+output);
        }
    }
}
