import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
    int m;
    int x;

    Point() {}

    Point(int m, int x) {
        this.m = m;
        this.x = x;
    }
}

public class SW1245 {
    static BufferedReader br;
    static StringTokenizer stz;
    static int N;
    static Point[] zasungche;

    public static void main(String[] args) throws Exception {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1245_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++) {
            N = Integer.parseInt(br.readLine());
            stz = new StringTokenizer(br.readLine());
            zasungche = new Point[N];
            
            for(int i=0; i<N; i++) {
                zasungche[i] = new Point();
            }

            for(int i=0; i<N; i++) {
                zasungche[i].x = Integer.parseInt(stz.nextToken());
            }

            for(int i=0; i<N; i++) {
                zasungche[i].m = Integer.parseInt(stz.nextToken());
            }
            
            System.out.print("#" + test_case);

            int cnt;
            double left, right, mid, distance;
            double leftsum, rightsum;
            for(int i=0; i<N-1; i++) {
                cnt = 0;
                mid = 0;
                left = zasungche[i].x;
                right = zasungche[i+1].x;
                while(cnt < 100) {
                    mid = (left + right) / 2;
                    leftsum = 0;
                    rightsum = 0;
                    for(int j=0; j<N; j++) {
                        distance = (double)(zasungche[j].x - mid);
                        if(zasungche[j].x < mid) {
                            leftsum += zasungche[j].m / Math.pow(distance, 2);
                        }
                        else if(zasungche[j].x > mid) {
                            rightsum += zasungche[j].m / Math.pow(distance, 2);
                        }
                    }

                    if(leftsum == rightsum) {
                        System.out.print(" ");
                        System.out.print(String.format("%.10f", mid));
                        break;
                    }
                    else if (leftsum > rightsum) {
                        left = mid;
                        cnt++;
                    }
                    else if (leftsum < rightsum) {
                        right = mid;
                        cnt++;
                    }

                }

                if(cnt == 100) {
                    System.out.print(" ");
                    System.out.print(String.format("%.10f", mid));
                }
            }
            System.out.println();
            
        }
    }
}
