// 10942.팰린드롬
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.util.Arrays;
import java.util.StringTokenizer;

public class DP_Palindrome {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] dp;
    static int[] num;
    static int N;
    // static int[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        dp = new boolean[N+1][N+1];
        // dp = new int[N][N];

        StringTokenizer stz = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(stz.nextToken());
            // 재귀
            // Arrays.fill(dp[i], -1);
        }

        //비재귀
        solve();

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(stz.nextToken());
            int E = Integer.parseInt(stz.nextToken());

            if(dp[S][E]) sb.append("1\n");
            else sb.append("0\n");

            // 재귀
            // System.out.println(solve_recursive(S-1,E-1));
        }
        System.out.println(sb);
    }

    public static void solve() {
        // 길이가 1일때 계산
        for(int i = 1; i <= N; i++)
            dp[i][i] = true;
        // 길이가 2일때 계산
        for(int i = 1; i <= N - 1; i++)
            if(num[i] == num[i + 1]) dp[i][i + 1]= true;

        // 길이가 3 이상일 때
        for(int i = 2; i < N; i++){
            for(int j = 1; j <= N - i; j++){
                if(num[j] == num[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }

    // public static int solve_recursive(int S, int E) {
    //     if(S>=E) return 1;
    //     if(dp[S][E] != -1) return dp[S][E];
    //     if(num[S]!=num[E]) return 0;
    //     return solve_recursive(S+1, E-1);
    // }

}