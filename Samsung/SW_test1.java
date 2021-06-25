// 수료 테스트_1
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_test1 {
    static int T, W, H;
    static char[][] boat;
    static Queue<Position> queue;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            W = Integer.parseInt(stz.nextToken());
            H = Integer.parseInt(stz.nextToken());
            boat = new char[H][W];

            String str;
            int startR = 0, startC = 0;
            for(int i = 0; i < H; i++) {
                str = br.readLine();
                for(int j = 0; j < W; j++){
                    boat[i][j] = str.charAt(j);
                    if(boat[i][j] == 'S') {
                        startR = i;
                        startC = j;
                    }
                }
            }

            bw.write("#" + t + " " + bfs(new Position(startR, startC)));
        }
    
        bw.flush();
    }

    public static int bfs(Position start) {
        int[][] count = new int[H][W];
        queue = new LinkedList<>();

        count[start.r][start.c] = 1;
        queue.offer(start);

        while(!queue.isEmpty()) {
            Position cur = queue.poll();
            int curR = cur.r;
            int curC = cur.c;

            if(boat[curR][curC] == 'E') {
                return count[curR][curC] - 1;
            }

            for(int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if(nextR >= 0 && nextR < H && nextC >= 0 && nextC < W) {
                    if(boat[nextR][nextC] != 'X' && count[nextR][nextC] == 0) {
                        count[nextR][nextC] = count[curR][curC] + 1;
                        queue.offer(new Position(nextR, nextC));
                    }
                }
            }

        }

        return -1;
    }
    
}

class Position {
    int r;
    int c;

    Position() {
    }

    Position(int r, int c) {
        this.r = r;
        this.c = c;
    }
}