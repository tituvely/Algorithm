// 교육P-0008. 임계경로
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_P0008 {
    static final int MAX = 100001;
    static int T, N, M;
    static int a, b, c;
    static ArrayList<Node>[] adj = new ArrayList[MAX];
    static int indegree[] = new int[MAX];
    static int costArr[] = new int[MAX];   // 1번 공정에서 작업을 시작하여 N번 공정에서 작업이 완료되는데 걸리는 최소 시간
    static Queue<Integer> queue;

    // DFS로 풀 경우
    static boolean visited[] = new boolean[MAX];
    static int history[] = new int[MAX];

    public static class Node {
        int dest;
        int cost;

        Node(int dest, int cost) {
            super();
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());
    
            for(int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
                indegree[i] = 0;
                costArr[i] = 0;
                // DFS
                visited[i] = false;
                history[i] = 0;
            }
            
            for(int i = 0; i < M; i++) {
                stz = new StringTokenizer(br.readLine());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());
                c = Integer.parseInt(stz.nextToken());
                adj[a].add(new Node(b, c));
                indegree[b]++;
            }

            BFS();
            DFS_sort();

            bw.write("#" + t + " " + costArr[N] + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void BFS() {
        queue = new ArrayDeque<>();
        queue.add(1);
        
        int current = 0;
        while(!queue.isEmpty()) {
            current = queue.poll();
            for(Node next: adj[current]) {
                // 가장 오래 걸리는 작업에 대한 cost 처리
                costArr[next.dest] = Math.max(costArr[next.dest], costArr[current] + next.cost);
                indegree[next.dest]--;
                if(indegree[next.dest] == 0) {
                    queue.add(next.dest);
                }
            }
        }
    }

    static int temp;
    private static void DFS_sort() {
        temp = 0;
        dfs(1);
        int cur;
        // 위상정렬된 결과물인 history 배열을 역으로 꺼내어서 cost 구하기
        for(int i = temp; i >= 1; i--) {
            cur = history[i];
            for(Node next : adj[cur]) {
                costArr[next.dest] = Math.max(costArr[next.dest], costArr[cur] + next.cost);
            }
        }   
    }

    private static void dfs(int node) {
        visited[node] = true;

        for(Node next : adj[node]) {
            if(!visited[next.dest]) {
                dfs(next.dest);
            }
        }
        // 가장 끝단부터 차례대로 history에 넣음, N번 노드가 hist[1]에 위치
        history[++temp] = node;
    }

}
