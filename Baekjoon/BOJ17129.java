// 17129. 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ17129 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int n, m;
    static int a[][];
    static boolean visited[][];
    static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        stz = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());

        a = new int[n][m];
        visited = new boolean[n][m];

        String str;
        int row = -1, col = -1;
        for(int i = 0; i < n; i++) {
            str = br.readLine();
            for(int j = 0; j < m; j++) {
                a[i][j] = str.charAt(j) -'0';
                if(a[i][j] == 2) {
                    row = i;
                    col = j;
                }
            }
        }

        int ans = bfs(row, col);
        if(ans == -1 ) {
			System.out.println("NIE");
		} else {
			System.out.println("TAK");
            System.out.println(ans);
		}

    }

    public static int bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {row, col, 0});

        while(!q.isEmpty()) {
            int[] loc = q.poll();

            for(int d = 0; d < 4; d++) {
                int nrow = loc[0] + dr[d];
                int ncol = loc[1] + dc[d];
                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || visited[nrow][ncol] || a[nrow][ncol] == 1) continue;
                if(a[nrow][ncol] == 3 || a[nrow][ncol] == 4 || a[nrow][ncol] == 5) return loc[2] + 1;
                visited[nrow][ncol] = true;
                q.offer(new int[] {nrow, ncol, loc[2] + 1});
            }

        }
        return -1;
    }

}
