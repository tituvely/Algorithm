import java.util.Scanner;
import java.io.FileInputStream;

public class SW1219 {
    static int[][] road;
    static int result = 0;

    public static void dfs(int i) {
        if(i == 99) {
            result = 1;
        } else {
            for(int j = 0; j < 100; j++) {
                if(road[i][j] == 1) {
                    dfs(j);
                }
            }
        }
    }

    public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("./SW1219_input.txt"));

		Scanner sc = new Scanner(System.in);
        
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            sc.nextInt();
			int cnt = sc.nextInt();
            road = new int[100][100];
            result = 0;

			for(int i=0; i < cnt; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
            
                road[start][end] = 1;
			}

            dfs(0);

			System.out.println("#" + test_case + " " + result);

		}
	}
}
