import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_test2 {
    static int T, N, M;
    static int weight[][];
    static int dp[][];

    static int INF = 300000000;
    static int cache[][][];
    static boolean visit[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        T = Integer.parseInt(br.readLine());
        
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());
            weight = new int[N+1][M+1];
            
            //DFS로 푸는 경우
            cache = new int[N+1][M+1][4];
            visit = new boolean[N+1][M+1];

            for(int i = 1; i <= N; i++) {
                stz = new StringTokenizer(br.readLine());
                for(int j = 1; j <= M; j++) {
                    weight[i][j] = Integer.parseInt(stz.nextToken());
                    cache[i][j][0] = cache[i][j][1] = cache[i][j][2] = cache[i][j][3] = INF;
                }
            }

            visit[1][1] = true;

            System.out.println("#" + t + " " + dfs(1, 1, 0));
        }      
    }

    //dir 방향에서 온 x, y
    public static int dfs(int x, int y, int dir) {
        if (x == N && y == M) return weight[x][y];
    
        //이미 값이 계산되어 있는 경우 반환
        if (cache[x][y][dir] != INF) return cache[x][y][dir];
        
        // 아닌 경우 계산
        for (int i = 0; i < 4; i++) {
            //0 왼쪽 1 오른쪽 2 위쪽 3 아래쪽
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > N || ny < 1 || ny > M || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            cache[x][y][dir] = Math.min(cache[x][y][dir], dfs(nx, ny, i) + weight[x][y]);
            visit[nx][ny] = false;
        }
        return cache[x][y][dir];
    }

}