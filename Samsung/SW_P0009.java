// 교육P-0009. 최단 경로
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW_P0009 {
    static int N, M;
    static int a,b,c;
    static ArrayList<Node>[] adjList;
    static int[] cost;

    static class Node implements Comparable<Node> {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
        
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());    // 도시의 수
            M = Integer.parseInt(stz.nextToken());    // 도로의 수

            cost = new int[N + 1];
            adjList = new ArrayList[N + 1];

            for(int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for(int i = 1; i <= M; i++) {
                stz = new StringTokenizer(br.readLine());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());
                c = Integer.parseInt(stz.nextToken());
                adjList[a].add(new Node(b, c));
                adjList[b].add(new Node(a, c));
            }

            dijkstra(1);

            int result = cost[N] == Integer.MAX_VALUE? -1 : cost[N];

            System.out.println("#" + t + " " + result);
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(cost, Integer.MAX_VALUE);
        
        cost[start] = 0;
        
        pq.add(new Node(start, 0));
     
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 더 큰 가중치로 도착한 경우 pass
            if(cost[cur.dest] < cur.cost) {
                continue;
            }

            for(Node next : adjList[cur.dest]) {
                // cost가 더 작을때만 갱신
                if(cost[next.dest] > next.cost + cost[cur.dest]) {
                    cost[next.dest] = next.cost + cost[cur.dest];
                    pq.add(new Node(next.dest, cost[next.dest]));
                }
            }

        }

    }

    
}
