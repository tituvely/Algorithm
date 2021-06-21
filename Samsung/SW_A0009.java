//기출A-0009. 정예병사
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW_A0009 {
    static final int MAXN = 100000;

    static int T, N, Ans;
    static int[][] Data = new int[MAXN + 1][2];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            
            for(int i = 0; i < N; i++) {
                stz = new StringTokenizer(br.readLine());
                Data[i][0] = Integer.parseInt(stz.nextToken());
                Data[i][1] = Integer.parseInt(stz.nextToken());
            }

            //공격력 순위로 오름차순 정렬
            Arrays.sort(Data, 0, N, new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            Ans = 0;
            int minD = N + 1;   //방어력 순위
            for (int i = 0; i < N; i++) {
                if(Data[i][1] < minD) {
                    Ans++;
                    minD = Data[i][1];
                }
            }
            
            bw.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(Ans));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
