import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1233 {
    static String[] tree;
    static BufferedReader br;
    static StringTokenizer stz;
    static int N, poss;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1233_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));
            
        for(int test_case=1; test_case<=10; test_case++) {
            N = Integer.parseInt(br.readLine());
            tree = new String[N+1];
            poss=1;

            for(int i=0; i<N; i++) {
                stz = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(stz.nextToken());
                String data = stz.nextToken();
                tree[index] = data;

                if(!stz.hasMoreTokens()) {
                    // 리프노드가 연산자라면 계산 불가 (-> 리프노드만 체크하는 것도 방법)
                    if(data.equals("*") || data.equals("/") || data.equals("-") || data.equals("+")){
                        poss = 0;
                    }
                }
            }

            System.out.println("#"+test_case+" "+poss);
        }
    }
}
