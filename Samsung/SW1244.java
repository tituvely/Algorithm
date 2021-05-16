import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1244 {
    static BufferedReader br;
    static StringTokenizer stz;
    static int[] original, sorted;
    static String str;
    static int T, swapCnt, index, lastIndex, changeIndex, max, size;
    static boolean flag,chk;

    public static void main(String[] args) throws Exception {
        System.setIn(new BufferedInputStream(new FileInputStream("./SW1244_input.txt")));
        br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        for(int i=1; i<=T; i++) {
            stz = new StringTokenizer(br.readLine());
            str = stz.nextToken();
            swapCnt = Integer.parseInt(stz.nextToken());
            
            size=str.length();
            original = new int[size];
            sorted = new int[size];

            for(int j=0; j<size; j++) {
                original[j] = str.charAt(j) - '0';
            }
            sorted = original.clone();
            Arrays.sort(sorted);

            index=0;
            lastIndex=size-1;
            max=sorted[size-1];
            flag=true;
            chk=false;

            while(swapCnt!=0) {
                //정렬되있는 상태인가 확인
                for(int j=0;j<size;j++){
                    if(original[j]!=sorted[size-1-j]){
                        flag=false;
                        break;
                    }
                }
                //중복된 값이 있나 확인
                for(int j=size-1;j>0;j--){
                    if(sorted[j]==sorted[j-1]){
                        chk=true;
                        break;
                    }
                }
                //중복된 숫자가 있고 내림차순으로 정렬되어 있다면
                if(chk&&flag) {
                    break;
                }
                
                    if(index<size) {
                        if (original[index] != sorted[size - 1 - index]) {
                            for (int j = size - 1; j > index; j--) {
                                //뒤에서부터 가장 큰 수를 찾아 교환함
                                if (original[j] == sorted[size - 1 - index]) {
                                    changeIndex = j;
                                    swap(index, changeIndex);
                                    // bc___aa -> ac___ab -> aa___cb 인 상탠데 b>c일 경우 c와 b를 교환해주는 것
                                    // -> aa___bc
                                    if (index > 0 && index < size && original[index - 1] == original[index]) {
                                        if (original[changeIndex] < original[changeIndex + 1]) {
                                            swap(changeIndex, changeIndex + 1);
                                        }
                                    }
                                    break;
                                }
                            }
 
                            index++;
                            swapCnt--;
                        } else if (original[index] == sorted[size - 1 - index]) {
                            index++;
                        }
                    }
                    else{
                        swap(size - 2, size - 1);
                        swapCnt--;
                    }
            }

            System.out.print("#"+ i +" ");
            for(int j=0;j<size;j++){
                System.out.print(original[j]);
            }
            System.out.println();
        }
    }

    static void swap(int i,int j){
        int tmp=original[i];
        original[i]=original[j];
        original[j]=tmp;
    }

}
