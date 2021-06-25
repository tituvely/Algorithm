// 교육 P-0013. 나무
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_P0013 {
    static int T, N, M, Li, Ri;
    static int[] tree;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());

            max = N + 1;
            for(int i = 0; i < M; i++) {
                stz = new StringTokenizer(br.readLine());
                Li = Integer.parseInt(stz.nextToken());
                Ri = Integer.parseInt(stz.nextToken());

                // 전체 길의 아름다움 값은 max(min(각 구간의 아름다운 값))
                max = Math.min(max, Ri - Li + 1);
            }
            bw.write("#" + t + " " + max);
            bw.newLine();

            // 구간의 아름다움 값이 커지려면 구간의 나무 높이를 0부터 연속으로 채우면 됨
            // 어느 위치에서 최소구간 길이(max)를 잡아도 [0, 1, 2, ... , max - 1] 이면
            // '최소구간길이 = 전체 길의 아름다움 값'을 만족한다.
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                sb.append(i % max).append(" ");
            }
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.flush();
    }
}