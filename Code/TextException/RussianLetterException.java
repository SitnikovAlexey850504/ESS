package TextException;

public class RussianLetterException {
    public static boolean checkOnRussianLetters(String str){
        if(str.isEmpty())
            return true;

        return str.matches("[а-яА-Я .]+");
    }
}
