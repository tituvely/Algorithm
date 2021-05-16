import java.io.FileInputStream;
import java.util.Scanner;

public class SW1217 {
    public static int power(int n, int m) {
        int result = 1;
        if (m > 1) {
            result = n * power(n, m-1);
        } else {
            result = n;
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
     
        System.setIn(new FileInputStream("./SW1217_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++) {
            sc.nextInt();

            int n = sc.nextInt();
            int m = sc.nextInt();

            int result = power(n,m);

            System.out.println("#" + test_case + " " + result);
        }
    }
    
}
