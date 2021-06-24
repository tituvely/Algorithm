// 연습P-0019. 구간합
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_P0019 {
    static int T, N, Q;
    static int leafCount = 1, leafPointer = 0;
    static long tree[];
    static long totalSum;
    static final long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            Q = Integer.parseInt(br.readLine());

            initTree();

            int q, a, b;
            totalSum = 0;
            for(int i = 0; i < Q; i++) {            
                stz = new StringTokenizer(br.readLine());
                q = Integer.parseInt(stz.nextToken());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());

                query(q, a, b);
            }
            
            bw.write("#" + t + " " + totalSum);
            bw.newLine();
        }
        bw.flush();
    }

    public static void initTree() {
        while(N > leafCount) {
            leafCount <<= 1;
        }

        tree = new long[leafCount * 2];
        leafPointer = leafCount - 1;
        
        for(int i = 1; i <= N; i++) {
            tree[leafPointer + i] = i;
        }
        
        for(int i = leafPointer; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[(i * 2) + 1];
        } 
    }

    static void query(int q, int a, int b) {
        if(q == 0) update(a, b);
        else if(q == 1) getSum(a, b);
    }

    static void getSum(int left, int right) {
        long sum = 0L;
        left += leafPointer;
        right += leafPointer;

        while(left <= right) {
            // left가 right child이면 + 1
            if(left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            // right가 left child이면 + 1
            if(right % 2 == 0) {
                sum += tree[right];
                right--;
            }

            // left가 left child, right가 right child이므로 나누기 2
            left /= 2;
            right /= 2;
        }

        // (a + b) % m = ((a % m) + (b % m)) % m
        // (a - b) % m = ((a % m) - (b % m) + m) % m
        sum = sum % MOD;
        if(sum < 0) sum += MOD;
        totalSum += sum;
        totalSum %= MOD;
    }

    static void update(int index, int value) {
        // index로 들어온 값을 tree배열에서 찾을 수 있는 leafNode의 인덱스로 바꿔준다.
        int i = leafPointer + index;

        // 리프노드의 값을 바꿈
        tree[i] = value;

        // 부모노드
        i /= 2;

        // 부모노드로부터 루트노드까지 값을 update
        // 부모 = sum(left child, right child)
        while(i > 0) {
            tree[i] = tree[i * 2] + tree[(i * 2) + 1];
            i /= 2;
        }
    }

}
