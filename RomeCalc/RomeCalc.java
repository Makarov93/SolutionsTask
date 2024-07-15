package RomeCalc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RomeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);

        System.out.print("Введите арифметическое выражение: ");
        String input = scanner.nextLine().trim();

        String operation = findOperation(input);
        int operand1 = extractOperand(input, operation, romanNumerals);
        int operand2 = extractOperand(input.substring(input.indexOf(operation) + 1), operation, romanNumerals);

        if (operand1 == -1 || operand2 == -1) {
            System.out.println("Ошибка: некорректный ввод.");
            return;
        }

        int result = evaluate(operand1, operation, operand2);

        if (isRomanInput(input) && isRomanNumeral(result)) {
            String romanResult = toRomanNumeral(result);
            System.out.println("Результат: " + romanResult);
        } else if (!isRomanInput(input) && !isRomanNumeral(result)) {
            System.out.println("Результат: " + result);
        } else {
            System.out.println("Ошибка: недопустимая комбинация римских и арабских цифр.");
        }
    }

    static String findOperation(String input) {
        if (input.contains("+")) {
            return "+";
        } else if (input.contains("-")) {
            return "-";
        } else if (input.contains("*")) {
            return "*";
        } else if (input.contains("/")) {
            return "/";
        } else {
            return "";
        }
    }

    static int extractOperand(String input, String operation, Map<String, Integer> romanNumerals) {
        String[] tokens = input.split("\\Q" + operation + "\\E");
        String operand = tokens[0].trim();

        if (isRomanInput(operand)) {
            return romanNumerals.getOrDefault(operand, -1);
        } else {
            try {
                return Integer.parseInt(operand);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    static boolean isRomanInput(String input) {
        return input.matches(".*[IVX]+.*");
    }

    static boolean isRomanNumeral(int number) {
        return number >= 1 && number <= 10;
    }

    static String toRomanNumeral(int number) {
        String[] romanNumerals = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return romanNumerals[number];
    }

    static int evaluate(int operand1, String operation, int operand2) {
        return switch (operation) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> 0;
        };
    }
}
