// 1509.팰린드롬 분할
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DP_Palindrome_divide {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] dp;
    static String str;
    static int N;
    static int[] dp_div;

    public static void main(String[] args) throws NumberFormatException, IOException {
        str = br.readLine();
        N = str.length();
        dp = new boolean[N+1][N+1];
        dp_div = new int[N+1];
        Arrays.fill(dp_div, 2*N);

        palindrome();
        palindrome_divide();

        System.out.println(dp_div[N]);
    }

    public static void palindrome() {
        // 길이가 1일때 계산
        for(int i = 1; i <= N; i++)
            dp[i][i] = true;

        // 길이가 2일때 계산
        for(int i = 1; i <= N - 1; i++)
            if(str.charAt(i - 1) == str.charAt(i)) dp[i][i + 1]= true;

        // 길이가 3 이상일 때
        for(int i = 2; i < N; i++){
            for(int j = 1; j <= N - i; j++){
                if(str.charAt(j - 1) == str.charAt(j + i -1) && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }

    public static void palindrome_divide() {
        // dp_div[n]는 1부터 N까지의 분할의 개수의 최솟값
        dp_div[0] = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= i; j++) {
                if(dp[j][i]) {
                    dp_div[i] = Math.min(dp_div[j-1] + 1, dp_div[i]);
                }
            }
        }         
    }

}