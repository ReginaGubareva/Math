import java.math.BigInteger;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Implement an expression evaluator which supports only addition (+) and multiplication (*) in Java (preferably),
 * or another language. The expression can have only digits, plus and multiplication symbols, and optional white-space.
 * Consider optimizing the algorithm for better performance. The challenge is to implement as CPU/RAM-efficient
 * algorithm as possible, and NOT to develop a calculator which is extensible to support other operations in future.
 * For example, calculate the result of “5 * 4 + 12”. Imagine receiving a very long expression like this - how it can
 * be calculated as efficiently as possible?
 *
 * The algorithm should work for O(n) time. I didn't find the way to do it faster.
 * @author Regina Gubareva
 * @since 31.07.2019
 */
public class RPN {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        //RPN - reverse polish notation
        Stack<String> stackRPN = convertToRPN(expression);

//        Collections.reverse(stackRPN);
//        stackRPN.stream().forEach(x -> System.out.print(x + " "));

        System.out.println("Result: " + calculate(stackRPN));
    }

    public static BigInteger calculate(Stack<String> stackRPN){
        Collections.reverse(stackRPN);
        Stack<BigInteger> stackCalc = new Stack<>();

        while (!stackRPN.isEmpty()) {
            String token = stackRPN.pop();

            if(isNumeric(token)){
                stackCalc.push(BigInteger.valueOf(Long.parseLong(token)));
            } else if (isOperator(token)){
                BigInteger operand1 = stackCalc.pop();
                BigInteger operand2 = stackCalc.pop();

                switch (token){
                    case "+" :
                        stackCalc.push(operand1.add(operand2));
                        break;
                    case "*" :
                        stackCalc.push(operand1.multiply(operand2));
                        break;
                }
            }
        }

        return stackCalc.pop();
    }

    public static Stack<String> convertToRPN(String expression){

        Stack<String> stackRPN = new Stack<>();
        Stack<String> symbolStack = new Stack<>();

        String[] arr = expression.split(" ");

        for(int i = 0; i < arr.length; i++) {
            if (arr[i].isEmpty()) {
                continue;
            } else if (isNumeric(arr[i])) {
                stackRPN.push(arr[i]);
            } else {
                if (symbolStack.isEmpty()) {
                    symbolStack.push(arr[i]);
                } else if (symbolStack.lastElement().equals("*")) {
                    stackRPN.push(symbolStack.pop());
                    symbolStack.push(arr[i]);
                } else {
                    symbolStack.push(arr[i]);
                }
            }
        }

        while (!symbolStack.isEmpty()){
            stackRPN.push(symbolStack.pop());
        }
        return stackRPN;
    }

    public static boolean isNumeric(String s){
        return s.chars().allMatch(Character::isDigit);
    }

    public static boolean isOperator(String s){
        return s.equals("*") | s.equals("+");
    }
}
