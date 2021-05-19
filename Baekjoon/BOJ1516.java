// 1516. 게임 개발
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N;
    static List<List<Integer>> arr;
    static Queue<Integer> q;
    static int[] indegree; 
    static int[] time;
    static int[] dp;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        
        arr = new ArrayList<List<Integer>>();
        for(int i = 0; i <= N; i++) {
            arr.add(new ArrayList<Integer>());
        }

        indegree = new int[N+1];
        time = new int[N+1];
        dp = new int[N+1];

        int next;
        for(int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(stz.nextToken());
            while(true) {
                next = Integer.parseInt(stz.nextToken());
                if(next== -1) break;
                indegree[i]++;
                arr.get(next).add(i);
            }
        }

        findMinimumTime();
    }

    static void findMinimumTime() {
        StringBuilder sb = new StringBuilder();
        q = new LinkedList<Integer>();
        
        // indegree가 0인 건물부터 먼저 Q에 넣고 하나씩 빼며 짓기 시작함
        for(int i = 1; i <= N ; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                // 먼저 지어야 하는 건물이 없는 경우, 해당 건물을 짓는데 걸리는 최소 시간은 그 건물을 짓는데 걸리는 시간 그 자체
                dp[i] = time[i];
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(Integer next : arr.get(cur)) {
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);

                // 특정 건물을 짓기 위해 필요한 최소 시간 = 특정 건물 이전에 지어야 하는 건물들의 완공시간 중 최댓값 + 특정 건물을 짓는데 걸리는 시간
                // 즉 먼저 지어야 하는 건물 중 짓는데 가장 오래 걸린 시간 + 지금 건물을 지어야 하는데 걸리는 시간
                dp[next] = Math.max(dp[next], dp[cur] + time[next]);
            }
        }
        
        for(int i = 1 ; i <= N; i++) {
            sb.append(dp[i]+"\n");
        }

        System.out.println(sb);
        
    }
}