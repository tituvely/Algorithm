import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1240 {
    static int N, M;
    static char code[][];
    static BufferedReader br;  
    static StringTokenizer stz;
    static String temp;

    static int decode(String arr) {
        if(arr.equals("0001101")) return 0;
        else if(arr.equals("0011001")) return 1;
        else if(arr.equals("0010011")) return 2;
        else if(arr.equals("0111101")) return 3;
        else if(arr.equals("0100011")) return 4;
        else if(arr.equals("0110001")) return 5;
        else if(arr.equals("0101111")) return 6;
        else if(arr.equals("0111011")) return 7;
        else if(arr.equals("0110111")) return 8;
        else if(arr.equals("0001011")) return 9;
        return -1;
    }

    static int check(int[] arr) {
        int odd = arr[0] + arr[2] + arr[4] + arr[6];
        int even = arr[1] + arr[3] + arr[5];
        int pass = arr[7];
        if( (odd*3 + even + pass) % 10 == 0 ) {
            return odd+even+pass;
        }
        return 0;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1240_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=10; test_case++) {
            stz = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stz.nextToken());
            M = Integer.parseInt(stz.nextToken());

            code = new char[N][M];

            int answer = 0;

            for(int i=0; i<N; i++) {
                temp = br.readLine();
                for(int j=0; j<M; j++) {
                    code[i][j] = temp.charAt(j);
                }
            }
            
            for(int i=0; i<N; i++) {
                if(answer!=0) {
                    
                    break;
                }
                boolean needDecode = false;
                boolean needCheck = false;
                for(char c: code[i]) {
                    if(c == '1') {
                        needDecode = true;
                        break;
                    }
                }
                if(needDecode == true) {
                    int[] arr = new int[8];
                    for(int j=0; j<=M-56; j++) {
                        temp="";
                        for(int k=0; k<7; k++) {
                            temp += code[i][j+k];
                        }
                        if(decode(temp)!=-1) {
                            needCheck = true;
                        }
                        if(needCheck == true) {
                            arr[0] = decode(temp);
                            for(int ii=1; ii<8; ii++) {
                                temp="";
                                for(int jj=0; jj<7; jj++) {
                                    temp += code[i][j+ii*7+jj];
                                }
                                if(decode(temp)==-1) {
                                    needCheck = false;
                                    break;
                                }
                                arr[ii] = decode(temp);
                            }
                           
                            if(needCheck==true) {
                                // for(int c: arr) {
                                //     System.out.print(c);
                                // }
                                // System.out.println();
                                answer = check(arr);
                            }
                        }
                        
                    }
                }
            }
            System.out.println("#"+test_case+" "+answer);
        }
    }
}