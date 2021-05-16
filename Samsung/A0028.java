//A0028. 제품의 일련번호
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A0028 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N;
    static String first, second; 
    static boolean used[] = new boolean[20];


    static int howManyUsed(int x) {
        int ret = 0;
        for(int i=0; i<x; i++) {
            if(used[i]) ret++;
        }

        return ret;
    }

    static long fact(int n) {   // factorial 함수
        long ret = n;
        for(int i=n-1; i>=2; i--) {
            ret*=i;
        }
        return ret;
    }

    static long func(String s) {
        Arrays.fill(used, false);

        long ret = 0;

        for(int i=0; i<N; i++){
            int ss=s.charAt(i) - 'a' - howManyUsed(s.charAt(i)-'a');
            ret+=(fact(N-i-1)*ss);      //(알파벳 인덱스 - 앞에서 사용된 자기보다 작은 애들 - 1) * 자리수팩토리얼
            used[s.charAt(i)-'a'] = true; 
        }

        return ret;
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());

        for(int test_case=0; test_case<T; test_case++) {
            stz = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(stz.nextToken());
            first = stz.nextToken();
            second = stz.nextToken();

            long ans = Math.abs(func(first)-func(second) - 1);

            System.out.println(ans);
        }
    }

}
