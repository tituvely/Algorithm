import java.io.FileInputStream;
import java.util.Scanner;

class SW5656 {
    static int N, W, H;
    static int[][] box;

    public static int explode_count(int i, int j) {
        int n = box[i][j];
        int k = 0;
        //상
        while(k < n && ) {

        }
        //하 점검
        //좌 점검
        //우 점검
    }
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("./SW5656_input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            box = new int[W][H];

            for(int i=0; i < W; i++) {
                for(int j=0; j < H; j++) {
                    box[i][j] = sc.nextInt();
                }
            }

            //열을 돌면서 가장 위에 블럭의 폭발 범위를 계산
            for(int j=0; j < H; j++) {
                for(int i=0; i < W; i++) {
                    if(box[i][j] != 0) {
                        explode_count(i,j);
                    } 
                }
            }

		}

        
	}
}