import java.util.*;

class SW5658 {
    public static int hexToDec(char a) {
        // char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (Character.isDigit(a)) {
            return Integer.parseInt(String.valueOf(a));
        } else if (a == 'A') {
            return 10;
        } else if (a == 'B') {
            return 11;
        } else if (a == 'C') {
            return 12;
        } else if (a == 'D') {
            return 13;
        } else if (a == 'E') {
            return 14;
        } else if (a == 'F') {
            return 15;
        }
        return a;
    }
    
    public static void merge(int[] arr, int low, int mid, int high) {
        int p = 0, l = low, h = mid;
        int[] temp = new int[high-low];

        while(l < mid && h < high) {
            if(arr[l] < arr[h]) {
                temp[p++] = arr[l++];
            } else {
                temp[p++] = arr[h++];
            }
        }

        while(l < mid) {
            temp[p++] = arr[l++];
        }

        while(h < high) {
            temp[p++] = arr[h++];
        }

        for(int i = low; i < high; i++) {
            arr[i] = temp[i - low];
        }

    }

    public static void sort(int[] arr, int low, int high) {
        if(high-low < 2) {
            return;
        }

        int mid = (low+high) / 2;

        sort(arr, low, mid);
        sort(arr, mid, high);
        merge(arr, low, mid, high);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            // input 입력
			int n = sc.nextInt();
            int k = sc.nextInt();
            String number = sc.next();
            
            char[] hex = new char[n];
            int[] dec = new int[n];
            
            for(int i = 0; i < n; i++) {
                hex[i] = number.charAt(i);
            }

            // n/4바퀴를 돌며 숫자 생성
            // hex to dec으로 저장
            int rot = n/4;
            int ind = 0;
            for(int i = 0; i < rot; i++) {
                for(int j = 0; j < 4; j++) {
                    int decNum = 0;
                    for(int l = 0; l < rot; l++) {
                        
                        int index = rot * j + l + i;
                        if (index >= n) {
                            index = index - n;
                        }
                        // System.out.println(index);
                        // System.out.println(rot-l-1);
                        // System.out.println(hex[index]);
                        decNum += Math.pow(16, rot-l-1) * hexToDec(hex[index]);
                    }
                    dec[ind++] = decNum;
                    // System.out.println(dec[ind]);
                }    
            }
            
            // sorting
            sort(dec, 0, dec.length);

            // k번째 숫자 출력
            // 중복은 넘김
            int output = dec[n-1];
            int cnt = 1;
            
            for(int i = n-2 ; i >=0 ; i--) {
                if(cnt == k) {
                    break;
                }
                if(output > dec[i]) {
                    output = dec[i];
                    cnt++;
                }
            }

            System.out.println("#" + test_case + " " + output);

        }
    }
}