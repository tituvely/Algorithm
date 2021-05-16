import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BiFunction;

public class SW1222 {
    public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("./SW1222_input.txt"));

		Scanner sc = new Scanner(System.in);
        int length;
        char[] before, stack;
        ArrayList<Character> after = new ArrayList<Character>();

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            length = sc.nextInt();
            before = sc.next().toCharArray();

            stack = new char[length];
            int top = -1;

            // 후위 표기식으로 변환
            for(int i=0; i < before.length; i++) {
                // 숫자가 나오면 그대로 출력
                if(Character.isDigit(before[i])) {
                    after.add(before[i]);
                }
                // // ( * / 나오면 스택에 push
                // else if(before[i] == '(' || before[i] == '*' || before[i] == '/' ){
                //     stack[++top] = before[i];
                // }
                // + - 연산이 나오면 여는 괄호('('), 여는 괄호가 없다면 스택의 끝까지 출력하고 그 연산자를 스택에 push한다.
                else if(before[i] == '+' || before[i] == '-') {
                    if(top >= 0) {
                        while (top >= 0) {
                            after.add(stack[top--]);
                        }
                        // while(top >= 0 && stack[top]!='(') {
                        //     after.add(stack[top--]);
                        // }
                        // if(stack[top]=='(') top--;
                    }
                    stack[++top] = before[i];
                } 
                // else if(before[i] == ')') {
                //     while(stack[top]=='(') {
                //         after.add(stack[top--]);
                //     }
                //     top--;
                // }
            }

            while (top >= 0) {
                after.add(stack[top--]);
            }

            Stack<Integer> cal = new Stack<Integer>();
            for(int i=0; i < after.size(); i++) {
                if(Character.isDigit(after.get(i))) {
                    Integer num = after.get(i) - '0';
                   
                    cal.push(num);
                }
                else {
                    Integer res = cal.pop() + cal.pop();
                    cal.push(res);
                }
            }


            
			System.out.println("#" + test_case + " " + cal.pop());

		}
	}
}
