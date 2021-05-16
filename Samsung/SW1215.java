import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SW1215 {
    public static int len;
    public static char[][] square;

    public static boolean isRowPalindrome(int row, int index) {
        boolean result = true;
        for(int i = 0; i < len/2; i++) {
            if(square[row][index+i] != square[row][index+len-1-i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isColPalindrome(int col, int index) {
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
        
        System.setIn(new FileInputStream("./SW1215_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++) {
            len = sc.nextInt();
            square = new char[8][8];
            int cnt = 0;

            for (int i = 0; i < 8; i++) {
                String temp = sc.next();
                for (int j = 0; j < 8; j++) {
                    square[i][j] = temp.charAt(j);
                }
            }

            for (int row = 0; row < 8; row++) {
                for (int j = 0; j < 8 - (len-1); j++) {
                    if(isRowPalindrome(row, j)==true) {
                        cnt++;
                    }
                }
            }

            for (int col = 0; col < 8; col++) {
                for (int j = 0; j < 8 - (len-1); j++) {
                    if(isColPalindrome(col,j)==true) {
                        cnt++;
                    }
                }
            }


            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
