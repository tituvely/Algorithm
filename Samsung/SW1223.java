// 중위 -> 후위
// 후위계산
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class SW1223 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String infix;
    static ArrayList<Character> postfix;
    static int N, output;
    static Stack<Character> stack;
    static Stack<Integer> stack2;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        for(int test_case=1; test_case<=10; test_case++) {
            N = Integer.parseInt(br.readLine());
            infix = br.readLine();
            postfix = new ArrayList<>();
            output = 0;
            stack = new Stack<>();
            stack2 = new Stack<>();
        
            // infix -> postfix
            for(char c: infix.toCharArray()) {
                if(Character.isDigit(c)) {
                    postfix.add(c);
                } else if(c=='(' || c=='*' || c=='/') {
                    stack.push(c);
                } else if(c=='+' || c=='-'){
                    while(!stack.isEmpty()) {
                        char k = stack.peek();
                        if(k=='(') {
                            break;
                        } else {
                            postfix.add(stack.pop());
                        }
                    }
                    stack.push(c);
                } else if (c==')') {
                    while(stack.peek()!='(') {
                        postfix.add(stack.pop());
                    }
                    stack.pop();
                }
            }

            while(!stack.isEmpty()) {
                postfix.add(stack.pop());
            }

            // postfix calculate
            for(char c: postfix) {
                if(Character.isDigit(c)) {
                    int a = c - '0';
                    stack2.push(a);
                } else {
                    int a = stack2.pop();
                    int b = stack2.pop();
                    if(c=='*') {
                        stack2.push(b*a);
                    } else if(c=='/') {
                        stack2.push(b/a);
                    } else if(c=='+') {
                        stack2.push(b+a);
                    } else if(c=='-') {
                        stack2.push(b-a);
                    }
                }
            }

            output = stack2.pop();

            System.out.println("#"+test_case+" "+output);
        }
    }
}
