package TextException;

public class NumberException {
    public static boolean checkOnNumbers(String str){
        return str.matches("[0-9]+");
    }
}
