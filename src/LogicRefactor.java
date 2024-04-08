import java.util.*;

public class LogicRefactor {
    static Map<String, String> pairs = new HashMap<>();

    static {
        pairs.put("(", ")");
        pairs.put("{", "}");
        pairs.put("[", "]");
    }

    public static boolean isOpenSymbol(String symbol) {
        return pairs.containsKey(symbol);
    }

    public static boolean isCloseSymbol(String symbol) {
        return pairs.containsValue(symbol);
    }

    public static boolean isValidPair(String openSymbol, String closeSymbol) {
        return pairs.get(openSymbol).equals(closeSymbol);
    }

    public static boolean isValidChain(List<String> symbols) {
        Stack<String> stack = new Stack<>();

        for (String symbol : symbols) {
            if (isOpenSymbol(symbol)) {
                stack.push(symbol);
            } else if (isCloseSymbol(symbol)) {
                if (stack.isEmpty() || !isValidPair(stack.pop(), symbol)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        List<String> inputString = Arrays.asList("(", ")", "{", "}", "[", "]");
        System.out.println(isValidChain(inputString));
        System.out.println(isValidChain(Arrays.asList("{", "}", "{", "}", ")", ")")));
        System.out.println(isValidChain(Arrays.asList("{", "]", "{", "}", "[", "]")));
        System.out.println(isValidChain(Arrays.asList("{", "}", "[", "]")));
        System.out.println(isValidChain(Arrays.asList("(", "{", "{", "}", "}", ")")));
        System.out.println(isValidChain(Arrays.asList("(", "{", "[", "]", "}", ")")));
    }
}
