import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int i;
    int j;
    long w;

    Point(int i, int j, long w) {
        this.i = i;
        this.j = j;
        this.w = w;
    }

    @Override
    public int compareTo(Point p) {
        return (int) (this.w - p.w);
    }
}

class Graph{
    private int n;           //노드들의 수
    private long maps[][];    //노드들간의 가중치 저장할 변수
    private PriorityQueue<Point> pq = new PriorityQueue<>();

    public Graph(int n){
        this.n = n;
        maps = new long[n+1][n+1];
    }

    public void input(int i,int j, long w){
        maps[i][j] = w;
        maps[j][i] = w;
        pq.add(new Point(i,j,w));
    }
 
    public void dijkstra(int start, int end, int K){
        long distance[] = new long[n+1];          //최단 거리를 저장할 변수
        boolean[] check = new boolean[n+1];     //해당 노드를 방문했는지 체크할 변수
        int pre[] = new int[n+1];               //직전 경로를 저장할 변수
         
        //distance값 초기화.
        for(int i = 1; i < n + 1; i++){
            distance[i] = Long.MAX_VALUE;
        }
         
        //시작노드값 초기화.
        distance[start] = 0;
        check[start] = true;
         
        //연결노드 distance갱신
        for(int i = 1; i < n + 1; i++){
            //방문하지 않았고 시작점과 연결되어 있다면
            if(!check[i] && maps[start][i]!=0){
                distance[i] = maps[start][i];
                pre[i] = start;
            }
        }
         
        for(int a=0;a<n-1;a++){
            //원래는 모든 노드가 true될때까지 인데
            //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
            //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
            long min = Long.MAX_VALUE;
            int min_index = -1;
             
            //최소값 찾기
            for(int i = 1; i < n + 1; i++){
                //방문하지 않았고 연결되어 있다면
                if(!check[i] && distance[i] != Long.MAX_VALUE){
                    if(distance[i] < min){
                        min = distance[i];
                        min_index = i;
                    }
                }
            }
             
            check[min_index] = true;
            for(int i=1;i<n+1;i++){
                //방문하지 않았고 min_index과 연결되어 있다면
                if(!check[i] && maps[min_index][i]!=0){
                    //i로 곧장 가는 것보다 min_index를 거쳐서 i로 가는 것이 나으면 갱신
                    if(distance[i]>distance[min_index]+maps[min_index][i]){
                        distance[i] = distance[min_index]+maps[min_index][i];
                        pre[i] = min_index;
                    }
                }
            }
            if(min_index==end) {
                break;
            }
        }
         
        if(K==1) {
            //제일 큰 거리 1로 설정 후 다시 다익스트라
            Point p = pq.peek();
            maps[p.i][p.j] = 1;
            maps[p.j][p.i] = 1;
            this.dijkstra(start, end, K);
        }

        if(K==2) {
            //제일 큰 거리 두개 1로 설정 후 다시 다익스트라
            
            
            if(new_distance[end] > distance[end]) {
                print new_distance[end]
            } else {
                밑에 식
            }
        }

        if(K!=0) {
            int index = end;
            long sum = 0;
            ArrayList<Long> path_dist = new ArrayList<>();
            while(pre[index] != 0) {
                path_dist.add(maps[pre[index]][index]);
                index = pre[index];
            }

            Collections.sort(path_dist);

            for(int i=0; i<path_dist.size()-K; i++) {
                System.out.println(path_dist.get(i));
                sum += path_dist.get(i);

            }
            sum += K;

            System.out.println(sum);
            System.out.println(distance[end]);
        } else {
            System.out.println(distance[end]);
        }
    }
}

public class TestNewbie {  
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, M, K;
    static int start, end;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            
            Graph g = new Graph(M);

            int p1, p2;
            long time;
            for(int i=0; i<M; i++) {
                stz = new StringTokenizer(br.readLine());
                p1 = Integer.parseInt(stz.nextToken());
                p2 = Integer.parseInt(stz.nextToken());
                time = Long.parseLong(stz.nextToken());
                g.input(p1, p2, time);
            }

            stz = new StringTokenizer(br.readLine());
            start = Integer.parseInt(stz.nextToken());
            end = Integer.parseInt(stz.nextToken());

            System.out.print("#" + test_case + " ");
            g.dijkstra(start, end, K);
        }
    }   
}
