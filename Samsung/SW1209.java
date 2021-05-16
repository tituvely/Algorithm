import java.util.*;
import java.io.*;

public class SW1209 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./SW1209_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++) {
			int[][] arr = new int[100][100];
            int max = 0;
            int sum = 0;

            sc.nextInt();
            
			for(int i=0; i < 100; i++) {
                for(int j=0; j < 100; j++) {
				    arr[i][j] = sc.nextInt();
                }
			}

            for(int i=0; i < 100; i++) {
                sum = 0;
                for(int j=0; j < 100; j++) {
                    sum += arr[i][j];
                    if(sum > max) {
                        max = sum;
                    }
                }
            }

            for(int j=0; j < 100; j++) {
                sum = 0;
                for(int i=0; i < 100; i++) {
                    sum += arr[i][j];
                    if(sum > max) {
                        max = sum;
                    }
                }
            }

            sum = 0;
            for(int i=0; i < 100; i++) {
                sum += arr[i][i];
                if(sum > max) {
                    max = sum;
                }
            }

            sum = 0;
            for(int i=0; i < 100; i++) {
                sum += arr[i][99-i];
                if(sum > max) {
                    max = sum;
                }
            }

			System.out.println("#" + test_case +" " + max);

		}
    }
}