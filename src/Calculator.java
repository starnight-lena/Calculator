public class Calculator {

    public static void main(String[] args) throws MyException{

        String[] tests = {"1 + 2", "VI / III", "I - II", "I + 1"};

        for(String x:tests) {
            test(x);
        }
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
        else{
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


}