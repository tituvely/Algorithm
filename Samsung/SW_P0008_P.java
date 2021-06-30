// 연습P-0008. 웜홀
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_P0008_P {
    
    static class Node {
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

    }

    static int N, M, W;
    static int s, e, t;
    static ArrayList<Node> adjList;
    static long[] dist;
    private static final long INF = 987654321987654321L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());    // 농장의 수
            M = Integer.parseInt(stz.nextToken());    // 도로의 수
            W = Integer.parseInt(stz.nextToken());    // 웜홀의 수
            
            dist = new long[N + 1];
            adjList = new ArrayList<>();

            for(int i = 1; i <= M; i++) {
                stz = new StringTokenizer(br.readLine());
                s = Integer.parseInt(stz.nextToken());
                e = Integer.parseInt(stz.nextToken());
                t = Integer.parseInt(stz.nextToken());
                adjList.add(new Node(s, e, t));
                adjList.add(new Node(e, s, t));
            }

            for(int i = 1; i <= W; i++) {
                stz = new StringTokenizer(br.readLine());
                s = Integer.parseInt(stz.nextToken());
                e = Integer.parseInt(stz.nextToken());
                t = Integer.parseInt(stz.nextToken());
                adjList.add(new Node(s, e, -t));
            }

            if(BellmanFord(N, 2 * M + W, 1)) {
                System.out.println("#" + tc + " YES");
            } else {
                System.out.println("#" + tc + " NO");
            }
        }
    }

    // V : 정점의 수
    // E : 간선의 수
    // start : 시작 정점
    private static boolean BellmanFord(int V, int E, int start) {
        // 무한대 값으로 초기화
        Arrays.fill(dist, INF);

        // 시작점은 거리 0으로 세팅
        dist[start] = 0;

        // 업데이트가 발생하지 않는다면, 반복문 종료 가능
        boolean isUpdate = false;
        
        // 음의 사이클 (최단 거리가 무한히 음수로 내려가는 경우) 이 없으면
        // 적어도 V-1번 돌리면 무조건 최단경로가 확정된다
        for(int i = 1; i <= V; i++) {
            isUpdate = false;
            // E
            for (Node node : adjList) {
                // 모든 정점들이 연결되어 있다는 보장이 없을 떄
                // 다른 시작점에서 사이클이 발생할 수 있는 경우
                // 사이클의 유무를 파악하려면 아래 조건 삭제
                if(dist[node.start] == INF)
                    continue;
                
                if(dist[node.end] > dist[node.start] + node.cost) {
                    dist[node.end] = dist[node.start] + node.cost;
                    isUpdate = true;
                }
            }
            if(!isUpdate)
                break;
        }

        // 첫번째 for 문을 V-1번이 아니라 V번까지 돌리면
        // isUpdate 변수의 값으로도 판별이 가능하다
        return isUpdate;
     
    }

}