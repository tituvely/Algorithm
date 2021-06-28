// 연습P-0023. 동맹의 동맹은 동맹
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// Union-find
public class SW_P0023_P {
    static int T, N, Q;
    static int a, b, c, answer;
    static int parent[] = new int[100001];
    static int rank[] = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {   
            N = Integer.parseInt(br.readLine());
            Q = Integer.parseInt(br.readLine());

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            answer = 0;
            for(int i = 1; i <= Q; i++) {
                stz = new StringTokenizer(br.readLine());
                c = Integer.parseInt(stz.nextToken());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());

                if(c == 0) union(a,b);
                else if(c == 1) {
                    if(find(a) == find(b)) {
                        answer++;
                    }    
                }      
            }

            bw.write("#" + t + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a==b) return;

        // 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다
        if(rank[a] < rank[b]) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }

        if(rank[a] == rank[b]) {
            rank[a]++;
        }

    }

    private static int find(int a) {
        if(a == parent[a])  return a;
        else return parent[a] = find(parent[a]);
    }

}