import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P0034 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    
    static StringTokenizer stz;
    static String K;
    static int L;
    static boolean[] noPrime = new boolean[1000001];
    static int result;

    public static void input() throws IOException {
        stz=new StringTokenizer(bf.readLine());
        K=stz.nextToken();
        L=Integer.parseInt(stz.nextToken());
    }

    public static void exec() {
        result = 0;
        int len = K.length();
        for(int i=2;i<L;i++){
            //k가 l이랑 나눠지면 얘는 취약하다
            if(noPrime[i]) continue;
            System.out.println("소수 "+i);
            int n=0;
            for(int j=0;j<len;j++) {        //123 n=1 n=10+2=12 n=120+3=123
                n=(n*10)+(K.charAt(j)-'0');
                // System.out.println(j + " " + n);
                n = n%i;
                System.out.println(n);
            }
            if(n==0){
                result=i;
                System.out.println(result);
                return; //K가 i로 나뉘어진다-> i는 L보다 작으니까 K는 취약한 암호다.
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        Arrays.fill(noPrime, false);

        // 소수만들기
        for(int i=2;i<=1000000;i++) {
            if(noPrime[i]) continue;
            for(int j=i+i;j<=1000000;j=j+i) {
                noPrime[j] = true;       
            }
        }

        int T=Integer.parseInt(bf.readLine());

        for(int i=0; i<T; i++) {
            input();
        }
        exec();
    }
}
