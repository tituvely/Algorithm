import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P0006 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N,K;
    static long[][] comb = new long[1001][1001];
    static long X;

    static String find(int n, int k, long x) {
        if(n>=1) {
            if(x<=comb[n-1][k]){
                return '0' + find(n-1,k,x);
            } else {
                return '1' + find(n-1,k-1,x-comb[n-1][k]);
            }
        }
        return "";
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        comb[0][0] = 1;
        for(int i=0; i<=1000; i++) {
            comb[i][0] = 1;
            for(int j=1;j<=i;j++) {
                comb[i][j] = comb[i-1][j] + comb[i-1][j-1];
                if(comb[i][j]<0) comb[i][j] = Long.MAX_VALUE;
            }
        }

        int T = Integer.parseInt(bf.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer stz = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            X= Integer.parseInt(stz.nextToken());
            String temp = find(N, K, X);
            System.out.println("#"+t+ " " + temp);
        }
    }

}