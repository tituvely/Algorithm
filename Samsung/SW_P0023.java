// 교육P-0023. 의미있는 문장 찾기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_P0023 {
    static int T, N, K;
    static int[] word;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            stz = new StringTokenizer(br.readLine());
            word = new int[N];
            cnt = new int[N + 1];
            for(int i = 0 ; i < N; i++) {
                word[i] = Integer.parseInt(stz.nextToken());
            }

            System.out.println("#" + t + " " + meaningful());
        }
    }   
    
    public static int meaningful() {
        int l = 0, r = K - 1;
        for(int i = l; i <= r; i++) {
            if(cnt[word[i]] == 0) {
                cnt[0]++;
            }
            cnt[word[i]]++;
        }

        while(true) {
            if(cnt[0] == K) {
                if(cnt[word[l]] == 1) {
                    return r - (l - 1); 
                } else {
                    cnt[word[l]]--;
                    l++;
                }
            } else if(cnt[0] < K) {
                r++;
                if(r >= N)  return -1;
                
                if(cnt[word[r]] == 0) {
                    cnt[0]++;
                }
                cnt[word[r]]++;
            }
        }

    }
}
