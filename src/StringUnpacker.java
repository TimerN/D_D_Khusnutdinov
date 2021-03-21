import java.io.BufferedReader;
import java.io.InputStreamReader;


public class StringUnpacker {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        /* цикл для проверки строки на валидность */
        int leftBracketCounter = 0;               // счетчик левых скобок
        int rightBracketCounter = 0;              // счетчик правых скобок
        int flagOfBracketEnclosure = 1;           // флаг того, есть вложенные скобки или нет
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
                    else if (!((s.charAt(k + 1) > 96 && s.charAt(k + 1) < 123) || Character.isDigit(s.charAt(k + 1)))) {
                        System.out.println("String is not valid!4"); // после скобки не буква
                        break;
                    }
                    leftBracketCounter++;
                    if (leftBracketCounter - rightBracketCounter > flagOfBracketEnclosure) {
                        flagOfBracketEnclosure = leftBracketCounter - rightBracketCounter;
                    }
                }
                else if (s.charAt(k) == ']') {
                    rightBracketCounter++;
                    if (leftBracketCounter < rightBracketCounter) {
                        System.out.println("String is not valid!5"); //есть уже закрывающая скобка хотя не было открывающей
                        break;
                    }
                }
                if (k == s.length() - 1) {
            /*ниже обработка валидной строки*/
                    if (leftBracketCounter == rightBracketCounter) {
                        if (leftBracketCounter == 0) {
                            System.out.println(s);                       //строка без скобок
                        }
                        else if (flagOfBracketEnclosure == 1) {
                            Unpacker unpacker = new Unpacker(s);
                            System.out.println(unpacker.strUnpacked());  //строка без вложенных скобок,т.к. флаг не поднялся
                        }
                        else {
                            /*цикл распаковывает (с помощью базового метода) каждую подстроку без вложенных скобок и перезаписывает в начальную строку*/
                            StringBuffer sb = new StringBuffer(s);                                      //используем StringBuffer, чтобы можно было применить replace
                            for (int m = 0; m < leftBracketCounter; m++) {
                                int firstRigBr = sb.indexOf("]");                                       //индекс первой правой скобки
                                int leftBrEncl = sb.substring(0, firstRigBr + 1).lastIndexOf("[");  //индекс первой левой от первой правой скобки
                                int leftbr = sb.substring(0,leftBrEncl).lastIndexOf("[");           //индекс второй левой от правой скобки
                                String subs = sb.substring(leftbr + 1, firstRigBr + 1);                 //подстрока без вложенных скобок
                                Unpacker unpacker = new Unpacker(subs);
                                subs = unpacker.strUnpacked();                                          //распаковка
                                sb.replace(leftbr + 1, firstRigBr + 1, subs);                  //замена подстроки на распакованную
                            }
                            System.out.println(sb);                       //строка с вложенными скобками
                        }
                    }
            /*выше обработка валидной строки*/
                    else {
                        System.out.println("String is not valid!6"); //открывающих скобок больше
                    }
                }
            }
            else {
                System.out.println("String is not valid!7"); //в строке есть другие символы кроме цифр, букв и скобок
                break;
            }
        }
    }
    public static class Unpacker {
        public String str;

        public Unpacker(String s) {
            str = s;
        }

        /* метод для распаковки базовой строки без вложенных скобок*/
        public String strUnpacked() {
            String numbers = "";            //строка для записи числа перед скобкой
            String symbols = "";            //строка для записи латиницы
            String unpackedStr = "";        //строка для записи распакованной строки
            int num = 1;                    //переменная для записи числа перед скобкой
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
                else if (str.charAt(i) > 96 && str.charAt(i) < 123 &&  Character.isDigit(str.charAt(i + 1))) {
                    symbols = symbols + str.charAt(i);
                    for (int j = 0; j < num; j++) {
                        unpackedStr = unpackedStr + symbols;
                    }
                    num = 1;
                    numbers = "";
                    symbols = "";
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
            }
            return unpackedStr;
        }
    }
}
