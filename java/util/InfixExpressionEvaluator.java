package util;

import java.util.*;

public class InfixExpressionEvaluator {

    // Operator Priority
    private static final Map<Character, Integer> OPERATOR_PRECEDENCE = new HashMap<>();

    static {
        OPERATOR_PRECEDENCE.put('+', 1);
        OPERATOR_PRECEDENCE.put('-', 1);
        OPERATOR_PRECEDENCE.put('*', 2);
        OPERATOR_PRECEDENCE.put('/', 2);
    }

    public static double evaluate(String expression) {
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private static List<String> infixToPostfix(String expression) {
        // Used to store suffix expressions
        List<String> postfix = new ArrayList<>();
        // Used to store operators
        Deque<Character> operators = new ArrayDeque<>();
        
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/", true);

        boolean expectOperator = false; // Used to detect illegal expressions

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            
            if (token.isEmpty()) {
                continue;
            }

            if (isNumeric(token)) { // If it is a number
                postfix.add(token);
                expectOperator = true;
            } else if (isOperator(token.charAt(0))) { // If it is an operator
                char op = token.charAt(0);

                // Check if the expression is legal
                if (!expectOperator) {
                    throw new IllegalArgumentException("Invalid expression: Unexpected operator.");
                }

                while (!operators.isEmpty() &&
                       OPERATOR_PRECEDENCE.get(operators.peek()) >= OPERATOR_PRECEDENCE.get(op)) {
                    postfix.add(Character.toString(operators.pop()));
                }

                operators.push(op);
                expectOperator = false; // The next one needs to be a number or negative sign
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        // Add the remaining operators to the suffix expression
        while (!operators.isEmpty()) {
            postfix.add(Character.toString(operators.pop()));
        }

        return postfix;
    }
    private static double evaluatePostfix(List<String> postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String token : postfix) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else { // operator
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        if (operand2 == 0) {
                            throw new IllegalArgumentException("Division by zero.");
                        }
                        stack.push(operand1 / operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }
        return stack.pop();
    }
    private static boolean isOperator(char ch) {
        return "+-*/".indexOf(ch) != -1;
    }
    private static boolean isNumeric(String token) {
        return token.matches("-?\\d+");
    }
    public Integer result;
    public boolean ifLegal(String prefixExpression){
        prefixExpression="0"+prefixExpression;//Supports starting with negative numbers
        try {
             result = (int) evaluate(prefixExpression); //legal
            return true;
        } catch (IllegalArgumentException e) {  //ilegal
           return  false;
        }
    }



    public static void main(String[] args) {

        String expression = "-3 + 8/2"; // This is a valid infix expression
        try {
            double result = evaluate(expression);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
