// 연습P-0007. 고속도로 건설
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW_P0007 {
    static int T, N, M;
    static int s, e, c;
    static ArrayList<Node> city = new ArrayList<>();
    static int parent[];
    static int rank[];

    static class Node implements Comparable<Node> {
        int start;
        int dest;
        int cost;

        public Node(int start, int dest, int cost) {
            this.start = start;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {   
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            city.clear();
            parent = new int[N+1];
            rank = new int[N+1];

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            for(int i = 0; i < M; i++) {
                stz = new StringTokenizer(br.readLine());
                s = Integer.parseInt(stz.nextToken());
                e = Integer.parseInt(stz.nextToken());
                c = Integer.parseInt(stz.nextToken());
                city.add(new Node(s,e,c));
            }

            bw.write("#" + t + " " + kruskal(N, M));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static int kruskal(int V, int E) {
        int mst_cost = 0;
        int selected = 0;

        // 간선이 짧은 순서대로 정렬
        Collections.sort(city);

        for(Node node : city) {
            if(find(node.start)!=find(node.dest)) {
                selected++;
                mst_cost+= node.cost;
                union(node.start, node.dest);
            }
            if(selected == V-1) {
                return mst_cost;
            }
        }

        if(selected == V - 1) {
            return mst_cost;
        }
        else {
            return -1;
        }
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