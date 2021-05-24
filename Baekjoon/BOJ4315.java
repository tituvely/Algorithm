//4315. 나무 위의 구슬
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ4315 {
    static int N, Root, Answer;
    static int[] D = new int[10001]; // 초기 구슬개수
    static int[] S = new int[10001]; // S[i] : i 를 Root 로 하는 트리의 구슬 과부족
    static ArrayList<Integer>[] AL = new ArrayList[10001]; // 자식들을 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            N = Integer.parseInt(br.readLine().trim());
            if(N==0) break;

            for(int i=1; i<=N; i++) {
                if(AL[i] == null) AL[i] = new ArrayList<Integer>();
                else AL[i].clear();
                S[i] = 0;
            }

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                D[n] = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                for(int j=0; j<m; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    AL[n].add(x);
                    // 루트를 판별하기 위해 자식으로 나온 정점은 카운트해준다
                    // 한 번도 나오지 않은 정점이 루트
                    S[x]++;
                }
            }

            // finding root
            for(int i=1; i<=N; i++) {
                if(S[i]==0) {
                    Root = i;
                }
                S[i] = 0;
            }

            Answer = 0;
            dfs(Root);

            System.out.println(Answer);
        }
    }
    
    // 각 정점에서의 과부족을 계산해준다
    // 부모의 과부족은 자식들의 과부족을 합산한 것
        /** DFS 탐색을 할 때
            현재 정점을 A 라 하고 A 의 자식 정점이 B, C 라 하자.
            그리고 A 정점 자체의 과부족을 X 라 하면
            B 로 DFS 탐색 (재귀)
            --> B 정점의 과부족을 X 에 더함.
            C 로 DFS 탐색 (재귀)
            --> C 정점의 과부족을 X 에 더함.
            그래서 나온 최종 X 값을 부모에 넘김. **/
    // 자식들의 과부족은 결국 부모에게 가고, 오게 됨

    // 구슬의 최소 이동 횟수는
    // 각 자식 정점의 과부족의 "절댓값"의 합이 된다.
    private static void dfs(int curr) {
        int subS = 0;
        for(int next : AL[curr]) {
            dfs(next);
            subS += S[next];
            Answer += Math.abs(S[next]);
        }
        S[curr] = subS + D[curr] - 1;
    }
}