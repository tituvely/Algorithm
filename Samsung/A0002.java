import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A0002 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M, ans;
    static int[] dist;  //촌수 저장
    static ArrayList<ArrayList<Integer>> adj1;
    static ArrayList<ArrayList<Integer>> adj2;
    static boolean[] visit1;
	static boolean[] visit2;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case <= T; test_case++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            for(int i=0; i<=N; i++) {
                adj1.add(new ArrayList<Integer>());
                adj2.add(new ArrayList<Integer>());
            }

            M = Integer.parseInt(br.readLine());

            for(int i=0; i<M; i++) {
                stz = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(stz.nextToken());
                int v = Integer.parseInt(stz.nextToken());
                adj1.get(u).add(v);
                adj2.get(v).add(u);
            }

            for(int i=0; i<=N; i++) {
                int sum = dfs(1, i) + dfs(2, i) - 2;
                if(sum == N-1) ans+=1;
            }

            System.out.println("#"+test_case+" "+ans);

        }
    }

    static int dfs(int index, int i) {
        
        return 0;
    }
}
