//1509. 팰린드롬 분할
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DP_Palindrome_divide {
    static int dp[][];
    // 시작점 1 -> N까지의 숫자까지의 팰린드롬 분할 최소 갯수
    static int dp_div[];
    static int n;
    static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();
        dp = new int[n+1][n+1];
        dp_div = new int[n+1];

        palindrome();
        palindrome_divide();

        System.out.println(dp_div[n]);
    }

    public static void palindrome() {
        // 길이가 1일때 계산
        for(int i = 1; i <= n; i++)
            dp[i][i] = 1;

        // 길이가 2일때 계산
        for(int i = 1; i <= n - 1; i++)
            if(str.charAt(i-1) == str.charAt(i+1-1)) dp[i][i + 1]= 1;

        // 길이가 3 이상일 때
        for(int i = 2; i < n; i++){
            for(int j = 1; j <= n - i; j++){
                if(str.charAt(j-1) == str.charAt(j+1-1) && dp[j + 1][j + i - 1]==1)
                    dp[j][j + i] = 1;
            }
        }
    }

    public static void palindrome_divide() {
        dp_div[0] = 0;
        dp_div[1] = 1;
        // dp_div[2]는 1 또는 2
        // dp_div[2] = Math.min(dp_div[0] + dp[1][2], dp_div[1] + 1);

        for(int i = 0; i < n-1 ; i++) {
            if(dp[i+1][n]==1) {
                dp_div[n] = Math.min(dp_div[i] + dp[i+1][n], dp_div[i-1] + 1);
            }
        } 
    }
}

