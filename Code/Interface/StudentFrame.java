package Interface;

import Controller.Controller;
import DataBase.WorkOnDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentFrame extends JFrame {
    private Image image;
    private Connection connection = WorkOnDB.getConnection();

    private JLabel surnameLabel = new JLabel("Фамилия");
    private JLabel nameLabel = new JLabel("Имя");
    private JLabel patronymicLabel = new JLabel("Отчество");
    private JLabel facultyLabel = new JLabel("Факультет");
    private JLabel specialtyLabel = new JLabel("Специальность");
    private JLabel courseLabel = new JLabel("Курс");
    private JLabel groupLabel = new JLabel("Группа");
    private JLabel studentCardLabel = new JLabel("Студенческий");
    private JLabel formLabel = new JLabel("Форма обучения");

    private JTextField surnameField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField patronymicField = new JTextField();
    private JTextField facultyField = new JTextField();
    private JTextField specialtyField = new JTextField();
    private JTextField courseField = new JTextField();
    private JTextField groupField = new JTextField();
    private JTextField formField = new JTextField();
    private JTextField studentCardField = new JTextField();

    private JTextField subjectName = new JTextField();
    private JTextField subjectField1 = new JTextField();
    private JTextField subjectField2 = new JTextField();
    private JTextField subjectField3 = new JTextField();
    private JTextField subjectField4 = new JTextField();
    private JTextField subjectField5 = new JTextField();
    private JTextField markName = new JTextField();
    private JTextField markField1 = new JTextField();
    private JTextField markField2 = new JTextField();
    private JTextField markField3 = new JTextField();
    private JTextField markField4 = new JTextField();
    private JTextField markField5 = new JTextField();
    private JTextField teacherName = new JTextField();
    private JTextField teacherField1 = new JTextField();
    private JTextField teacherField2 = new JTextField();
    private JTextField teacherField3 = new JTextField();
    private JTextField teacherField4 = new JTextField();
    private JTextField teacherField5 = new JTextField();

    private Font font = new Font("Verdana", Font.ROMAN_BASELINE, 14);

    private JButton passwordButton = new JButton("Изменить пароль");

    String studentCard;


    private void setImage() {
        String path = "D:\\5sem\\TRITPO\\image.png";
        File file = new File(path);

        try {
            image = ImageIO.read(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        getContentPane().add(imageLabel);
        imageLabel.setBounds(20, 20, 200, 200);
    }

    private void setInformation(String login){
        String set = "SELECT * from TRITPO.students";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(set);

            while(resultSet.next()){
                if(login.equals(resultSet.getString(10))){
                    surnameField.setText(resultSet.getString(1));
                    nameField.setText(resultSet.getString(2));
                    patronymicField.setText(resultSet.getString(3));
                    facultyField.setText(resultSet.getString(4));
                    specialtyField.setText(resultSet.getString(5));
                    courseField.setText(resultSet.getString(6));
                    groupField.setText(resultSet.getString(7));
                    formField.setText(resultSet.getString(8));
                    studentCardField.setText(resultSet.getString(9));

                    subjectName.setText("Предмет");
                    subjectField1.setText(resultSet.getString(12));
                    subjectField2.setText(resultSet.getString(13));
                    subjectField3.setText(resultSet.getString(14));
                    subjectField4.setText(resultSet.getString(15));
                    subjectField5.setText(resultSet.getString(16));

                    markName.setText("Отметка");
                    markField1.setText(resultSet.getString(17));
                    markField2.setText(resultSet.getString(18));
                    markField3.setText(resultSet.getString(19));
                    markField4.setText(resultSet.getString(20));
                    markField5.setText(resultSet.getString(21));

                    teacherName.setText("Преподаватель");
                    teacherField1.setText(resultSet.getString(22));
                    teacherField2.setText(resultSet.getString(23));
                    teacherField3.setText(resultSet.getString(24));
                    teacherField4.setText(resultSet.getString(25));
                    teacherField5.setText(resultSet.getString(26));

                    studentCard = resultSet.getString(9);

                    break;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private ActionListener passwordAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame passwordFrame = FramesService.createFrame(FramesService.FramesType.PASSWORD, studentCard);
        }
    };

    private void setLabels(){
        getContentPane().add(surnameLabel);
        surnameLabel.setBounds(20, 230, 120, 30);
        getContentPane().add(nameLabel);
        nameLabel.setBounds(20, 270, 120, 30);
        getContentPane().add(patronymicLabel);
        patronymicLabel.setBounds(20, 310, 120, 30);
        getContentPane().add(facultyLabel);
        facultyLabel.setBounds(20, 350, 120, 30);
        getContentPane().add(specialtyLabel);
        specialtyLabel.setBounds(20, 390, 120, 30);
        getContentPane().add(courseLabel);
        courseLabel.setBounds(20, 430, 120, 30);
        getContentPane().add(groupLabel);
        groupLabel.setBounds(20, 470, 120, 30);
        getContentPane().add(formLabel);
        formLabel.setBounds(20, 510, 120, 30);
        getContentPane().add(studentCardLabel);
        studentCardLabel.setBounds(20, 550, 120, 30);
    }

    private void setTextAreas(){
        getContentPane().add(surnameField);
        surnameField.setBounds(150, 230, 400, 30);
        getContentPane().add(nameField);
        nameField.setBounds(150, 270, 400, 30);
        getContentPane().add(patronymicField);
        patronymicField.setBounds(150, 310, 400, 30);
        getContentPane().add(facultyField);
        facultyField.setBounds(150, 350, 400, 30);
        getContentPane().add(specialtyField);
        specialtyField.setBounds(150, 390, 400, 30);
        getContentPane().add(courseField);
        courseField.setBounds(150, 430, 400, 30);
        getContentPane().add(groupField);
        groupField.setBounds(150, 470, 400, 30);
        getContentPane().add(formField);
        formField.setBounds(150, 510, 400, 30);
        getContentPane().add(studentCardField);
        studentCardField.setBounds(150, 550, 400, 30);
    }

    private void setTextFields(){
        getContentPane().add(subjectName);
        subjectName.setBounds(250, 20, 250, 30);
        getContentPane().add(subjectField1);
        subjectField1.setBounds(250, 50, 250, 30);
        getContentPane().add(subjectField2);
        subjectField2.setBounds(250, 80, 250, 30);
        getContentPane().add(subjectField3);
        subjectField3.setBounds(250, 110, 250, 30);
        getContentPane().add(subjectField4);
        subjectField4.setBounds(250, 140, 250, 30);
        getContentPane().add(subjectField5);
        subjectField5.setBounds(250, 170, 250, 30);
        getContentPane().add(markName);
        markName.setBounds(500, 20, 200, 30);
        getContentPane().add(markField1);
        markField1.setBounds(500, 50, 200, 30);
        getContentPane().add(markField2);
        markField2.setBounds(500, 80, 200, 30);
        getContentPane().add(markField3);
        markField3.setBounds(500, 110, 200, 30);
        getContentPane().add(markField4);
        markField4.setBounds(500, 140, 200, 30);
        getContentPane().add(markField5);
        markField5.setBounds(500, 170, 200, 30);
        getContentPane().add(teacherName);
        teacherName.setBounds(700, 20, 400, 30);
        getContentPane().add(teacherField1);
        teacherField1.setBounds(700, 50, 400, 30);
        getContentPane().add(teacherField2);
        teacherField2.setBounds(700, 80, 400, 30);
        getContentPane().add(teacherField3);
        teacherField3.setBounds(700, 110, 400, 30);
        getContentPane().add(teacherField4);
        teacherField4.setBounds(700, 140, 400, 30);
        getContentPane().add(teacherField5);
        teacherField5.setBounds(700, 170, 400, 30);
    }

    private void setButtons(){
        getContentPane().add(passwordButton);
        passwordButton.setBounds(1105, 10, 150, 20);
        passwordButton.addActionListener(passwordAction);
        passwordButton.setBackground(Color.WHITE);
        passwordButton.setForeground(Color.RED);
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

        surnameField.setFont(font);
        nameField.setFont(font);
        patronymicField.setFont(font);
        facultyField.setFont(font);
        specialtyField.setFont(font);
        courseField.setFont(font);
        groupField.setFont(font);
        formField.setFont(font);
        studentCardField.setFont(font);

        subjectName.setFont(font);
        subjectField1.setFont(font);
        subjectField2.setFont(font);
        subjectField3.setFont(font);
        subjectField4.setFont(font);
        subjectField5.setFont(font);
        markName.setFont(font);
        markField1.setFont(font);
        markField2.setFont(font);
        markField3.setFont(font);
        markField4.setFont(font);
        markField5.setFont(font);
        teacherName.setFont(font);
        teacherField1.setFont(font);
        teacherField2.setFont(font);
        teacherField3.setFont(font);
        teacherField4.setFont(font);
        teacherField5.setFont(font);
    }

    private void setEditable(){
        surnameField.setEditable(false);
        nameField.setEditable(false);
        patronymicField.setEditable(false);
        facultyField.setEditable(false);
        specialtyField.setEditable(false);
        courseField.setEditable(false);
        groupField.setEditable(false);
        formField.setEditable(false);
        studentCardField.setEditable(false);

        subjectName.setEditable(false);
        subjectField1.setEditable(false);
        subjectField2.setEditable(false);
        subjectField3.setEditable(false);
        subjectField4.setEditable(false);
        subjectField5.setEditable(false);
        markName.setEditable(false);
        markField1.setEditable(false);
        markField2.setEditable(false);
        markField3.setEditable(false);
        markField4.setEditable(false);
        markField5.setEditable(false);
        teacherName.setEditable(false);
        teacherField1.setEditable(false);
        teacherField2.setEditable(false);
        teacherField3.setEditable(false);
        teacherField4.setEditable(false);
        teacherField5.setEditable(false);
    }

    public StudentFrame(String name, String login){
        super(name);

        Controller.showFrame(this);
        this.setSize(1280,720);
        this.setLocation(320, 180);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        this.setLayout(null);

        setLabels();
        setTextAreas();
        setTextFields();
        setButtons();
        setImage();
        setInformation(login);
        setFonts();
        setEditable();
    }
}
