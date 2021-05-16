//  P0058.탱크
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2
// 5      
// 1 1 5
// 2 2 4
// 3 3 3
// 4 4 2
// 5 5 1
// #1 20
// 9
// 3 1 7
// 4 4 6
// 1 3 9
// 7 2 3
// 8 5 2
// 2 6 8
// 9 7 1
// 5 8 5
// 6 9 4
// #2 77

public class P0058 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer stz;
    private static int N, x, y, score, leafStart;
    private static long answer;
    private static Tank[] tanks = new Tank[1_000_000];
    private static long[] tree = new long[4_000_000];

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        init();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            answer = 0;

            for (int i = 0; i < N; i++) {
                stz = new StringTokenizer(br.readLine());
                x = Integer.parseInt(stz.nextToken());
                y = Integer.parseInt(stz.nextToken());
                score = Integer.parseInt(stz.nextToken());

                tanks[i] = new Tank(x, y, score);
            }
                    
            Arrays.sort(tanks, 0, N);
            // x좌표가 가장 큰 순으로 정렬
            
            update(tanks[0].y, tanks[0].score);
            System.out.println("tanks[0].y "+tanks[0].y + "  tanks[0].score "+tanks[0].score);
            int max = tanks[0].y, min = tanks[0].y;

            for (int i = 1; i < N; i++) {
                // x좌표가 큰 것부터 tree에 집어 넣음
                update(tanks[i].y, tanks[i].score);
                System.out.println("tanks[i].y "+tanks[i].y + "  tanks[i].score "+tanks[i].score);
                if (tanks[i].y > max) {
                    System.out.println("tanks[i].y > max");
                    max = tanks[i].y;
                    continue;
                }
                if (min > tanks[i].y) {
                    answer += sum(min, max);
                    System.out.println("min > tanks[i].y");
                    System.out.println("answer "+ answer);
                    min = tanks[i].y;
                }
                else {
                    System.out.println("answer += sum(tanks[i].y, max);");
                    answer += sum(tanks[i].y, max);
                    System.out.println("answer "+ answer);
                    answer -= tanks[i].score;
                    System.out.println("answer "+ answer);
                }
            }

            System.out.println("#" + t + " " + answer);
                        
            Arrays.fill(tree, 0);
        }
    }

    private static void init() {
        // leafStart = 1;
        // while (1_000_000 >= leafStart) {
        //     leafStart *= 2;
        // }
        leafStart = 32;
    }

    private static void update(int y, int score) {
        int index = leafStart + y - 1;
        // System.out.println("시작 "+ index+ " y좌표: " + y);

        tree[index] = score;
        index /= 2;
        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            // System.out.println(index + " " + tree[index]);
            index /= 2;
        }
    }

    private static long sum(int left, int right) {
        long sum = 0;
        left = leafStart + left - 1;
        right = leafStart + right - 1;
        // System.out.println("시작/ 왼쪽 "+ left +" 오른쪽 "+right);
        
        while (left <= right) {
            // System.out.println("왼쪽 "+ left +" 오른쪽 "+right);
            if (left % 2 == 1) {
                sum += tree[left];
                // System.out.println("tree[left] "+ tree[left]);
                left++;
            }

            if (right % 2 == 0) {
                sum += tree[right];
                // System.out.println("tree[right] "+ tree[right]);
                right--;
            }
            // System.out.println("sum "+ sum);

            left /= 2;
            right /= 2;
            // System.out.println("왼쪽 "+ left +" 오른쪽 "+right);

        }

        return sum;
    }
}

class Tank implements Comparable<Tank> {
    int x, y, score;

    public Tank(int x, int y, int score) {
        super();
        this.x = x;
        this.y = y;
        this.score = score;
    }

    @Override
    public int compareTo(Tank tank) {
        return tank.x > this.x ? 1 : -1;
    }

}





// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.Comparator;
// import java.util.StringTokenizer;

// public class P0058 {
//     static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//     static StringTokenizer stz;
//     static int N;
//     static int tank[][];

//     static long getScore() {
//         long score = 0;

//         for(int i=0; i<N; i++) {
//             for(int j=i+1; j<N; j++) {
//                 if(tank[i][1] < tank[j][1]) {
//                     if(tank[i][2] > tank[j][2]) {
//                         score+= tank[j][2];
//                     }
//                 }
//             }
//         }

//         return score;
//     }

//     public static void main(String[] args) throws NumberFormatException, IOException {
//         int T = Integer.parseInt(bf.readLine());
//         long ans = 0;

//         for(int t=1; t<=T; t++) {
//             N = Integer.parseInt(bf.readLine());
//             tank = new int[N][3];
//             for(int i=0; i<N; i++) {
//                 stz = new StringTokenizer(bf.readLine());
//                 for(int j=0; j<3; j++) {
//                     tank[i][j] = Integer.parseInt(stz.nextToken());
//                 }
//             }

//             Arrays.sort(tank, new Comparator<int[]>(){
//                 @Override
//                 public int compare(int[] a, int[] b) {
//                     return a[0] - b[0];
//                 }
//             });

//             ans = getScore();

//             System.out.println("#"+t+ " "+ans);
//         }

//     }
// }
