import java.io.FileInputStream;
import java.util.*;

public class SW1210 {
    public static int[][] ladder;

    public static int findEnd(int start) {
        int i = 1;
        int j = start;
        while(i < 100) {
            // 왼쪽으로 이동
            if(j - 1 >= 0 && ladder[i][j-1]==1) {
                j--;
                while(j - 1 >= 0) {
                    if(ladder[i][j-1]==1) {
                        j--;
                    } else {
                        break;
                    }
                }
                i++;
            }
            // 오른쪽으로 이동
            else if(j + 1 < 100 && ladder[i][j+1]==1) {
                j++;
                while(j + 1 < 100) {
                    if(ladder[i][j+1]==1) {
                        j++;
                    } else {
                        break;
                    }
                }
                i++;
            }
            // 아래로 이동
            else {
                i++;
                // System.out.println(i + " "+j);
            }
        }

        return j;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./SW1210_input.txt"));

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {
            sc.nextInt();
            ladder = new int[100][100];

            for(int i = 0; i < 100; i++) {
                for(int j = 0; j < 100; j++) {
                    ladder[i][j] = sc.nextInt();
                }
            }

            for(int j = 0; j < 100; j++) {
                if(ladder[0][j] == 1) {
                    if(ladder[99][findEnd(j)]==2) {
                        System.out.println("#" + test_case + " " + j);
                        break;
                    }
                }
            }
        }
    }
}