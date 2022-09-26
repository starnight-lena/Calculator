import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Calculator {

    public static void main(String[] args) throws MyException{

        String[] normal_tests = {"1 + 2", "10 * 4", "3 - 10", "3 / 10", "I + IV", "II * IV", "VI / III", "X - I"};
        int i = 1;
        System.out.println("---------------------------------------------------");
        System.out.print(Color.YELLOW);
        System.out.println("NORMAL TESTS");
        System.out.print(Color.RESET);
        System.out.println("---------------------------------------------------");

        for(String x:normal_tests) {

            System.out.print(Color.GREEN);
            System.out.println("Тест №" + i);
            System.out.print(Color.RESET);

            System.out.print(x + " = ");
            test(x);
            System.out.println("---------------------------------------------------");
            i++;
        }

        String[] failed_tests = {"I - II", "I + 1", "1", "1 + 2 + 3"};
        //выбираем какой тест хотим проверить
        String fail = failed_tests[3];
        System.out.print(Color.RED);
        System.out.println("FAIL TESTS");
        System.out.print(Color.RESET);
        System.out.println("---------------------------------------------------");
        System.out.println(fail);
        test(fail);
        System.out.println("---------------------------------------------------");
    }
    static void test(String input) throws MyException {
        if (!exam1(input)){
            throw new MyException("throws Exception //т.к. строка не является математической операцией");
        }
        else if (!exam2(input)){
            throw new MyException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if (!exam3(input)){
            throw new MyException("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        else{
            System.out.println(calc(input));

        }

    }

    public static String calc(String input) throws MyException{

        String[] expr = input.split(" ");
        String oper = expr[1];
        int numb1;
        int numb2;

        if ("12345678910".contains(expr[0])) {
            numb1 = Integer.parseInt (expr[0]);
            numb2 = Integer.parseInt (expr[2]);
        }
        else {
            numb1 = RomeNumber.valueOf(expr[0]).getDecimalNumb();
            numb2 = RomeNumber.valueOf(expr[2]).getDecimalNumb();

        }

        int result = 0;
        switch (oper){
            case "+":
                result = numb1 + numb2;
                break;
            case "/":
                result = numb1 / numb2;
                break;
            case "*":
                result = numb1 * numb2;
                break;
            case "-":
                result = numb1 - numb2;
                break;
            default:
                result = -1;


        }
        if (!"12345678910".contains(expr[0]) && result < 0) {
            throw new MyException("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }
        else if (!"12345678910".contains(expr[0]))
        {
            Converter res = new Converter();
            return res.intToRoman(result);

        }
        else

        {
            return "" + result;
        }

    }

    static boolean exam1(String input){
        return input.contains("+") || input.contains("/") || input.contains("-") || input.contains("*");
    }

    static boolean exam2(String input){
        return input.length() - 1 == input.replace("-", "").replace("*", "").replace("/", "").replace("+", "").length();
    }
    static boolean exam3(String input){
        String[] expr = input.split(" ");
        return "12345678910".contains(expr[0]) && "12345678910".contains(expr[2]) || !"12345678910".contains(expr[0]) && !"12345678910".contains(expr[2]);
    }
    static class MyException extends Exception {
        MyException(String description){
            super(description);
        }

    }

    enum RomeNumber{
        I(1), II(2), III(3), IV(4),
        V(5), VI(6), VII(7), VIII(8),
        IX(9), X(10);

        private int decimalNumb;

        RomeNumber(int decimalNumb){
            this.decimalNumb = decimalNumb;

        }

        public int getDecimalNumb() {
            return decimalNumb;
        }

    }
    static class Converter {

        public static String intToRoman(int number) {
            if (number >= 4000 || number <= 0)
                return null;
            StringBuilder result = new StringBuilder();
            for(Integer key : units.descendingKeySet()) {
                while (number >= key) {
                    number -= key;
                    result.append(units.get(key));
                }
            }
            return result.toString();
        }

        private static final NavigableMap<Integer, String> units;
        static {
            NavigableMap<Integer, String> initMap = new TreeMap<>();
            initMap.put(1000, "M");
            initMap.put(900, "CM");
            initMap.put(500, "D");
            initMap.put(400, "CD");
            initMap.put(100, "C");
            initMap.put(90, "XC");
            initMap.put(50, "L");
            initMap.put(40, "XL");
            initMap.put(10, "X");
            initMap.put(9, "IX");
            initMap.put(5, "V");
            initMap.put(4, "IV");
            initMap.put(1, "I");
            units = Collections.unmodifiableNavigableMap(initMap);
        }
    }

    enum Color {
        //Color end string, color reset
        RESET("\033[0m"),
        YELLOW ("\u001B[33m"),
        RED ("\u001B[31m"),

        GREEN("\033[0;32m");  // GREEN

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }


}