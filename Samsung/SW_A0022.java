//연습A-0022. 소수경로
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_A0022 {
    static int T, A, B;
    static final int MAX = 10000;
    static boolean[] primeNum = new boolean[MAX + 1];
    static ArrayDeque<Integer> queue;
    static int[] visited;
    static int[] offset = {1, 10, 100, 1000};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        Arrays.fill(primeNum, true);
        getPrimeNum();

        // BFS로 최단 경로 탐색
        // 소수는 에라토스테네스의 체로 구함
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            A = Integer.parseInt(stz.nextToken());
            B = Integer.parseInt(stz.nextToken());

            visited = new int[MAX];
            int result = bfs(A, B) - 1; //visited[start] = 1로 시작했으므로 -1 해줘야 함

            bw.write("#" + t + " " + result);
            bw.newLine();
        }
        
        bw.flush();
    }

    public static int bfs(int start, int dest) {
        queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = 1; //출발한 곳으로 되돌아가지 않도록 초기값을 1로 세팅

        int current = 0;
        while(!queue.isEmpty()) {
            current = queue.poll();

            // 현재위치가 도착소수이면 몇 번안에 왔는지 리턴함
            if(current == dest) {
                return visited[current];
            }

            // 현재소수에서 숫자 하나만 바꿔서 갈 수 있는 소수를 모두 queue에 담는다
            for(int i = 0; i < 4; i++) {
                // (current % (offset[i] * 10) / offset[i]) -> 자릿수를 구함
                // ex) (1234 % 10) / 1 = 4
                //     (1234 % 100) / 10 = 3
                //     (1234 % 1000) / 100 = 2
                // (current % (offset[i] * 10) / offset[i]) * offset[i] -> 자릿수를 구하고 offset을 곱해서 빼줘서 해당 자릿수를 0으로 만듦
                int changeDigit = current - (current % (offset[i] * 10) / offset[i]) * offset[i];
                for(int j = 0; j < 10; j++) {
                    int next = changeDigit + (offset[i] * j);

                    //방문한적이 없고, 소수일 경우 queue에 담는다
                    if(visited[next] == 0 && primeNum[next]) {
                        queue.add(next);
                        visited[next] = visited[current] + 1;
                    }
                }
            }
        }

        return 0;
    }

    public static void getPrimeNum() {
        primeNum[0] = primeNum[1] = false; // 1은 소수가 아니므로 제외
        for(int i = 2; i * i <= MAX; i++) { 
            if(primeNum[i]) {   //소수이면
                //for loop 배수들을 모두 제외
                for(int j = i * i; j <= MAX; j += i) {  // i * i보다 작은 값들은 이미 이전 i의 배수를 체크할 때 다 체크했음
                    primeNum[j] = false;    // 소수의 배수는 모두 소수가 아니다.
                }
            }
        }

        // 문제의 조건에 맞게 1000보다 작은 구간은 전부 false로 해서 못가게 함
        for(int i = 0; i <= 1000; i++) {
            primeNum[i] = false;
        }
    }

}
