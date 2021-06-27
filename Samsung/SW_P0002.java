// 교육 P-0002. 개미
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_P0002 {
    static int T, N, K, L;

    static class Ant implements Comparable<Ant> {
        int index;      // 개미 번호
        int position;   // 시작 위치
        int direction;  // 시작 방향 (L=0, R=1)

        public Ant(int index, int position, int direction) {
            this.index = index;
            this.position = position;
            this.direction = direction;
        }

        @Override
        public int compareTo(Ant o) {
            return Integer.compare(this.position, o.position);
        }

    }

    static class Fall implements Comparable<Fall> {
        int time;       // 낙하 시간
        int direction;  // 낙하 방향
        public Fall(int time, int direction) {
            this.time = time;
            this.direction = direction;
        }

        // 떨어지는 시간 순서대로 정렬하되, 문제의 조건에 따라 같은 시간에 떨어지면 L > R의 우선순위를 갖는다
        @Override
        public int compareTo(Fall o) {
            if(this.time == o.time) return Integer.compare(this.direction, o.direction);
            else return Integer.compare(this.time, o.time);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            K = Integer.parseInt(stz.nextToken());
            L = Integer.parseInt(stz.nextToken());

            Fall[] fallArr = new Fall[N];
            Ant[] antArr = new Ant[N];

            for(int i = 0; i < N; i++) {
                stz = new StringTokenizer(br.readLine());
                int pos = Integer.parseInt(stz.nextToken());
                
                // L -> R 방향으로 움직이는 개미는 떨어질때까지 (선분길이 - 입력위치) 만큼의 시간이 걸리고,
                // R -> L 방향으로 움직이는 개미는 떨어질때까지 (입력위치) 만큼의 시간이 걸린다

                // L쪽 끝에서 가장 먼저 떨어지는 개미는 입력 시 가장 왼쪽의 개미
                // R쪽 끝에서 가장 먼저 떨어지는 개미는 입력 시 가장 오른쪽의 개미
                if((stz.nextToken().charAt(0) == 'L')) {
                    fallArr[i] = new Fall(pos, 0);
                    antArr[i] = new Ant((i+1), pos, 0);
                } else {
                    fallArr[i] = new Fall((L - pos), 1);
                    antArr[i] = new Ant((i+1), pos, 1);
                }
            }

            // 떨어지는 시간에 대해 오름차순으로 정렬 -> 어느 방향에서 떨어졌는지 알 수 있음
            Arrays.sort(fallArr);

            // 초기 개미 위치에 대해 오름차순으로 정렬
            Arrays.sort(antArr);

            int left = 0, right = N - 1;
            for(int i = 0; i < K - 1; i++) {
                // 왼쪽에서 개미 하나 떨어짐
                if(fallArr[i].direction == 0) {
                    left++;
                }
                // 오른쪽에서 개미 하나 떨어짐
                else {
                    right--;
                }
            }

            int result = 0;
            // K번째 떨어지는 방향이 왼쪽일 경우
            if(fallArr[K-1].direction == 0) {
                result = antArr[left].index;
            }
            // K번째 떨어지는 방향이 오른쪽일 경우
            else {
                result += antArr[right].index;
            }

            bw.write("#" + t + " " + result + " " + fallArr[K-1].time);
            bw.newLine();
        }
        bw.flush();
    }
}
