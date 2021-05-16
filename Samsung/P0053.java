import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P0053 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static double[][] dp = new double[1005][1005];
    static int w,b;

    static void input() throws IOException {
        stz = new StringTokenizer(bf.readLine());
        w = Integer.parseInt(stz.nextToken());
        b = Integer.parseInt(stz.nextToken());

        for(int i=0; i<=w; i++) {
            for(int j=0; j<=b; j++) {
                dp[i][j] = 0;
            }
        }
    }

    static void exec() {
        dp[0][0] = 1;
        //dp[i][j] = i개의 백을 보내고, j개의 흑을 보냈을 확률
        for(int i=0; i<=w; i++) {
            for(int j=0; j<=b; j++) {
                if(i==0 && j==0) continue;

                if(i!=0) {
                    int wLeft = w-i+1;
                    int bLeft = b-j;

                    //dp[i][j] += dp[i-1][j] * (wLeft/(wLeft+bLeft)) * ((wLeft-1)/wLeft*bLeft-1);
                    double temp = dp[i-1][j] * wLeft * (wLeft-1) / ((wLeft+bLeft) * (wLeft+bLeft-1));
                   
                    dp[i][j] += Double.isNaN(temp)?0:temp;
                }

                if(j!=0) {
                    int wLeft = w-i;
                    int bLeft = b-j+1;

                    double temp = dp[i][j-1] * bLeft * (bLeft-1) / ((wLeft+bLeft)*(wLeft+bLeft-1));

                    dp[i][j] += Double.isNaN(temp)?0:temp;
                }

                if(i!=0&&j!=0) {
                    int wLeft = w-i+1;
                    int bLeft = b-j+1;

                    double temp = 2*dp[i-1][j-1]*wLeft*bLeft/((wLeft+bLeft)*(wLeft+bLeft-1));

                    dp[i][j] += Double.isNaN(temp)?0:temp;
                }
            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());
        for(int t=1;t<=T;t++) {
            input();
            exec();
            double ans1, ans2, ans3;
            if(w!=0) {
                ans1 = dp[w-1][b];   //백이 하나 남은 경우
            } else {
                ans1 = 0;
            }

            if(b!=0) {
                ans2 = dp[w][b-1];   //흑이 하나 남은 경우
            } else {
                ans2 = 0;
            }
            
            ans3 = dp[w][b];

            System.out.println(ans1 + " " + ans2 + " " + ans3);
            
        }
    }
}
