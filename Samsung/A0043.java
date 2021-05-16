// A0043. 카드게임 전략
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class A0043 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stz;
    static int N, ans, max, sum;
    static int[][] coun;   // 상대방이 낼 카드의 정보 (counterpart)
    static boolean[] card;   // 내가 가진 카드 체크
    static int[] mycard;   // 내가 가진 카드의 수

    static void setCard() { // mycard를 채우는 함수
        int cnt = 0;
        for(int i=1; i<=4*N; i++) {
            if(card[i]==true) {
                mycard[cnt] = i;
                cnt++;
            }
        }
    }

    static void permutation(int[] arr, int depth, int n, int r) {   // nPr의 경우의 수를 구함: 순열
        if (depth == r) {
            score();
            return;
        }

        // 배열의 첫 값부터 순서대로 하나씩 바꾸며 모든 값을 한번씩 swap 합니다.
        // depth 를 기준 인덱스로 하여 depth 보다 인덱스가 작은 값들은 그대로 고정하고
        // depth 보다 인덱스가 크거나 같은 값들만 가지고 다시 swap 을 진행합니다.
        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }   

    static void score() {
        sum = 0;
        List<Integer> arr = new ArrayList<Integer>(4);
        for(int i=0; i<N; i++) {
            arr.add(coun[0][i]);
            arr.add(coun[1][i]);
            arr.add(coun[2][i]);
            arr.add(mycard[i]);

            arr.sort(null);

            for(int j=0; j<4; j++) {
                if(mycard[i] == arr.get(j)) {
                    if(j==0) sum+= arr.get(3);
                    else sum+= arr.get(j-1);        
                    break;   
                }
            }

            arr.clear();
        }

        // for(int i=0; i<N; i++) {
        //     System.out.println(mycard[i]);
        // }
        // System.out.println(sum);

        if(sum>max) max = sum;
        
    }

    static int max() {
        max = 0;

        // 모든 순열을 구해서 score의 합이 max가 되는 경우를 반환
        permutation(mycard, 0, N, N);

        return max;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());
       
        for(int test_case=1; test_case<=T; test_case++) {
            N = Integer.parseInt(bf.readLine());
            card = new boolean[4*N + 1];
            mycard = new int[N];
            coun = new int[3][N];
            Arrays.fill(card, true);

            for(int i=0; i<3; i++) {
                stz = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; j++) {
                    int temp = Integer.parseInt(stz.nextToken());
                    card[temp] = false;
                    coun[i][j] = temp;
                }
            }

            setCard();

            ans = max();

            System.out.println("#"+test_case+" "+ans);
        }
    }
}
