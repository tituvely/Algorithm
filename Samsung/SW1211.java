import java.io.FileInputStream;
import java.util.*;

public class SW1211 {
    public static int ladder[][];
    public static int distance[];

    public static void findEnd(int j) {
        int row = 1;
        int col = j;
        int count = 2;

        while (row < 100) {
            //왼쪽으로 이동
            if(col-1>=0 && ladder[row][col-1] == 1) {
                while (col-1>=0) {
                    if(ladder[row][col-1] == 1) {
                        col--;
                        count++;
                    } else {
                        break;
                    }
                }
                row++;
                count++;
            }
            //오른쪽으로 이동
            else if (col+1 < 100 && ladder[row][col+1] == 1) {
                while (col+1 < 100) {
                    if(ladder[row][col+1] == 1) {
                        col++;
                        count++;
                    } else {
                        break;
                    }
                }
                row++;
                count++;
            }
            //아래로 이동
            else {
                row++;
                count++;
            }
        }

        if (ladder[99][col] == 1) {
            distance[j] = count; 
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./SW1211_input.txt"));

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++) {
            sc.nextInt();
            ladder = new int[100][100];
            distance = new int[100];

            for(int i = 0; i < 100; i++) {
                for(int j = 0; j < 100; j++) {
                    ladder[i][j] = sc.nextInt();
                }   
            }

            for(int j = 0; j < 100; j++) {
                if(ladder[0][j] == 1) {
                    findEnd(j);
                }
            }

            int min = 10000000;
            int start = 0;

            for(int i = 0; i < 100; i++) {
                if(distance[i] != 0) {
                    if(min >= distance[i]) {
                        min = distance[i];
                        start = i;
                    }
                }
            }

            System.out.println("#" + test_case + " " + start);

        }
    }
}
