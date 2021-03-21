import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StringUnpacker {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        /* цикл для проверки строки на валидность */
        int leftBracketCounter = 0;
        int rightBracketCounter = 0;
        for (int k = 0; k < s.length(); k++) {
            if (Character.isDigit(s.charAt(k)) || s.charAt(k) > 96 && s.charAt(k) < 123 || s.charAt(k) == '[' || s.charAt(k) == ']') {
                if (Character.isDigit(s.charAt(k))) {
                    if (!(Character.isDigit(s.charAt(k + 1)) || s.charAt(k + 1) == '[')) {
                        System.out.println("String is not valid!1"); // после числа идет не открывающая скобка
                        break;
                    }
                }
                if (s.charAt(k) == '[') {
                    if (k == 0) {
                        System.out.println("String is not valid!2"); // скобка вместо числа вначале строки
                        break;
                    }
                    else if (!(Character.isDigit(s.charAt(k - 1)))) {
                        System.out.println("String is not valid!3"); // перед скобкой не число
                        break;
                    }
                    leftBracketCounter++;
                }
                else if (s.charAt(k) == ']') {
                    rightBracketCounter++;
                    if (leftBracketCounter < rightBracketCounter) {
                        System.out.println("String is not valid!4"); //есть уже закрывающая скобка хотя не было открывающей
                        break;
                    }
                }
                if (k == s.length() - 1) {
                    if (leftBracketCounter == rightBracketCounter) {
                        /*строка валидна*/
                        if (leftBracketCounter == 0) {
                            System.out.println(s);
                        }
                        else {
                            Unpacker unpacker = new Unpacker(s);
                            System.out.println(unpacker.strUnpacked());
                        }

                    }
                    else {
                        System.out.println("String is not valid!5"); //открывающих скобок больше
                    }
                }
            }
            else {
                System.out.println("String is not valid!6"); //в строке есть другие символы кроме цифр, букв и скобок
                break;
            }
        }
    }
    public static class Unpacker {
        public String str;

        public Unpacker(String s) {
            str = s;
        }

        /* метод для распаковки строки без скобок*/
        public String strUnpacked() {
            String numbers = "";
            String symbols = "";
            String unpackedStr = "";
            int num = 1;
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) && str.charAt(i + 1) == '[') {
                    numbers = numbers + str.charAt(i);
                    num = Integer.parseInt(numbers);
                }
                else if (Character.isDigit(str.charAt(i))) {
                    numbers = numbers + str.charAt(i);
                }
                else if (str.charAt(i) == '[' || str.charAt(i) == ']') {
                    continue;
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 &&  i == str.length() - 1) {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
                    num = 1;
                    numbers = "";
                    symbols = "";
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 && str.charAt(i + 1) == ']' ) {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
                    num = 1;
                    numbers = "";
                    symbols = "";
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123) {
                    symbols = symbols + str.charAt(i);
                }
                else {
                    unpackedStr = "Boom";
                    break;
                }
            }
            return unpackedStr;
        }
    }
}
