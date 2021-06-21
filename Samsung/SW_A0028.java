// A0028. 나누기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW_A0028 {
    static int arr[][];
    static int zero, one;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N;
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            zero = 0; 
            one = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            split(0, 0, N);
            System.out.println("#" + test_case + " " + zero + " " + one);
        }
    }

    public static void split(int x, int y, int N) {
        //기저조건
        if(isFinished(x, y, N)) {
            if(arr[x][y] == 0) zero++;
            else one++;

            return;
        }

        N = N/2;
        split(x, y, N);
        split(x + N, y, N);
        split(x, y + N, N);
        split(x + N, y + N, N);
    }

    public static boolean isFinished(int x, int y, int N) {
        boolean flag = true;
        int mark = arr[x][y];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[x + i][y + j] != mark) {
                    flag = false;
                    return flag;
                }
            }
        }

        return flag;
    }

}
