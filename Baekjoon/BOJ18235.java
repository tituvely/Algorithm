// 18235. 지금 만나러 갑니다
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18235 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, A, B, ans = -1;
    static int L;
    static int visitedA[][], visitedB[][];
    public static void main(String[] args) throws Exception {
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        A = Integer.parseInt(stz.nextToken());
        B = Integer.parseInt(stz.nextToken());

        // 1) BFS 탐색으로 푸는 경우
        // 먼저 오리의 점프 위치를 저장하고 육리의 점프 위치를 기록하면서 오리가 방문했던 곳이면 같은 일수에 방문한 것인지 확인하면 된다.
        // 또한, 오리와 육리가 만나는 최소 일수는 20일을 넘길 수 없다. 2^19승이 500000을 넘기기 때문에
        // BFS 탐색으로 오리의 점프를 기록을 visitedA[위치][일수]에 저장한다.
        // BFS 탐색으로 육리의 점프를 기록 visitedB[위치][일수]에 기록하면서 오리도 같은 일수에 방문한 곳인지 확인한다.
        // visitedA = new int[500001][20];
        // visitedB = new int[500001][20];

        // for(int i=0; i <500001; i++) {
        //     Arrays.fill(visitedA[i], -1);
        //     Arrays.fill(visitedB[i], -1);
        // }

        // solveA();
        // solveB();

        // System.out.println(ans);
 
        // 2) DFS와 비트마스킹으로 풀기
        // 두 오리 A, B 사이의 거리를 L이라 하자. (A의 위치 < B의 위치 라고 생각합니다.)
        // 두 오리의 움직임은 다음 4가지와 같습니다.
        // 1) A 가 왼쪽 B가 왼쪽
        // 2) A가 오른쪽 B가 오른쪽
        // 3) A가 오른쪽 B가 왼쪽
        // 4) A가 왼쪽 B가 오른쪽
        // 움직임 1), 2) 의 경우 L 값이 유지됩니다.
        // 움직임 3) 의 경우 L 값이 줄어듭니다.
        // 움직임 4) 의 경우는... 딱히 생각 안해줘도 됩니다....
        //
        // 각 오리는 n일차에 2^(n-1) 만큼 뛰므로, B-A가 홀수일 경우에는 A, B가 만날 수 없음
        // 오리가 가까워 질때는 2*2^(n-1) = 2^n 만큼 가까워 짐
        //
        // 예를 들어 두 오리 사이의 거리가 L = 11 인 경우를 생각해보면,
        // 11을 이진법으로 나타내면 1011 입니다.
        // 즉 1일차, 2일차에 가까워지고(움직임 3), 3일차에 거리를 유지하며(움직임 1or2), 4일차에 다시 가까워 지면 됩니다.
        // 이를 생각하면, 주어진 L에서,
        // 가까워 져야 하는 일차는 신경 쓸 필요 없이,
        // 거리를 유지해야 하는 날에서 움직임 1) 혹은 2) 가 주어진 땅 내에서 가능한지만 확인하면 됩니다.
        // 이를 DFS 함수로 확인하면 됩니다.

        L = Math.max(A,B) - Math.min(A,B);
        
        // 거리가 홀수일 때
        if(L % 2 != 0){
            System.out.println(-1);
            return;
        }
        
        int cnt = 0;
        int temp = L;
        temp = 11;
        while(temp > 0){
            temp /= 2;
            cnt++;
        }
        ans = cnt - 1;

        if(!DFS(Math.min(A,B), Math.max(A,B), 1)) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    public static boolean DFS(int a, int b, int date){
        int now = 1 << date;
        if(date > ans) return true;
    
        if((now & L) != 0){
            if(DFS(a + now / 2, b - now / 2, date + 1)) return true;
        }
        else{
            if((a - now / 2) > 0) 
                if(DFS(a - now / 2, b - now / 2, date + 1)) return true;
            if((b + now / 2) < N + 1) 
                if(DFS(a + now / 2, b + now / 2, date + 1)) return true;
        }
        return false;
    }

    // public static void solveA() {
    //     Queue<int[]> q = new LinkedList<>();
    //     visitedA[A][0] = 0;
    //     q.offer(new int[] {A, 0});

    //     while(!q.isEmpty()) {
    //         int[] loc = q.poll();
    //         int cur = loc[0];
    //         int day = loc[1];
    //         int jump = (1 << day);
    //         int next;
    //         // 왼쪽
    //         next = cur - jump;
    //         if(next >= 1 && next <= N) {
    //             if(visitedA[next][day + 1] == -1) {
    //                 visitedA[next][day + 1] = day + 1;
    //                 q.offer(new int[] {next , day + 1});
    //             }
    //         }
    //         // 오른쪽
    //         next = cur + jump;
    //         if(next >= 1 && next <= N) {
    //             if(visitedA[next][day + 1] == -1) {
    //                 visitedA[next][day + 1] = day + 1;
    //                 q.offer(new int[] {next , day + 1});
    //             }
    //         }
    //     }
    // }

    // public static void solveB() {
    //     Queue<int[]> q = new LinkedList<>();
    //     visitedB[B][0] = 0;
    //     q.offer(new int[] {B, 0});

    //     while(!q.isEmpty()) {
    //         int[] loc = q.poll();
    //         int cur = loc[0];
    //         int day = loc[1];
    //         int jump = (1 << day);
    //         int next;

    //         if(visitedA[cur][day] != -1 && visitedA[cur][day] == visitedB[cur][day]) {
    //             ans = day;
    //             return;
    //         }

    //         // 왼쪽
    //         next = cur - jump;
    //         if(next >= 1 && next <= N) {
    //             if(visitedB[next][day + 1] == -1) {
    //                 visitedB[next][day + 1] = day + 1;
    //                 q.offer(new int[] {next , day + 1});
    //             }
    //         }
    //         // 오른쪽
    //         next = cur + jump;
    //         if(next >= 1 && next <= N) {
    //             if(visitedB[next][day + 1] == -1) {
    //                 visitedB[next][day + 1] = day + 1;
    //                 q.offer(new int[] {next , day + 1});
    //             }
    //         }
    //     }
    // }

}
