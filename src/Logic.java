import java.util.*;

public class Logic {
    static List<String> openSymbols = Arrays.asList("(", "{", "[");
    static List<String> closeSymbols = Arrays.asList(")", "}", "]");
    static List<String> pairSymbols = Arrays.asList("()", "{}", "[]");

    public static boolean validateOpen(String symbol) {
        return openSymbols.contains(symbol);
    }

    public static boolean validateClose(String symbol) {
        return closeSymbols.contains(symbol);
    }

    public static boolean validateLast(String currentSymbol, String lastSymbol) {
        return pairSymbols.contains(currentSymbol + lastSymbol);
    }

    public static boolean validateNext(String currentSymbol, String nextSymbol) {
        return pairSymbols.contains(currentSymbol + nextSymbol);
    }

    public static boolean validateChain(List<String> input) {
        int lastIndex = input.size()-1;

        if(input.isEmpty()) return false;
        if(input.size() % 2 != 0) return false;

        for(int currentIndex = 0; currentIndex < input.size()-1; currentIndex++) {

            String currentSymbol = input.get(currentIndex);
            String nextSymbol = input.get(currentIndex+1);
            String lastSymbol = input.get(lastIndex);

            if (validateNext(currentSymbol, nextSymbol)) {
                currentIndex += 1;
            }

            if (!validateNext(currentSymbol, nextSymbol) && validateClose(lastSymbol)) {
                if (!validateLast(currentSymbol, lastSymbol)) {
                    return false;
                }
            }
            lastIndex--;
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> inputString = Arrays.asList("(", ")", "{", "}", "[", "]");
        System.out.println(validateChain(inputString));
        System.out.println(validateChain(Arrays.asList("{", "}", "{", "}", ")", ")")));
        System.out.println(validateChain(Arrays.asList("{", "]", "{", "}", "[", "]")));
        System.out.println(validateChain(Arrays.asList("{", "}", "[", "]")));
        System.out.println(validateChain(Arrays.asList("(", "{", "{", "}", "}", ")")));
        System.out.println(validateChain(Arrays.asList("(", "{", "[", "]", "}", ")")));
    }
}