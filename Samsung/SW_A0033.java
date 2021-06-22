import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//기출A-0033. 유니콘
public class SW_A0033 {
    static int T, N, R, C, U, L; 
    static int[] dr = {-1, 1, -1, 1, -2, -2, 2, 2};
    static int[] dc = {-2, -2, 2, 2, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            R = Integer.parseInt(stz.nextToken());
            C = Integer.parseInt(stz.nextToken());
            U = Integer.parseInt(stz.nextToken());
            L = Integer.parseInt(stz.nextToken());
            System.out.println("#" + test_case + " " + (bfs(1) - bfs(3)));
        }
    }

    static int bfs(int cnt) {
        int[][] map = new int[N+1][N+1];
        Queue<Position> queue = new LinkedList<>();

        map[R][C] = 1;
        queue.add(new Position(R, C));

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.r == U && current.c == L)
                return map[current.r][current.c];

            for (int i = 0; i < 8; i++) {
                for(int c = 1; c <= cnt; c++) {
                    int nextR = current.r + dr[i] * c;
                    int nextC = current.c + dc[i] * c;

                    if (nextR > 0 && nextR <= N && nextC > 0 && nextC <= N) {
                        if(map[nextR][nextC] == 0) {
                            map[nextR][nextC] = map[current.r][current.c] + 1;
                            queue.add(new Position(nextR, nextC));
                        }
                    }
                }
            }

        }
        return 1;
    }
}

class Position {
    int r;
    int c;

    Position(int r, int c) {
        this.r = r;
        this.c = c;
    }
}