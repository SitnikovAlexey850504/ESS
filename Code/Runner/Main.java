package Runner;

import Controller.Controller;
import Interface.AuthorizationFrame;
import Interface.FramesService;

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        JFrame authorizationFrame = FramesService.createFrame(FramesService.FramesType.AUTHORIZATION, null);
    }
}