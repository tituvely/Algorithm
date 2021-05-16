import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최대공약수(GCD: Greatest Common Divisor) 구하기

public class P0033 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static final int MAX = 1000000;
    static int N;
    static int[] left = new int[MAX + 1];
    static int[] right = new int[MAX + 1];
    static int[] list = new int[MAX + 1];

    static void input() throws NumberFormatException, IOException {
        N = Integer.parseInt(bf.readLine());
        stz = new StringTokenizer(stz.nextToken());
        for(int i=1; i<=N; i++) {
            list[i] = Integer.parseInt(stz.nextToken());
        }
        left[1] = list[1];
        right[N] = list[N];
        for(int i=2; i<=N; i++) {
            left[i] = gcd(left[i-1], list[i]);
        }
        for(int i=N-1; i>0; i--) {
            right[i] = gcd(right[i+1], list[i]);
        }
        int x = 0;
        int max = 0;
        int excl = 0;

        if(list[N]%left[N-1]!=0) {
            max = x;
            excl = list[N];
        }

        if(list[1]%right[2]!=0) {
            if(max<right[2]) max=right[2]; excl=list[1];
        }

        for(int i=2; i<N;i++) {
            x = gcd(left[i-1], right[i+1]);
            if(max < x){
                max = x;
                excl = list[i];
            }
        }

    }

    //유클리드 호제법을 이용한 GCD 구하기
    public static int iterative_gcd(int a, int b) {
        int temp;

        while(b != 0) {
            temp = a % b;
            a = b;
            b= temp;
        }

        return a;
    }

    public static int recursive_gcd(int a, int b) {
        if(b == 0) return a;
        return recursive_gcd(b, a%b);
    }

    public static int gcd(int a, int b) {
        return b==0? a : gcd(b, a%b); 
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());
        for(int t=1; t<T; t++) {
            input();
        }
        
    }
}
