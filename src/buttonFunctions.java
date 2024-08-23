import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class buttonFunctions implements ActionListener {
    private JTextField textField;

    public buttonFunctions(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getText();

        if ("CE".equals(command)) {
            textField.setText("");
        } else if ("Del".equals(command)) {
            String text = textField.getText();
            if (!text.isEmpty()) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        } else if ("=".equals(command)) {
            try {
                String expression = textField.getText();
                double result = evaluateExpression(expression);
                textField.setText(String.valueOf(result));
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else if ("sqrt".equals(command)) {
            try {
                double value = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(Math.sqrt(value)));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else if ("pow".equals(command)) {
            try {
                String[] parts = textField.getText().split(",");
                if (parts.length == 2) {
                    double base = Double.parseDouble(parts[0]);
                    double exponent = Double.parseDouble(parts[1]);
                    textField.setText(String.valueOf(Math.pow(base, exponent)));
                } else {
                    textField.setText("Error");
                }
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else if ("sin".equals(command)) {
            try {
                double value = Math.toRadians(Double.parseDouble(textField.getText()));
                textField.setText(String.valueOf(Math.sin(value)));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else if ("cos".equals(command)) {
            try {
                double value = Math.toRadians(Double.parseDouble(textField.getText()));
                textField.setText(String.valueOf(Math.cos(value)));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else if ("tan".equals(command)) {
            try {
                double value = Math.toRadians(Double.parseDouble(textField.getText()));
                textField.setText(String.valueOf(Math.tan(value)));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else if ("log".equals(command)) {
            try {
                double value = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(Math.log10(value)));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else {
            textField.setText(textField.getText() + command);
        }
    }

    private double evaluateExpression(String expression) {
        // Implement a simple parser and evaluator for the expression
        try {
            return evaluatePostfix(infixToPostfix(expression));
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating expression", e);
        }
    }

    private String infixToPostfix(String infix) {
        // Convert infix expression to postfix notation
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(' ').append(stack.pop());
                }
                stack.pop(); // Remove '(' from stack
            } else {
                postfix.append(' ');
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(' ').append(stack.pop());
        }
        return postfix.toString();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix.split(" ")) {
            if (token.isEmpty()) continue;
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }
}
