// 기출P-0006. 아름다운 비트 문자열
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_P0006 {
    static int T, N, K;
    static long X;
    static final int MAX = 1000;
    static final long MAX_VALUE = (1L << 60) + 1;   // 2^60 + 1
    static long[][] pascalsTriangle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());

        // 파스칼의 삼각형은 test_case마다 새로 구하지 않고 미리 한번만 구해놓음
        makePascalsTriangle();
        
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            X = Long.parseLong(stz.nextToken());

            StringBuilder sb = new StringBuilder();
            sb.append("#" + t + " ");

            for(int i = 1; i <= N; i++) {
                // n-iCk 안에 X번째 값이 포함이 되면 해당 자리에 0
                if (X <= pascalsTriangle[N-i][K]) {
                    sb.append("0");
                } else {
                    // n-iCk안에 X번째 값이 포함되지 않으면 해당 자리에 1
                    sb.append("1");
                    X = X - pascalsTriangle[N-i][K];
                    // 남은 1의 개수를 -1
                    K = K - 1;
                }
            }

            bw.write(sb.toString());
            bw.newLine();
        }

        bw.flush();
    }


    static void makePascalsTriangle() {
        pascalsTriangle = new long[MAX+1][MAX+1];
        pascalsTriangle[0][0] = 1;
        for(int i = 1; i <= MAX; i++) {
            pascalsTriangle[i][0] = 1;  // nC0 = 1
            for(int j = 1; j <= i; j++) {
                // 터지는 경우를 고려해야 함
                pascalsTriangle[i][j] = Math.min((pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j]), MAX_VALUE);
            }
        }
    }
}
