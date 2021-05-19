import java.io.BufferedReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import jdk.internal.org.jline.utils.InputStreamReader;

//2169. 로봇 조종하기


public class BOJ2169 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M;
    static int weight[][];
    static int dp[][];

    public static void main(String[] args) {
        stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        M = Integer.parseInt(stz.nextToken());
        weight = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            stz = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                weight[i][j] = Integer.parseInt(stz.nextToken());;
            }
        }

        int [][] visited = new int[N+1][M+1];

        System.out.println(findMaxValue(0, 0, visited));
    }

    public static int findMaxValue(int n, int m, int[][] visited) {
        // 로봇은 왼쪽, 오른쪽, 아래쪽으로만 이동할 수 있다.
        // 이미 방문한 지점일 경우 값을 갱신하지 않는다.

        // 시작점
        if(n == 0 && m == 0) dp[0][0] = weight[0][0];

        // 왼쪽으로 이동
        if(dp[])
        
    }
}
