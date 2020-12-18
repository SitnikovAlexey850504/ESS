package Controller;

import Interface.FramesService;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Controller {
    public static void showFrame(JFrame frame){
        frame.setVisible(true);
    }

    public static void hideFrame(JFrame frame) { frame.setVisible(false); }

    public static void disposeFrame(JFrame frame) { frame.dispose(); }
}
