// 기출P-0033. 미운오리새끼 찾기
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_P0033 {
    static int T, N, kMAX, excludedNumber;
    static int[] input, leftToRight, rightToLeft;
    static final int MAX = 2000000001;  // 20억1

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            stz = new StringTokenizer(br.readLine());

            kMAX = excludedNumber = 0;
            input = new int[N + 1];
            leftToRight = new int[N + 2];
            rightToLeft = new int[N + 2];

            for(int i = 1; i <= N; i++) {
                input[i] = Integer.parseInt(stz.nextToken());
            }

            // 배열을 N + 2의 크기로 잡고 인덱스를 2부터 시작한 이유는
            // i - 1, i + 1 인덱스를 사용하기 때문에

            // 0은 어떤 수와 곱해도 0이기 때문에 0의 약수는 정의상 모든 정수가 된다.
            // gcd(n, 0) = n

            // left -> 방향 gcd 구하기
            leftToRight[1] = input[1];
            for(int i = 2; i <= N; i++) {
                leftToRight[i] = gcd(leftToRight[i - 1], input[i]);
            }

            // right <- 방향 gcd 구하기
            rightToLeft[N] = input[N];
            for(int i = N - 1; i > 0; i--) {
                rightToLeft[i] = gcd(rightToLeft[i + 1], input[i]);
            }

            int maxK = 0, minK = MAX;
            for(int i = 1; i <= N; i++) {
                // input[i]를 제외하고 구한 gcd값
                int exceptGcd = gcd(leftToRight[i-1], rightToLeft[i+1]);

                // minGCD = 하나 빼고 구한 gcd중 최솟값
                minK = Math.min(exceptGcd, minK);

                if(maxK < exceptGcd) {
                    maxK = exceptGcd;
                    kMAX = maxK;
                    excludedNumber = input[i];
                }
            }

            // 하나 빼고 구한 gcd의 최솟값과 최댓값이 같다는 것은
            // 어떤 수를 제외하더라도 제외된 수의 약수가 아니면서 
            // 나머지 수들이 공약수인 것을 찾을 수 없다는 것
            // K의 배수가 아닌 1개를 제외하고 나머지 모든 수가 K의 배수가 되도록 하는 특별한 수를 선택하는 것이 불가능한 경우
            if(minK == maxK) {
                System.out.println(minK);
                bw.write("#" + t + " 0 0");   
            } else {
                bw.write("#" + t + " " + kMAX + " " + excludedNumber);   
            }

            bw.newLine();

        }
    
        bw.flush();
    }    

    static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
