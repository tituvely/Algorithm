// 교육 A-0008. Anagram
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_A0008 {
    static int T, N;
    static long cnt;
    static String[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            S = new String[N];
            cnt = 0;

            for(int i = 0; i < N; i++) {
                S[i] = br.readLine();
            }

            cnt = solve1();

            bw.write("#" + t + " " + cnt);
            bw.newLine();
        }
        bw.flush();
    }

    public static long solve1() {
        // 1. 정렬 풀이
        // 문자열을 알파벳 순서대로 정렬해서 넣어줌
        for(int i = 0; i < N; i++) {
            char[] charArr = S[i].toCharArray();
            Arrays.sort(charArr);
            S[i] = String.valueOf(charArr);
        }   

        // S 문자열 배열에서 같은 anagram끼리 순서를 모아줌
        Arrays.sort(S);

        // 페어가 되는 쌍의 수를 출력
        long result = 0;
        int count = 1;
        for(int i = 1; i < N; i++) {
            if(S[i].equals(S[i - 1])) {
                count++;
            } else {
                count = 1;
            }
            result += (count - 1);
        }

        return result;
    }

    public static long solve2() {
        // 2. O(N) 풀이
        int[][] alphabetCounter = new int[N][26];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < S[i].length(); j++) {
                char ch = S[i].charAt(j);
                alphabetCounter[i][ch-'a']++;
            }
        }   

        long result = 0;
        boolean[] visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            long count = 0;
            if(visited[i]) continue;

            for(int j = i + 1; j < N; j++) {
                if(visited[j]) continue;
                boolean isEquals = true;
                for(int k = 0; k < 26; k++) {
                    if(alphabetCounter[i][k] != alphabetCounter[j][k]) {
                        isEquals = false;
                        break;
                    }
                }
                if(isEquals) {
                    visited[j] = true;
                    count++;
                }
            }

            // n+1C2 = N (N + 1) / 2
            result += count * (count + 1) / 2;
        }

        return result;
    }

}
