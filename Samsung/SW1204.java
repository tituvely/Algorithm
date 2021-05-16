// #1204. 최빈값구하기

import java.util.Scanner;
import java.io.FileInputStream;

class SW1204
{
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("./SW1204_input.txt"));

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int max = 0;
			int index = 0;
			int[] cnt = new int[101];

			sc.nextInt();

			for(int j=0; j < 1000; j++) {
				cnt[sc.nextInt()]++;
			}

			for(int j=0; j < cnt.length; j++) {
				if(cnt[j] >= max) {
					max = cnt[j];
					index = j;
				}
			}

			System.out.println("#" + test_case +" " + index);

		}
	}
}