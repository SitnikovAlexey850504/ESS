package Interface;

import Controller.Controller;
import Student.StudentsService;
import TextException.CountLimit;
import TextException.StringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordFrame extends JFrame {
    private JLabel passwordLabel = new JLabel("Введите пароль");
    private JLabel passwordLabel2 = new JLabel("Повторите пароль");
    private JLabel errorLabel = new JLabel("");

    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField passwordField2 = new JPasswordField();

    private JButton saveButton = new JButton("Сохранить");

    private Font font = new Font("Arial", Font.ROMAN_BASELINE, 14);

    String card;

    private JFrame getFrame(){
        return this;
    }

    private ActionListener saveAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(checkEquals() == false){
                errorLabel.setText("Введенные данные не корректны!");
                return;
            }

            if(passwordField.getText().equals(passwordField2.getText())){
                StudentsService.changeStudentInformation(passwordField.getText(), card, "password");
                Controller.disposeFrame(getFrame());
            }
            else{
                errorLabel.setText("Введенные пароли не совпадают");
            }
        }
    };

    private boolean checkEquals(){
        boolean correct = true;

        if(StringException.checkOnString(passwordField.getText()) == false) { correct = false; }
        if(StringException.checkOnString(passwordField2.getText()) == false) { correct = false; }

        return correct;
    }

    private void setFont(){
        passwordLabel.setFont(font);
        passwordLabel2.setFont(font);
        //errorLabel.setFont(font);

        passwordField.setFont(font);
        passwordField2.setFont(font);
    }

    private void setLabels(){
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(10, 70, 130, 30);
        getContentPane().add(passwordLabel2);
        passwordLabel2.setBounds(10, 110, 130, 30);
        getContentPane().add(errorLabel);
        errorLabel.setBounds(10, 150, 250, 30);
    }

    private void setFields(){
        getContentPane().add(passwordField);
        passwordField.setBounds(140,70, 310,30);
        getContentPane().add(passwordField2);
        passwordField2.setBounds(140,110, 310,30);

        passwordField.setDocument(new CountLimit(35));
        passwordField2.setDocument(new CountLimit(35));
    }

    private void setButton(){
        getContentPane().add(saveButton);
        saveButton.setBounds(330, 200, 120, 20);
        saveButton.addActionListener(saveAction);
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(Color.RED);
    }

    public PasswordFrame(String name, String studentCard){
        super(name);

        Controller.showFrame(this);
        this.setSize(480, 270);
        this.setLocation(600, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        this.setLayout(null);

        setFont();
        setLabels();
        setFields();
        setButton();

        card = studentCard;
    }
}
