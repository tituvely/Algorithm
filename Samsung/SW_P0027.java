// 기출P-0027. 기름 값
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_P0027 {
    static int T, N, L;
    static int answer;
    static int[][] dp;
    static int[] dist;
    static int[] oil;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {   
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            L = Integer.parseInt(stz.nextToken());

            // dp[i][j]는 도시 i에서 기름이 j만큼 남았을 때의 최소비용
            dp = new int[N + 1][L + 1]; 
            dist = new int[N + 1];
            oil = new int[N + 2];
            
            stz = new StringTokenizer(br.readLine());
            for(int i = 1; i < N; i++) {
                oil[i] = Integer.parseInt(stz.nextToken());
                dist[i] = Integer.parseInt(stz.nextToken());
            }
            oil[N] = Integer.parseInt(stz.nextToken());

            boolean flag = true;
            for(int i = 1; i <= N; i++) {
                if(flag == false) break;
                for(int j = 0; j <= L; j++) {
                    if(flag == false) break;
                    //초기값 설정
                    if(i == 1) {
                        dp[i][j] = oil[1] * j;
                        continue;
                    }
                    if(j + dist[i - 1] > L) {
                        if(j - 1 >= 0) {
                            dp[i][j] = dp[i][j - 1] + oil[i];
                        } else {
                            flag = false;
                        }
                    } else {
                        if(j - 1 < 0) {
                            dp[i][j] = dp[i - 1][j + dist[i - 1]];
                        } else {
                            dp[i][j] = Math.min(dp[i - 1][j + dist[i - 1]], dp[i][j - 1] + oil[i]);
                        }
                    }
                }   
            }

            if(flag) {
                System.out.println("#" + t + " " + dp[N][0]);
            } else {
                System.out.println("#" + t + " -1");
            }
            
        }
    }
}