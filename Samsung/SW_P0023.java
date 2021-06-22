// 교육P-0023. 의미있는 문장 찾기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_P0023 {
    static int T, N, K;
    static int[] word;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            stz = new StringTokenizer(br.readLine());
            word = new int[N];
            for(int i = 0 ; i < N; i++) {
                word[i] = Integer.parseInt(stz.nextToken());
            }

            System.out.println("#" + t + " " + meaningful());
        }
    }   
    
    public static int meaningful() {
        int minCnt = -1;

        dp = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            dp[i][i] = 1;
        }

        for(int i = 0 ; i < N - 1; i++) {
            if(word[i] != word[i + 1]) {
                dp[i][i + 1] = 2;
            } else {
                dp[i][i + 1] = 1;
            }
        }

        for(int i = 2; i < N; i++) {
            for(int j = 0; j + i < N; j++) {
                if (countWord(j, j + i) == K) {
                    minCnt = i;
                    return minCnt;
                }
            }
        }

        return minCnt;
    }

    public static int countWord(int start, int end) {
        for(int i = start; i < end; i++) {
            if(word[i] == word[end]) {
                dp[start][end] = dp[start][end - 1];
                return dp[start][end];
            }
        }
        dp[start][end] = dp[start][end - 1] + 1;
        return dp[start][end - 1];
    }
}
