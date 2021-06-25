// 기출A-0030. 어떤 트리
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_A0030 {
    static int T, N;
    static int[] heap;
    static int depth, index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            heap = new int[N + 1];

            stz = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                heap[i] = Integer.parseInt(stz.nextToken());
            }

            delete();
            bw.write("#" + t + " " + depth + " " + index);
            bw.newLine();
        }
        bw.flush();
    }

    public static void delete() {
        depth = 0;
        index = 1;

        int num = heap[N];
        heap[1] = num;
        
        int size = N - 1;

        int pos = 1;
        while ((pos * 2) <= size) {
            int max = heap[pos * 2];
            int maxPos = pos * 2;
            
            // right child가 있는 경우
            if(((pos * 2 + 1) <= size) && max < heap[pos * 2 + 1]) {
                max = heap[pos * 2 + 1];
                maxPos = pos * 2 + 1;
            }
            
            // 종료 조건을 만났을 때의 index로 현재 위치를 구할 수 있음
            if(heap[pos] > max) {
                break;
            }
            
            int temp = heap[pos];
            heap[pos] = heap[maxPos];
            heap[maxPos] = temp;
            pos = maxPos;
        }

        while(pos >= index) {
            index <<= 1;
            depth++;
        }

        depth--;
        index >>= 1;
        index = pos - index + 1;
    }

}
