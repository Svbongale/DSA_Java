import java.util.Stack;

public class ValidParenthesis {

    public static void main(String[] args) {
        ValidParenthesisImpl obj = new ValidParenthesisImpl();

        char[] chars = {'{','[', ']','}'};

        boolean retVal = obj.isValidParenthesis(chars);

        System.out.println(retVal);
    }
}


class ValidParenthesisImpl {

    public boolean isValidParenthesis(char[] brackets) {

        Stack<Character> parenthesis = new Stack<>();

        for (char bracket: brackets) {
            // opening brackets {,[,(
            switch (bracket) {
                case '(': parenthesis.push(')');
                case '[': parenthesis.push(']');
                case '{': parenthesis.push('}');
                default: break;
            }

            if ((bracket == ')') ||(bracket == ']') || (bracket == '}')) {
                return parenthesis.isEmpty() || parenthesis.pop() != bracket;
            }

            System.out.println(parenthesis);

        }
        return parenthesis.isEmpty();
    }
}