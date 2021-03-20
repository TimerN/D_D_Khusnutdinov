import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StringUnpacker {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        /* цикл для проверки строки на валидность */
        int leftBracketCounter = 0;
        int rightBrackerCounter = 0;
        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(k) > 47 && s.charAt(k) < 58 || s.charAt(k) > 96 && s.charAt(k) < 123 || s.charAt(k) == '[' || s.charAt(k) == ']') {
                if (s.charAt(k) == '[') {
                    leftBracketCounter++;
                }
                else if (s.charAt(k) == ']') {
                    rightBrackerCounter++;
                    if (leftBracketCounter < rightBrackerCounter) {
                        System.out.println("String is not valid!1"); //есть уже закрывающая скобка хотя не было открывающей
                        break;
                    }
                }
                if (k == s.length() - 1) {
                    if (leftBracketCounter == rightBrackerCounter) {
                /*строка валидна*/
                        Unpacker unpacker = new Unpacker(s);
                        System.out.println(unpacker.strUnpacked());
                    }
                    else {
                        System.out.println("String is not valid!2"); //открывающих скобок больше
                    }
                }
            }
            else {
                System.out.println("String is not valid!3"); //в строке есть другие символы кроме цифр, букв и скобок
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
                if (str.charAt(i) > 47 && str.charAt(i) < 58 && !(str.charAt(i + 1) > 47 && str.charAt(i + 1) < 58)) {
                    numbers = numbers + str.charAt(i);
                    num = Integer.parseInt(numbers);
                }
                else if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                    numbers = numbers + str.charAt(i);
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 && i == str.length() - 1) {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 && str.charAt(i+1) > 47 && str.charAt(i+1) < 58)  {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
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
