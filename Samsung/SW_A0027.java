// 연습A-0027. 인접한 비트의 개수
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_A0027 {
    static int T, N, K;
    static int answer;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;
        
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {   
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());

            dp = new int[N + 1][K + 1][2];  // 0초기화
            // Top-down      
            answer = dp(N, K, 0) + dp(N, K, 1);
        
            // Bottom-up
            // for(int i = 2; i <= N; i++) {
            //     for(int k = 0; k <= K; k++) {
            //         dp[i][k][0] = dp[i - 1][k][0] + dp[i - 1][k][1];
            //         dp[i][k][1] = dp[i - 1][k][0] + (k > 0 ? dp[i - 1][k - 1][1] : 0);
            //     }
            // }
            // answer = dp[n][k][0] + dp[n][k][1];

            bw.write("#" + t + " " + answer);
            bw.newLine();
        }
        bw.flush();
    }    

    static int dp(int n, int k, int s) {
        if(k >= n || k < 0) return 0;
        if(n == 1)  return 1;
        if(dp[n][k][s] > 0) return dp[n][k][s];
        if(s == 0)  return dp[n][k][0] = dp(n - 1, k, 0) + dp(n - 1, k, 1);
        else    return dp[n][k][1] = dp(n - 1, k, 0) + dp(n - 1, k - 1, 1);
    }
} 