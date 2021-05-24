// 6232. Ranking the Cows
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ6232 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M;
    static boolean connected[][];
    static List<Integer> adjacent[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());

        connected = new boolean[N + 1][N + 1];
        adjacent = new LinkedList[N + 1];
        for(int i = 0; i < N + 1; i++) {
            adjacent[i] = new LinkedList<Integer>();
        }

        int start, end;
        for(int i = 0; i < M; i++) {
            stz = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stz.nextToken());
            end = Integer.parseInt(stz.nextToken());
            connected[start][end] = true;
            adjacent[start].add(end);   
        }

        // 모든 정점에 대해서 DFS를 진행하고, 
        // 방문할 수 있는 정점에 대해서 connected값을 true로 갱신한다.
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            dfs(i, i);
        }

        int answer = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(!connected[i][j] && !connected[j][i]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int v, int start) {
        visited[v] = true;
        connected[start][v] = true;

        for(int i = 0; i < adjacent[v].size(); ++i) {
            int next = adjacent[v].get(i);
            if(!visited[next]) {
                dfs(next, start);    
            }
        }
    }
}