// 교육P-0016. 동굴
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_P0016 {
    static int T, N, H;
    static int[] obstacle;
    static int minCnt, minRangeCnt;
    static int[] sukson;
    static int[] jongyusuk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            H = Integer.parseInt(stz.nextToken());
            obstacle = new int[N];
            sukson = new int[H+1];
            jongyusuk = new int[H+1];
            for(int i = 0; i < N; i++) {
                obstacle[i] = Integer.parseInt(br.readLine());
                // 석순과 종유석을 높이별로 카운트
                if(i % 2 == 0) {
                    sukson[obstacle[i]]++;
                } else {
                    jongyusuk[obstacle[i]]++;
                }   
            }

            minCnt = 2 * N;
            for(int i = 1; i <= H; i++) {
                if(minCnt > sukson[i] + jongyusuk[i]) {
                    minCnt = sukson[i] + jongyusuk[i];
                }
            }

            for(int i = 1; i <= H; i++) {
                if(minCnt == sukson[i] + jongyusuk[i]) {
                    minRangeCnt++;
                }    
            }

            System.out.println("#" + t + " " + minCnt + " " + minRangeCnt);
        }
    }
}
