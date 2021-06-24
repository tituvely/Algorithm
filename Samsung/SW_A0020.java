// 연습A-0020. 탑
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW_A0020 {
    static int T, N;
    static long MOD = 1000000007;
    static long result;
    static Stack<int[]> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            result = 0;
            stack = new Stack<>();

            stz = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                int[] top = new int[2];
                top[0] = i;
                top[1] = Integer.parseInt(stz.nextToken());

                while(!stack.isEmpty()) {
                    int[] current = stack.peek();
                    // 왼쪽에 있는 탑 중 자신보다 작거나 같은 탑은 제거
                    if(current[1] <= top[1]) {
                        stack.pop();
                    } else {   
                        result += current[0];
                        result %= MOD;
                        break;
                    }
                }

                stack.push(top);
            }

            if(result == 0) result = -1;
            else result %= MOD;
        
            bw.write("#" + t + " " + result);
            bw.newLine();
        }
        bw.flush();
    }
}
