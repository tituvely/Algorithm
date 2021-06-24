// 기출A-0028. 제품의 일련번호2
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWA0028 {
    static int T, N;
    static String a, b;
    static long[] fac = new long[19];
    static boolean isUsed[] = new boolean[19];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        factorial();

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            a = stz.nextToken();
            b = stz.nextToken();

            bw.write("#" + t + " " + (Math.abs(getOrder(a) - getOrder(b)) - 1));
            bw.newLine();
        }
        bw.flush();
    }

    public static void factorial() {
        fac[0] = fac[1] = 1;
        int i = 2;
        while(i <= 18) {
            fac[i] = i * fac[i-1];
            i++;
        }
    }

    public static long getOrder(String str) {
        Arrays.fill(isUsed, false);

        long result = 0;
        int alphaOrder = 0;
        int count = 0;
        for(int i = 0; i < N; i++) {
            // 입력된 글자보다 앞에 있는 글자가 몇 개인지
            alphaOrder = (int)(str.charAt(i) - 'a');
            count = countChar(alphaOrder);

            // 입력된 글자보다 앞에 있는 글자의 수 * 남은 자리의 수!
            result += count * fac[N - i - 1];
            isUsed[alphaOrder] = true;
        }

        result += 1;    // 앞에 있는 단어의 개수 + 1 = 자기 자신의 순서
        return result;
    }

    // 알파벳 순서로 더 앞에 있으면서, 사용하지 않은 글자를 반환
    public static int countChar(int order) {
        int count = 0;
        for(int i = 0; i < order; i ++) {
            if(!isUsed[i]) {
                count++;
            }
        }
        return count;
    }

}
