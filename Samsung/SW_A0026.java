// 연습A-0026. 지은이가 지은 집
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SW_A0026 {
    static int T, X, N;
    static int[] L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        // 정렬 후 투 포인터
        for(int t = 1; t <= T; t++) {
            X = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            L = new int[N];
            
            for(int i = 0; i < N; i++) {
                L[i] = Integer.parseInt(br.readLine());
            }

            X = X * 10000000;
            Arrays.sort(L);

            int l = 0, r = N - 1;
            while(l < r) {
                if(L[l] + L[r] == X) {
                    System.out.println("#" + t + " yes " + L[l] + " " + L[r]);
                    return;
                }
                l++;
                r--;
            }
            System.out.println("#" + t + " danger");
        }
    }
}
