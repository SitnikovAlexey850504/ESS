package Interface;

import javax.swing.*;

public class FramesService {
    public static JFrame createFrame(FramesType type, String str){
        JFrame frame = null;

        switch (type){
            case AUTHORIZATION:
                frame = createAuthorizationFrame();
                break;
            case MAIN:
                frame = createMainFrame();
                break;
            case PASSWORD:
                frame = createPasswordFrame(str);
                break;
            case REGISTRATION:
                frame = createRegistrationFrame();
                break;
            case STUDENT_INFORMATION:
                frame = createStudentInformationFrame(str);
                break;
            case STUDENT:
                frame = createStudentFrame(str);
                break;
            default:
                throw new IllegalArgumentException("Wrong Frame type: " + type);
        }

        return frame;
    }

    public enum FramesType{
        AUTHORIZATION,
        MAIN,
        PASSWORD,
        REGISTRATION,
        STUDENT_INFORMATION,
        STUDENT
    }

    private static JFrame createAuthorizationFrame(){
        JFrame frame = new AuthorizationFrame("ESS");

        return frame;
    }

    private static JFrame createMainFrame(){
        JFrame frame = new MainFrame("ESS");

        return frame;
    }

    private static JFrame createPasswordFrame(String str){
        JFrame frame = new PasswordFrame("ESS", str);

        return frame;
    }

    private static JFrame createRegistrationFrame(){
        JFrame frame = new RegistrationFrame("ESS");

        return frame;
    }

    private static JFrame createStudentInformationFrame(String str){
        JFrame frame = new StudentInformationFrame("ESS", str);

        return frame;
    }

    private static JFrame createStudentFrame(String str){
        JFrame frame = new StudentFrame("ESS", str);

        return frame;
    }
}
