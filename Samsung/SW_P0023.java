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
                cnt[word[i]]++;
            }

            System.out.println("#" + t + " " + meaningful());
        }
    }   
    
    public static int meaningful() {
        int minCnt = -1;

        // 전체 단어 종류의 개수가 K보다 작을 경우
        for(int i = 1 ; i <= N; i++) {
            if(cnt[i] != 0) {
                cnt[0]++;       
            }
            if(i == N) {
                if (cnt[0] < K) {
                    return minCnt;
                }
            }
        }

        Arrays.fill(cnt, 0);
        int l = 0, r = K - 1;
        for(int i = l ; i <= r; i++) {
            if(cnt[word[i]] == 0) {
                cnt[0]++;
            } 
            cnt[word[i]]++;
        }

        if(cnt[0] == K) {
            minCnt = K;
            return minCnt;
        } else {
            r++;
        }
        
        while(true) {
            // 단어의 개수가 K보다 작을 경우
            if(r - (l - 1) < K) {
                r++;
            }

            // 끝까지 탐색했을 경우
            if(r > N) {
                return minCnt;
            }

            // 앞 단어와 마지막 단어가 같을 경우 앞의 인덱스를 증가시켜 줌
            if(word[l] == word[r]) {
                l++;
                r++;
                continue;
            }
            // 다를 경우, 마지막 단어가 새로운 단어인지 체크해 줌
            else {
                if(cnt[word[r]] == 0) {
                    cnt[0]++;
                }
                cnt[word[r]]++;
            }
            
            if(cnt[0] == K) {
                minCnt = r - (l - 1);
                return minCnt;
            } else {
                r++;
                if(cnt[word[l]] > 1) {
                    l++;
                }
            }
        }
    }
}
