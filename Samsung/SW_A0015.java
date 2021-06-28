// 기출A-0015. 사교모임
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW_A0015 {
    static int T, N, K;
    static int a, b;
    static int parent[];
    static int rank[];
    static int member[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {   
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());

            parent = new int[N+1];
            rank = new int[N+1];
            member = new int[N+1];

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
                member[i] = 1;
                rank[i] = 0;
            }

            for(int i = 0; i < K; i++) {
                stz = new StringTokenizer(br.readLine());
                a = Integer.parseInt(stz.nextToken());
                b = Integer.parseInt(stz.nextToken());
                union(a, b);
            }

            int cnt = 0, max = 0;
            for(int i = 1; i <= N; i++) {
                if(parent[i] == i) {
                    if(member[i] == 1) {
                        continue;
                    }
                    cnt++;
                    if(max < member[i]) {
                        max = member[i];
                    }
                }
            }

            bw.write("#" + t + " " + cnt + " " + max);
        }
        bw.flush();
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return;

        // 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다
        if(rank[a] < rank[b]) {
            parent[a] = b;
            member[b] += member[a];
        } else {
            parent[b] = a;
            member[a] += member[b];
        }

        if(rank[a] == rank[b]) {
            rank[a]++;
        }
    }

    public static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
