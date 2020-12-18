package Interface;

import Controller.Controller;
import Student.Student;
import Student.StudentsService;
import TextException.CountLimit;
import TextException.RussianLetterException;
import TextException.NumberException;
import TextException.StringException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegistrationFrame extends JFrame {
    private Image image;

    private JLabel surnameLabel = new JLabel("Фамилия");
    private JLabel nameLabel = new JLabel("Имя");
    private JLabel patronymicLabel = new JLabel("Отчество");
    private JLabel facultyLabel = new JLabel("Факультет");
    private JLabel specialtyLabel = new JLabel("Специальность");
    private JLabel courseLabel = new JLabel("Курс");
    private JLabel groupLabel = new JLabel("Группа");
    private JLabel formLabel = new JLabel("Форма обучения");
    private JLabel studentCardLabel = new JLabel("Студенческий");
    private JLabel loginLabel = new JLabel("Логин");
    private JLabel passwordLabel = new JLabel("Пароль");
    private JLabel passwordLabel2 = new JLabel("Повторите пароль");
    private JLabel errorLabel = new JLabel("");

    private JTextField surnameField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField patronymicField = new JTextField();
    private JTextField facultyField = new JTextField();
    private JTextField specialtyField = new JTextField();
    private JTextField courseField = new JTextField();
    private JTextField groupField = new JTextField();
    private JTextField formField = new JTextField();
    private JTextField studentCardField = new JTextField();
    private JTextField loginField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JTextField passwordField2 = new JTextField();

    private JButton saveButton = new JButton("Сохранить");

    private Font font = new Font("Verdana", Font.ROMAN_BASELINE, 14);

    private JFrame getFrame(){
        return this;
    }

    private ActionListener saveStudentAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(surnameField.getText().equals("") || nameField.getText().equals("") || patronymicField.getText().equals("") || facultyField.getText().equals("") ||
                    specialtyField.getText().equals("") || courseField.getText().equals("") || groupField.getText().equals("") || formField.getText().equals("") ||
                    studentCardField.getText().equals("") || loginField.getText().equals("") || passwordField.getText().equals("") || passwordField2.getText().equals("")) {
                errorLabel.setText("Вы заполнили не все поля!");
            }

            if(checkEquals() == false){
                errorLabel.setText("Введенные данные не корректны!");
                return;
            }

            if (passwordField.getText().equals(passwordField2.getText())) {
                Student student = new Student();

                student.setSurname(surnameField.getText());
                student.setName(nameField.getText());
                student.setPatronymic(patronymicField.getText());
                student.setFaculty(facultyField.getText());
                student.setSpecialty(specialtyField.getText());
                student.setCourse(courseField.getText());
                student.setGroup(groupField.getText());
                student.setForm(formField.getText());
                student.setStudentCard(studentCardField.getText());
                String login = loginField.getText();
                String password = passwordField.getText();

                StudentsService.addStudent(student, login, password);

                Controller.disposeFrame(getFrame());
            } else {
                errorLabel.setText("Пароли не совпадают!");
            }
        }
    };

    private boolean checkEquals(){
        boolean correct = true;

        if(RussianLetterException.checkOnRussianLetters(surnameField.getText()) == false) { correct = false; }
        if(RussianLetterException.checkOnRussianLetters(nameField.getText()) == false) {correct = false; }
        if(RussianLetterException.checkOnRussianLetters(patronymicField.getText()) == false) { correct = false; }
        if(RussianLetterException.checkOnRussianLetters(facultyField.getText()) == false) { correct = false; }
        if(RussianLetterException.checkOnRussianLetters(specialtyField.getText()) == false) { correct = false; }
        if(NumberException.checkOnNumbers(courseField.getText()) == false){ correct = false; }
        if(NumberException.checkOnNumbers(groupField.getText()) == false){ correct = false; }
        if(RussianLetterException.checkOnRussianLetters(formField.getText()) == false) { correct = false; }
        if(NumberException.checkOnNumbers(studentCardField.getText()) == false){ correct = false; }
        if(NumberException.checkOnNumbers(loginField.getText()) == false) { correct = false; }
        if(StringException.checkOnString(passwordField.getText()) == false) { correct = false; }
        if(StringException.checkOnString(passwordField2.getText()) == false) { correct = false; }

        return correct;
    }

    private void setImage(){
        String path = "D:\\5sem\\TRITPO\\image.png";
        File file = new File(path);

        try{
            image = ImageIO.read(file);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        getContentPane().add(imageLabel);
        imageLabel.setBounds(20, 20, 200, 200);
    }

    private void setFonts(){
        surnameLabel.setFont(font);
        nameLabel.setFont(font);
        patronymicLabel.setFont(font);
        facultyLabel.setFont(font);
        specialtyLabel.setFont(font);
        courseLabel.setFont(font);
        groupLabel.setFont(font);
        formLabel.setFont(font);
        studentCardLabel.setFont(font);
        loginLabel.setFont(font);
        passwordLabel.setFont(font);
        passwordLabel2.setFont(font);

        surnameField.setFont(font);
        nameField.setFont(font);
        patronymicField.setFont(font);
        facultyField.setFont(font);
        specialtyField.setFont(font);
        courseField.setFont(font);
        groupField.setFont(font);
        formField.setFont(font);
        studentCardField.setFont(font);
        loginField.setFont(font);
        passwordField.setFont(font);
        passwordField2.setFont(font);

        saveButton.setFont(font);
    }

    private void setLabels(){
        getContentPane().add(surnameLabel);
        surnameLabel.setBounds(250, 20, 140, 30);
        getContentPane().add(nameLabel);
        nameLabel.setBounds(250, 60, 140, 30);
        getContentPane().add(patronymicLabel);
        patronymicLabel.setBounds(250, 100, 140, 30);
        getContentPane().add(facultyLabel);
        facultyLabel.setBounds(250, 140, 140, 30);
        getContentPane().add(specialtyLabel);
        specialtyLabel.setBounds(250, 180, 140, 30);
        getContentPane().add(courseLabel);
        courseLabel.setBounds(250, 220, 140, 30);
        getContentPane().add(groupLabel);
        groupLabel.setBounds(250, 260, 140, 30);
        getContentPane().add(formLabel);
        formLabel.setBounds(250, 300, 140, 30);
        getContentPane().add(studentCardLabel);
        studentCardLabel.setBounds(250, 340, 140, 30);
        getContentPane().add(loginLabel);
        loginLabel.setBounds(250, 380, 140, 30);
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(250, 420, 140, 30);
        getContentPane().add(passwordLabel2);
        passwordLabel2.setBounds(250, 460, 140, 30);
        getContentPane().add(errorLabel);
        errorLabel.setBounds(730, 440, 220, 30);
    }

    private void setFields(){
        getContentPane().add(surnameField);
        surnameField.setBounds(400, 20, 320, 30);
        getContentPane().add(nameField);
        nameField.setBounds(400, 60, 320, 30);
        getContentPane().add(patronymicField);
        patronymicField.setBounds(400, 100, 320, 30);
        getContentPane().add(facultyField);
        facultyField.setBounds(400, 140, 320, 30);
        getContentPane().add(specialtyField);
        specialtyField.setBounds(400, 180, 320, 30);
        getContentPane().add(courseField);
        courseField.setBounds(400, 220, 320, 30);
        getContentPane().add(groupField);
        groupField.setBounds(400, 260, 320, 30);
        getContentPane().add(formField);
        formField.setBounds(400, 300, 320, 30);
        getContentPane().add(studentCardField);
        studentCardField.setBounds(400, 340, 320, 30);
        getContentPane().add(loginField);
        loginField.setBounds(400, 380, 320, 30);
        getContentPane().add(passwordField);
        passwordField.setBounds(400, 420, 320, 30);
        getContentPane().add(passwordField2);
        passwordField2.setBounds(400, 460, 320, 30);

        surnameField.setDocument(new CountLimit(35));
        nameField.setDocument(new CountLimit(35));
        patronymicField.setDocument(new CountLimit(35));
        facultyField.setDocument(new CountLimit(10));
        specialtyField.setDocument(new CountLimit(10));
        courseField.setDocument(new CountLimit(1));
        groupField.setDocument(new CountLimit(6));
        formField.setDocument(new CountLimit(10));
        studentCardField.setDocument(new CountLimit(8));
        loginField.setDocument(new CountLimit(35));
        passwordField.setDocument(new CountLimit(35));
    }

    private void setButton(){
        getContentPane().add(saveButton);
        saveButton.setBounds(810, 470, 120, 20);
        saveButton.addActionListener(saveStudentAction);
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(Color.RED);
    }

    public RegistrationFrame(String name){
        super(name);

        this.setSize(960, 540);
        this.setResizable(false);
        this.setLocation(600, 300);
        Controller.showFrame(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        this.setLayout(null);

        setLabels();
        setFields();
        setButton();
        setImage();
        setFonts();
    }
}
