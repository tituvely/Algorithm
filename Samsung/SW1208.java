import java.util.*;
import java.io.*;

public class SW1208 {
    public static int max(int[] box) {
        int max=0;
        int index = 0;
        for(int i=0; i < box.length; i++) {
            if(box[i] > max) {
                max = box[i];
                index = i;
            }
        }
        return index;
    }

    public static int min(int[] box) {
        int min=box[0];
        int index = 0;
        for(int i=1; i < box.length; i++) {
            if(box[i] < min) {
                min = box[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./SW1208_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int dumpCount = sc.nextInt();
			int[] box = new int[100];
            int diff = 0;

			for(int i=0; i < 100; i++) {
				box[i] = sc.nextInt();
			}

            for(int i=0; i < dumpCount; i++) {
                box[max(box)]--;
                box[min(box)]++;
			}

            diff = box[max(box)] - box[min(box)];

			System.out.println("#" + test_case +" " + diff);

		}
    }
}
