import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StringUnpacker {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int k = 0;
        int leftBracketCounter = 0;
        int rightBrackerCounter = 0;
        while (k < s.length()) {
            if (s.charAt(k) > 47 && s.charAt(k) < 58 || s.charAt(k) > 96 && s.charAt(k) < 123 || s.charAt(k) == '[' || s.charAt(k) == ']') {
                if (s.charAt(k)=='[') {
                    leftBracketCounter++;
                }
                else if (s.charAt(k)==']') {
                    rightBrackerCounter++;
                    if (leftBracketCounter < rightBrackerCounter) {
                        System.out.println("String is not valid!2");
                        break;
                    }
                }
                if (k == s.length() - 1) {
                    if (leftBracketCounter == rightBrackerCounter) {
                        Unpacker unpacker = new Unpacker(s);
                        System.out.println(unpacker.strUnpacked());
                        System.out.println("Boom");
                    }
                    else {
                        System.out.println("String is not valid!3");
                    }
                }
                k++;
            }
            else {
                System.out.println("String is not valid!4");
                break;
            }
        }
    }
    public static class Unpacker {
        public String str;

        public Unpacker(String s) {
            str = s;
        }

        public String strUnpacked() {
            String numbers = "";
            String symbols = "";
            String unpackedStr = "";
            int num = 1;
            int i = 0;
            while (i < str.length()) {
                if (str.charAt(i) > 47 && str.charAt(i) < 58 && !(str.charAt(i + 1) > 47 && str.charAt(i + 1) < 58)) {
                    numbers = numbers + str.charAt(i);
                    num = Integer.parseInt(numbers);
                    i++;
                }
                else if (str.charAt(i) > 47 && str.charAt(i) < 58) {
                    numbers = numbers + str.charAt(i);
                    i++;
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 && i == str.length() - 1) {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }

                    i++;
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 && str.charAt(i+1) > 47 && str.charAt(i+1) < 58)  {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
                    numbers = "";
                    symbols = "";
                    i++;
                }
                else if (str.charAt(i) > 96 && str.charAt(i) < 123) {
                    symbols = symbols + str.charAt(i);
                    i++;
                }
                else {
                    unpackedStr = "String is not valid!1";
                    break;
                }
            }
            return unpackedStr;
        }
    }

}
