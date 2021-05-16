import java.util.*;
import java.io.*;

public class SW1206 {
    public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("./SW1206_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int width = sc.nextInt();
			int[] buildings = new int[width];
            int cnt = 0;

			for(int i=0; i < width; i++) {
				buildings[i] = sc.nextInt();
			}
            
			for(int j=0, len = buildings.length; j < len; j++) {
                int max = 0;
				if(buildings[j] != 0) {
					if(j > 2) {
                        if(buildings[j-2] > max) {
                            max = buildings[j-2];
                        }
                    }
                    if(j > 1) {
                        if(buildings[j-1] > max) {
                            max = buildings[j-1];
                        }
                    }
                    if(j < len - 1) {
                        if(buildings[j+1] > max) {
                            max = buildings[j+1];
                        }
                    }
                    if(j < len - 2) {
                        if(buildings[j+2] > max) {
                            max = buildings[j+2];
                        }
                    }
				}

                if(buildings[j] - max > 0) {
                    cnt += buildings[j] - max;
                }
			}

			System.out.println("#" + test_case +" " + cnt);

		}
	}
}
