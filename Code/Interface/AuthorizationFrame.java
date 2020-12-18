package Interface;

import Controller.Authorization;
import Controller.Controller;
import DataBase.WorkOnDB;
import TextException.CountLimit;
import TextException.NumberException;
import TextException.RussianLetterException;
import TextException.StringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizationFrame extends JFrame {
    Dimension labelSize = new Dimension(80, 80);

    private JLabel loginLabel =  new JLabel("Логин");
    private JLabel passwordLabel =  new JLabel("Пароль");
    private JLabel errorLabel = new JLabel("");

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton button =  new JButton("Войти");

    private JCheckBox checkBox = new JCheckBox("студент");

    private Font font = new Font("Verdana", Font.ROMAN_BASELINE, 14);

    private String login = "s";
    private String password = "s";
    private int check = 0;

    private JFrame getFrame(){
        return this;
    }

    private ActionListener authorizationAction = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            errorLabel.setText("");

            if(loginField.getText().equals("") || passwordField.getText().equals("")){
                errorLabel.setText("Вы не ввели логин или пароль!");
                return;
            }

            WorkOnDB.establishConnection();

            if(checkBox.isSelected()){
                check = 1;
            }else {
                check = 0;
            }

            if(checkEquals() == false){
                errorLabel.setText("Введенные данные не корректны!");
                return;
            }

            int result = Authorization.authentificate(loginField.getText(), passwordField.getText(), check);

            if(result == 0){
                errorLabel.setText("Неверный логин или пароль!");
                return;
            }
            if(result == 1){
                Controller.disposeFrame(getFrame());
                String log = loginField.getText();
                JFrame studentFrame = FramesService.createFrame(FramesService.FramesType.STUDENT,  log);
                return;
            }
            if(result == 2){
                Controller.disposeFrame(getFrame());
                JFrame mainFrame = FramesService.createFrame(FramesService.FramesType.MAIN, null);
                return;
            }
        }
    };

    private boolean checkEquals(){
        boolean correct = true;

        if(StringException.checkOnString(loginField.getText()) == false) {correct = false; }
        if(StringException.checkOnString(passwordField.getText()) == false) {correct = false; }

        return correct;
    }

    private void setLabels(){
        getContentPane().add(loginLabel);
        loginLabel.setBounds(50, 70, 60, 30);
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(50, 110, 60, 30);
        getContentPane().add(errorLabel);
        errorLabel.setBounds(110, 165, 250, 20);
    }

    private void setFields(){
        getContentPane().add(loginField);
        loginField.setBounds(110,70, 300,30);
        getContentPane().add(passwordField);
        passwordField.setBounds(110,110, 300,30);

        loginField.setDocument(new CountLimit(35));
        passwordField.setDocument(new CountLimit(35));
    }

    private void setButtons(){
        getContentPane().add(button);
        button.setBounds(329, 145, 80, 20);
        button.addActionListener(authorizationAction);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.RED);
    }

    private void setCheckBox(){
        getContentPane().add(checkBox);
        checkBox.setBounds(110, 145, 100, 20);
        checkBox.setBackground(new Color(255, 255, 255));
    }

    private void setFonts(){
        loginLabel.setFont(font);
        passwordLabel.setFont(font);

        loginField.setFont(font);
        passwordField.setFont(font);

        button.setFont(font);
    }

    public AuthorizationFrame(){};

    public AuthorizationFrame(String name){
        super(name);

        Controller.showFrame(this);
        this.setSize(480, 270);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        getContentPane().setBackground(new Color(255, 255, 255));
        this.setLayout(null);

        setLabels();
        setFields();
        setButtons();
        setCheckBox();
        setFonts();
    }
}