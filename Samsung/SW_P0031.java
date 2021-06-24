// 교육P-0031. 최대와 최소
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_P0031 {
    static int T, N, Q;
    static int[][] tree;
    static int leafCount = 1, leafPointer = 0;
    static int minSum, maxSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            Q = Integer.parseInt(stz.nextToken());

            initTree();

            // 3. input 데이터 N개를 리프노드에 삽입한다.
            // leafPointer = leafSize - 1;
            // tree[]에 tree[(leafSize - 1) + i] ~  tree[(leafSize - 1) + N] 까지 데이터 저장
            stz = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                int leafInput = Integer.parseInt(stz.nextToken());
                tree[leafPointer + i][0] = leafInput;   // max 구하기용 input값
                tree[leafPointer + i][1] = leafInput;   // min 구하기용 input값
            }

            // 4. 힙 조건에 따라 리프노드의 부모노드를 따라 루트노드까지 값을 업데이트
            // min/max heap일 경우, 부모 min/max(left child, right child)
            for(int i = leafPointer; i > 0; i--) {
                tree[i][0] = Math.max(tree[i * 2][0], tree[(i * 2) + 1][0]);
                tree[i][1] = Math.min(tree[i * 2][1], tree[(i * 2) + 1][1]);
            } 

            int q, a, b;
            minSum = 0; maxSum = 0;
            for(int i = 0; i < Q; i++) {            
                stz = new StringTokenizer(br.readLine());
                q = Integer.parseInt(stz.nextToken());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());

                query(q, a, b);
            }
            
            bw.write("#" + t + " " + minSum + " " + maxSum);
            bw.newLine();
        }
        bw.flush();
    }

    static void initTree() {
        // 1. input 데이터가 N개인 경우, 리프노드의 수 K 구하기
        // N <= 2^k를 만족하는 최소값 2^k = K = leafCount
        // ex) N = 9인 경우, K = leafCount = 16
        while(N > leafCount) {
            // LeafCount *= 2;
            leafCount <<= 1;     //shift연산
        } 

        // 2. 리프노드의 사이즈를 바탕으로 전체 인덱스 트리의 크기를 구한다
        // 전체 인덱스 트리의 크기 = 2 ^ (K+1);
        // tree[i][0] = max, tree[i][1] = min
        tree = new int[leafCount * 2][2];
        leafPointer = leafCount - 1;

        // tree[i][0]는 최대값을 저장할거니까 0으로 초기화
        // tree[i][1]는 최소값을 저장할거니까 Integer.MAX_VALUE로 초기화
        for(int i = 1; i < tree.length; i++) {
            tree[i][1] = Integer.MAX_VALUE;
        }        
    }

    static void query(int q, int a, int b) {
        if(q == 0) getMinMax(a, b);
        else if(q == 1) update(a, b);
    }

    static void getMinMax(int left, int right) {
        // left, right 포인터를 tree배열에서 찾을 수 있는 leafNode의 인덱스로 바꿔준다.
        int max = 0, min = Integer.MAX_VALUE;
        left += leafPointer;
        right += leafPointer;

        // left, right 포인터가 교차할 때까지
        while(left <= right) {
            // left 포인터가 right child면 현재 값을 result에 max/min으로 업데이트하고 left 포인터를 오른쪽으로 하나 당긴다
            if(left % 2 == 1) {
                max = Math.max(max, tree[left][0]);
                max = 
            }
            // right 포인터가 left child면 현재 값을 result에 max/min으로 업데이트하고 right 포인터를 왼쪽으로 하나 당긴다
        }



    }
    

}
