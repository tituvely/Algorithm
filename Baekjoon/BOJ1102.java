// 1102. 발전소
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1102 {
    static int N, P;
    static int cost[][];
    static char powerplant[];
    static int dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        for(int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        dp = new int[1 << N];
        Arrays.fill(dp, -1);

        powerplant = br.readLine().toCharArray();
        P = Integer.parseInt(br.readLine());
        int on = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            if(powerplant[i] == 'Y') {
                on = on | (1 << i);
                cnt++;
            }
        }
        
        if(cnt == 0) {
            if(P == 0) System.out.println(0);
            else System.out.println(-1);
        }
        else if(cnt >= P) {
            System.out.println(0);
        }
        else {
            System.out.println(findMinimumCost(cnt, on));
        }
    }

    static int findMinimumCost(int cnt, int on) {
        if(cnt == P) return 0;

        if(dp[on] != -1) return dp[on];

        dp[on] = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            if((on & (1 << i)) != 0) {
                for(int j = 0; j < N; j++) {
                    if((on & (1 << j)) == 0) {
                        dp[on] = Math.min(dp[on], cost[i][j] + findMinimumCost(cnt + 1, on | (1 << j)));
                    }
                }
            } 
        }
        return dp[on];
    }
}