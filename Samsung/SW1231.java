import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW1231 {
    static String[] tree;
    static BufferedReader br;
    static StringTokenizer stz;
    static int leafStart, N;
    static String output;

    static void init() {
        leafStart = 1;
        while (N >= leafStart) {
            leafStart *= 2;
        }
    }

    static void inorder_traverse(int leaf) {
        //왼쪽 자식이 있다면
        if(leaf * 2 <= N) {
            inorder_traverse(leaf*2);
        }

        output += tree[leaf];

        //오른쪽 자식이 있다면
        if(leaf * 2 + 1 <= N) {
            inorder_traverse(leaf*2+1);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1231_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));
            
        for(int test_case=1; test_case<=10; test_case++) {
            N = Integer.parseInt(br.readLine());
            tree = new String[N+1];
            output="";

            for(int i=0; i<N; i++) {
                stz = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(stz.nextToken());
                tree[index] = stz.nextToken();
            }

            init();

            inorder_traverse(1);

            
            System.out.println("#"+test_case+" "+output);
        }
    }
}
