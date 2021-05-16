import java.io.FileInputStream;
import java.util.Scanner;

public class SW1218 {
    public static void main(String[] args) throws Exception {
     
        System.setIn(new FileInputStream("./SW1218_input.txt"));

		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++) {
            int length = sc.nextInt();
            char[] bracket = sc.next().toCharArray();

            char[] stack = new char[1000];
            int top = -1;
            
            int result = 1;

            for(int i = 0; i < bracket.length; i++) {
                if(bracket[i]=='(' || bracket[i]=='[' || bracket[i]=='{' || bracket[i]=='<') {
                    stack[++top] = bracket[i];
                } else if(bracket[i]==')') {
                    if(top >=0 && stack[top] == '(') {
                        top--;
                    } else {
                        result = 0;
                        break;
                    }
                } else if(bracket[i]==']') {
                    if(top >=0 && stack[top] == '[') {
                        top--;
                    } else {
                        result = 0;
                        break;
                    }
                } else if(bracket[i]=='}') {
                    if(top >=0 && stack[top] == '{') {
                        top--;
                    } else {
                        result = 0;
                        break;
                    }
                } else if(bracket[i]=='>') {
                    if(top >=0 && stack[top] == '<') {
                        top--;
                    } else {
                        result = 0;
                        break;
                    }
                } 
            }


            System.out.println("#" + test_case + " " + result);
        }
    }
    
}
