//2098. 외판원 순회
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TSP {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N;
    static int[][] dist;
    static int[][] dp;
    static int INF = 20000000;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        dp = new int[N][1 << N];

        for(int i = 0; i < N; i++) { 
            Arrays.fill(dp[i], INF);
        }
        
        for(int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 어느 마을에서 시작하더라도 모든 마을을 순회하는 최소 비용은 같으므로
        // 0번 마을에서, 0번째 마을을 방문한 상태로(1 << 0 = 1) 시작한다. 
        System.out.println(tsp(0, 1));
    }

    /*
        현재 외판원이 current번 마을에 있고, 방문한 마을 집합이 visited일 때
        앞으로 남은 모든 마을을 방문하고 0번 마을로 돌아갈 때 드는 최소 비용
    */
    public static int tsp(int current, int visited) {
        /* 
            모든 마을을 방문한 경우
            현재 마을에서 시작지점으로 갈 수 있으면
            해당 비용을 리턴하고 아닐 경우 불가능 값을 리턴한다
        */
        if((visited == (1 << N) - 1)) {
            // 마지막 도시에서 0번 도시로 돌아갈 수 없을 경우, 불가능 값 리턴
            if (dist[current][0] == 0) return INF;
            return dist[current][0];
        }

        // 이미 방문한 적이 있는 마을일 경우
        if (dp[current][visited] != INF) {
            return dp[current][visited];
        }

        for (int k = 0; k < N; k++) {
            int next = 1 << k;
            // k번 마을이 방문되었는지는 visited & (1 << k) 값이 0인지 1인지로 판별할 수 있음
            if (dist[current][k] == 0 || (visited & next) != 0) {
                // 갈 수 없거나, 방문했다면 건너뜀.
                continue;
            }
            // k번 마을을 visited에 포함시키는 것은 visited |= (1 << k) 구문으로 수행
            dp[current][visited] = Math.min(dp[current][visited], tsp(k, visited | next) + dist[current][k]);
        }
        return dp[current][visited];
    }
}