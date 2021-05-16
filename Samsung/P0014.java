import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 서로소 찾기

public class P0014 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int MOD=10000003;

    static int GCD(int a, int b) {
        return b==0?a:GCD(b,a%b);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());
        for(int test_case=0; test_case<T; test_case++) {
            int N = Integer.parseInt(bf.readLine());
            stz = new StringTokenizer(bf.readLine());
            int[] sequence = new int[N];
            int D[] = new int[100005];  //D[i]에는 i가 최대공약수일 때인 상황이 count됨
            for(int i=0; i < N; i++) {
                sequence[i] = Integer.parseInt(stz.nextToken());
            }

            Arrays.sort(sequence,0,N);
            Arrays.fill(D,0);

            for(int i=0; i<N; i++) {
                D[sequence[i]]=1;       //2,3,4,5   ->5차례일 때, 2,3,4는 최대공약수5불가 {5}
                for(int j=0;j<sequence[i];j++) {
                    if(D[j]>0) {
                        int gcd= GCD(j, sequence[i]);
                        D[gcd] = (D[gcd]+D[j]) % MOD;
                    }
                }
            }
            System.out.println(D[1]);
        }
    }
}
