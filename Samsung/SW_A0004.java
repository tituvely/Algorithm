// 기출A-0004. 전등켜기
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SW_A0004 {
    static int T, N;
    static char[][] map;
    static int[][] xCount;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N+1][N+1];
            xCount = new int[2][N+1];
            cnt = 0;
        
            for(int i = 1; i <= N; i++) {
                String str = br.readLine();
                for(int j = 1; j <= N; j++) {
                    map[i][j] = str.charAt(j - 1);
                    if(map[i][j] == 'X') {
                        xCount[0][i]++;
                        xCount[1][j]++;
                    }
                }
            }

            int[][] index = new int[N+1][2];
            // 행과 열의 X개수가 같은 곳을 찾아야 함
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(map[i][j] == 'O' && xCount[0][i] == xCount[1][j]) {
                        cnt++;
                        index[cnt][0] = i;
                        index[cnt][1] = j;
                    }
                }
            }

            bw.write("#" + t + " " + cnt);
            for(int i = 1; i <= cnt; i++) {
                bw.write(" " + index[cnt][0] + " " + index[cnt][1]);
            }
            bw.newLine();
        }
        bw.flush();
    }
    
}

