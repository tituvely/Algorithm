//교육P-0020. 달리기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_P0020 {
    static int T, N;
    static int[] runCapa;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            stz = new StringTokenizer(br.readLine());
            runCapa = new int[N];
            for(int i = 0; i < N; i++) {
                runCapa[i] = Integer.parseInt(stz.nextToken());
            }
            System.out.println("#"+ t + " " + reverse());
        }
    }    

    public static int reverse() {
        int cnt = 0;
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(runCapa[j] < runCapa[i])
                    cnt++;
            }  
        }
        return cnt;
    }

}