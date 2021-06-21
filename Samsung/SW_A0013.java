//기출A-0013. 숫자 배치하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_A0013 {
    static int N;
    static int score[][];
    static boolean visited[];
    static int MaxScore;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            score = new int[N][N];
            for(int i = 0; i < N; i++) {
                stz = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    score[i][j] = Integer.parseInt(stz.nextToken());
                }
            }
            
            MaxScore = 0;
            visited = new boolean[N];
            dfs(0, 0);
            System.out.print(MaxScore);
        }
    }

    public static void dfs(int row, int sum) {
        if(row == N) {
            MaxScore = Math.max(MaxScore, sum);
            return;
        } else {
            for(int col = 0; col < N; col++) {
                if(visited[col] == false) {
                    sum = sum + score[row][col];
                    visited[col] = true;
                    row = row + 1;
                    dfs(row, sum);
                    row = row - 1;
                    visited[col] = false;
                    sum = sum - score[row][col];
                }
            }
        }
        return;
    }
}
