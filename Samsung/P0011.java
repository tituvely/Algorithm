import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P0011 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N,K;
    static int[] f = new int[1000001];
    static int[] visit = new int[1000001];
    static long[] dp = new long[1000001];
    static final int MOD = 1000000007;

    //사이클 노드 개수와 종류 K개에 따른 경우의 수 
    static void setDP(int n, int k){
        dp[2]=(long)k*(k-1)%MOD;
        dp[3]=(long)dp[2]*(k-2)%MOD;
        for(int i=4;i<=n;i++) {
            dp[i]=(long)dp[i-2]*(k-1)%MOD;
            dp[i]+=(long)dp[i-1]*(k-2)%MOD;
        }
    }

    static long pow(int k, int n) {     //k^n % MOD
        if(n==0) return (long)1;
        if(n==1) return (long)k;
        long ret=k;

        for(int i=2; i<=n; i++) {
            ret*=k;
            ret%=MOD;
        }
        return ret;
    }

    // <선형> = K * (K-1)^(n-1)
    
    // <사이클>
    // K * (K-1)^(n-1)
    // 1개: 1, 2개: 2, 3개: K(K-1)(K-2)
    // 양 끝이 다른 경우: (n-1) 사이클 개수 * (k-2)
    // 양 끝이 같은 경우: (n-2) 사이클 개수 * 1 * (k-1)
    // n > 3: D[n] = D[n-1]*(k-2) + D[n-2]*(k-1)

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());
        
        for(int t=1; t<=T; t++) {
            stz = new StringTokenizer(bf.readLine());
            N=Integer.parseInt(stz.nextToken());
            K=Integer.parseInt(stz.nextToken());

            stz = new StringTokenizer(bf.readLine());
            for(int i=1; i<=N; i++) {
                f[i] = Integer.parseInt(stz.nextToken());
                visit[i]=0;
            }

            setDP(N, K);

            int vS,vE,vIdx; //v Start, v End, v Index
            vS=vE=vIdx=1;
            long ans = 1;

            for(int i=1; i<=N; i++) {
                if(visit[i]!=0) continue;

                vS=vE;
                vIdx=i;
                while(visit[vIdx]==0) {
                    visit[vIdx]=vE++;
                    vIdx=f[vIdx];
                }

                //사이클인 부분
                int cycle = 0;
                if(vS<=visit[vIdx]) {
                    cycle = vE - visit[vIdx];
                    if(cycle==1) {
                        ans*=K;
                        ans%=MOD;
                    } else if(cycle%2==0 && K==1) {
                        // 사이클 노드가 짝수면 색 최소 2개 필요
                        ans=0; 
                        break;
                    } else if(cycle%2==1 && K<=2) {
                        // 사이클 노드가 홀수면 색 최소 3개 필요
                        ans=0;
                        break;
                    } else {
                        ans*=dp[cycle];
                        ans%=MOD;
                    }
                }

                //사이클이 아닌 부분
                ans*=pow(K-1, vE-vS-cycle)%MOD;
                ans%=MOD;
            }
            
            System.out.println("#"+t+" "+ans);
        }

       
        
    }


}
