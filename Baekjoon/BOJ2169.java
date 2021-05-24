package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

//2169. 로봇 조종하기
public class BOJ2169 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M;
    static int weight[][];
    static int dp[][];

    static int INF = 1000000;
    static int cache[][][];
    static boolean visit[][];
    static int dx[] = {0, 0, 1};
    static int dy[] = {-1, 1, 0};

    public static void main(String[] args) throws IOException {
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        weight = new int[N+1][M+1];
        //DP로 푸는 경우
        dp = new int[N+1][M+1];

        //DFS로 푸는 경우
        cache = new int[N+1][M+1][3];
        visit = new boolean[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                weight[i][j] = Integer.parseInt(stz.nextToken());
                cache[i][j][0] = cache[i][j][1] = cache[i][j][2] = -INF;
            }
        }

        //DP로 푸는 경우
        // findMaxValue();

        //DFS로 푸는 경우
        visit[1][1] = true;
        
        System.out.println( dfs(1, 1, 0) );
    }

    public static void findMaxValue() {

        // 로봇은 왼쪽, 오른쪽, 아래쪽으로만 이동할 수 있다.

        // 시작점
        dp[1][1] = weight[1][1];

        // 첫째줄
        // 첫 줄은 왼쪽에서 오는 경우 밖에 없다
        for(int j = 2; j <= M; j++) {
            dp[1][j] = dp[1][j-1] + weight[1][j];
        }

        // 둘째줄
        // dp[n][m] = 왼쪽에서 올 때, 오른쪽에서 올 때, 위에서 올 때의 최댓값 + weight[n][m]
        // for문을 돌려서 내려올 때 위에서 내려온 경우는 최댓값이 갱신된 상태이지만
        // 왼쪽에서 오거나 오른쪽에서 온 경우는 최댓값이 갱신된 상태가 아니다
        // 따라서, 각 row에 대해 왼쪽에서 오는 경우와 오른쪽에서 오는 경우를 따로 계산해야 한다
        // 이를 위해 배열 두개를 만든다
        int left[], right[];
        for(int i = 2; i <= N; i++) {
            //왼쪽에서 오는 경우
            //왼쪽에서 올 때 VS 위에서 올 때 중에 최댓값을 구해 저장한다
            left = new int[M+1];
            left[0] = dp[i-1][1];
            for(int j = 1 ; j <= M; j++) {
                left[j] = Math.max(left[j-1], dp[i-1][j]) + weight[i][j];
            }
            
            //오른쪽에서 오는 경우
            //오른쪽에서 올 때 VS 위에서 올 때 중에 최댓값을 구해 저장한다
            right = new int[M+2];
            right[M+1] = dp[i-1][M];
            for(int j = M; j >=1 ; j--) {
                right[j] = Math.max(right[j+1], dp[i-1][j]) + weight[i][j]; 
            }

            //left[i]와 right[i] 중에 더 큰 값은 왼쪽, 오른쪽, 위쪽에서 오는 모든 경우에 대한 최댓값이 된다
            for(int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
       
        System.out.println(dp[N][M]);
    }

    //dir 방향에서 온 y,x
    public static int dfs(int x, int y, int dir) {
        // 로봇은 왼쪽, 오른쪽, 아래쪽으로만 이동할 수 있다.

        if (x == N && y == M) return weight[x][y];
    
        //이미 값이 계산되어 있는 경우 반환
        if (cache[x][y][dir] != -INF) return cache[x][y][dir];
        
        // 아닌 경우 계산
        for (int i = 0; i < 3; i++) {
            //0 왼쪽 1 오른쪽 2 아래쪽
            int nx = x + dx[i];
            int ny = y + dy[i];

            System.out.println(x + " " + y + " ");
            System.out.println(nx + " " + ny + " ");

            if (nx < 1 || nx > N || ny < 1 || ny > M || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            cache[x][y][dir] = Math.max(cache[x][y][dir], dfs(nx, ny, i) + weight[x][y]);
            visit[nx][ny] = false;
        }
        return cache[x][y][dir];
    }

}