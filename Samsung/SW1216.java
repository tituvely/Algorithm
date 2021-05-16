import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SW1216 {
    public static char[][] square;

    public static boolean isRowPalindrome(int row, int index, int len) {
        boolean result = true;
        for(int i = 0; i < len/2; i++) {
            if(square[row][index+i] != square[row][index+len-1-i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isColPalindrome(int col, int index, int len) {
        boolean result = true;
        for(int i = 0; i < len/2; i++) {
            if(square[index+i][col] != square[index+len-1-i][col]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./SW1216_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++) {
            sc.nextInt();
            square = new char[100][100];
            int max = 1;

            for (int i = 0; i < 100; i++) {
                String temp = sc.next();
                for (int j = 0; j < 100; j++) {
                    square[i][j] = temp.charAt(j);
                }
            }

            boolean flag = false;
            for(int len = 100; len > 0; len--){
                for (int rowcol = 0; rowcol < 100; rowcol++) {
                    for (int j = 0; j < 100 - (len-1); j++) {
                        if(isRowPalindrome(rowcol, j, len)==true) {
                            flag = true;
                            max = len;
                            break;
                        }
                        if(isColPalindrome(rowcol, j, len)==true) {
                            flag = true;
                            max = len;
                            break;
                        }
                    }
                }
                if(flag==true) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }
}
