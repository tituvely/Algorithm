// #1225. 암호생성기
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

class SW1225
{
    static Queue<Integer> q = new LinkedList<>();
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("./SW1225_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            sc.nextInt();
            q.clear();
            
			for(int i=0; i < 8; i++) {
				q.offer(sc.nextInt());
			}

            int cnt = 1;
            int num = 0;
            while(true) {
                num = q.poll();
                if(num-cnt<=0) {
                    q.offer(0);
                    break;
                } else {
                    q.offer(num-cnt);
                }
                cnt++;
                if(cnt==6) {
                    cnt = 1;
                }
            }

			String output="";
            for(int i : q) {
                output += " " + String.valueOf(i);
            }

			System.out.println("#" + test_case + output);

		}
	}
}