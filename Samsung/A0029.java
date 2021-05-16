// A0029. 촌수 계산
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A0029 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M;
    static int[] dist;  //촌수 저장
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            adj = new ArrayList[N+1];

            for(int i=1; i<N+1; i++) {
                adj[i] = new ArrayList<Integer>();
            }

            dist = new int[N+1];
            Arrays.fill(dist, -1);

            stz = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(stz.nextToken());
            int person2 = Integer.parseInt(stz.nextToken());

            M = Integer.parseInt(br.readLine());

            for(int i=0; i<M; i++) {
                stz = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(stz.nextToken());
                int u = Integer.parseInt(stz.nextToken());
                adj[v].add(u);
                adj[u].add(v);
            }

            bfs(person1,person2);
            System.out.println("#"+test_case+" "+dist[person2]);
        }    
    }


    static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<Integer>();
        
        dist[start] = 0;
        q.offer(start);

        while(!q.isEmpty()) {
            int now = q.poll();

            if(now==end) {
                return;
            }

            for(int i: adj[now]) {
                if(dist[i]!=-1) continue;   // 이미 촌수를 확인한 사람이면 pass
                
                dist[i] = dist[now] + 1;
                q.offer(i);
            }
        }
    }
}
