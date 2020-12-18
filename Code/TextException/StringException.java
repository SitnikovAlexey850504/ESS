package TextException;

public class StringException {
    public static boolean checkOnString(String str){
        return str.matches("[a-zA-Zа-яА-Я0-9]+");
    }
}
