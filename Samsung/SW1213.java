import java.util.Scanner;
import java.io.*;

public class SW1213 {
    public static void main(String[] args) throws Exception {
        
        System.setIn(new FileInputStream("./SW1213_input.txt"));

        Scanner sc = new Scanner(System.in);

        for(int test_case=1; test_case <= 10; test_case++) {
            sc.nextInt();
            String findStr =  sc.next();
            String sentence = sc.next();
            int cnt = 0;
            int senIndex = findStr.length() - 1;
            while (senIndex < sentence.length()){
                for(int i = 0, len = findStr.length(); i < len; i++) {
                    if(findStr.charAt(len-1-i) == sentence.charAt(senIndex-i)) {
                        if(i == len-1) {
                            cnt++;
                            senIndex++;
                        }
                    } else {
                        int jump = findStr.indexOf(sentence.charAt(senIndex));
                        jump = len - 1 - jump;
                        if(jump > 0) {
                            senIndex = senIndex + jump;
                        }
                        else if(jump==0) {
                            senIndex++;
                        }
                        else {
                            senIndex = senIndex + len;
                        }
                        break;
                    }
                }
            }
        
            
            System.out.println("#" + test_case + " " + cnt);
        
        }

    }
}
