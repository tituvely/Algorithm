import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 교육P-0026. 최저 공통 조상
public class SW_P0026 {
    static final int LGN = 17;  // 2^16 (65,536) < N = 100,000 < 2^17 (131,072) 2^K = N번 조상, K = logN
    static int T, N, Q; 
    static int a, b;
    static int[][] parent;
    static int[] depth;
    static ArrayList<Integer>[] adjList;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            Q = Integer.parseInt(stz.nextToken());

            adjList = new ArrayList[N + 1];
            depth = new int[N + 1];

            for(int i = 1; i<= N; i++) {
                adjList[i] = new ArrayList<>();
                depth[i] = -1;
            }

            parent = new int[LGN + 1][N + 1];
            stz = new StringTokenizer(br.readLine());
            for(int i = 1; i < N; i++) {
                int a = Integer.parseInt(stz.nextToken());
                adjList[a].add(i + 1);
            }

            // 루트 정점
            depth[1] = 0;

            // depth 설정
            // BFS
            bfs(1, 0);
            // DFS -> stack overflow가 발생할 수 있음
            // dfs(1);

            findAncestor();

            StringBuilder sb = new StringBuilder();

            for(int i = 1; i <= Q; i++) {
                stz = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());
                sb.append(" ").append(lca(a,b));
            }

            System.out.println("#" + t + sb);
        }
    }

    private static void dfs(int cur) {
        for(int next : adjList[cur]) {
            if(depth[next] != -1) 
                continue;
            // parent[i][j] -> j의 2^i 번째 부모 저장
            parent[0][next] = cur;
            depth[next] = depth[cur] + 1;

            dfs(next);
        }
    }

    private static void bfs(int start, int dep) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        depth[start] = dep;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : adjList[cur]) {
                if(depth[next] != -1)
                    continue;
                parent[0][next] = cur;
                depth[next] = depth[cur] + 1;
                q.add(next);
            }
        }
    }

    // 2^n 번째 조상들을 다 저장해둔다
    private static void findAncestor() {
        // k = 0은 BFS(DFS)를 통해 저장해놓음
        for(int k = 1; k <= LGN; k++) {
            for(int v = 1; v<= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }
    }

    // x와 y의 공통 조상 찾기
    private static int lca(int x, int y) {
        // 루트 기준에서 더 아래 있는 정점을 y, 더 높이 있는 정점을 x로 맞춘다.
        // depth가 더 작다는 것은 루트에 더 가깝다는 뜻
        // depth가 큰 정점을 y로 만든다
        if(depth[x] > depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        // y의 depth가 x의 depth와 같아질 때까지 y를 끌어올림 = 높이 맞추기
        for(int i = LGN; i >= 0; i--) {
            // 1 << i 값은 2의 i승 값이다
            // [i, i << 1] = [17, 131072], [16, 65536], [15, 32768] ... [4, 16], [3, 8], [2, 4], [1, 2], [0, 1]
            if(depth[y] - depth[x] >= (1 << i)) {
                y = parent[i][y];
            }

        }

        // 동일한 높이까지 끌어올렸을 때, x와 y가 값다면 둘의 조상이 같다는 뜻이니 
        // 그것이 최저 공통 조상이다.
        if(x == y)
            return x;

        // x와 y가 같지 않다면, 루트에서부터 처음으로 조상이 같지 않은 지점을 만날 때까지 탐색
        // 처음으로 달라진 위치에서 그들의 부모 중 공통 조상을 다시 찾음
        for(int i = LGN; i >= 0; i--) {
            if(parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];

    }


}
