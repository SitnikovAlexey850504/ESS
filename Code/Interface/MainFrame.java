package Interface;

import Controller.Controller;
import DataBase.WorkOnDB;
import Student.Student;
import Student.StudentsService;
import Student.SortStudents;
import Student.SortByAlphabet;
import Student.SortByScore;
import TextException.CountLimit;
import TextException.StringException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

public class MainFrame extends JFrame {
    private JLabel errorLabel = new JLabel("");

    private JButton addButton = new JButton("Добавить");
    private JButton closeButton = new JButton("Завершить");
    //private JButton deleteButton = new JButton("Удалить");
    private JButton searchButton = new JButton("Найти");
    private JButton moveButton = new JButton("Перейти");

    private JButton sortButton = new JButton("Сортировать");

    private JCheckBox checkBox1 = new JCheckBox("По алфавиту");
    private JCheckBox checkBox2 = new JCheckBox("По баллу");

    private JTextField searchField = new JTextField();

    private JTextArea textArea = new JTextArea();

    private Font font = new Font("Arial", Font.ROMAN_BASELINE, 14);

    private String[] columnNames = {"Фамилия", "Имя", "Факультет", "Специальность", "Курс", "Группа", "Студенческий", "Балл"};

    JFrame registrationWindow = null;

    ArrayList<Student> students = new ArrayList<Student>();

    SortStudents sortStudents;

    private JFrame getFrame(){
        return this;
    }

    private void setLabels(){
        getContentPane().add(errorLabel);
        errorLabel.setBounds(20, 50, 600, 20);
    }

    private void setFields(){
        getContentPane().add(searchField);
        searchField.setBounds(20, 20, 770, 30);

        searchField.setDocument(new CountLimit(55));
    }

    private void setButtons(){
        getContentPane().add(addButton);
        addButton.setBounds(800, 170, 130, 30);
        addButton.addActionListener(addAction);
        addButton.setToolTipText("Добавить нового студента в базу данных");
        addButton.setBackground(Color.WHITE);
        addButton.setForeground(Color.RED);

        getContentPane().add(closeButton);
        closeButton.setBounds(800, 470, 130, 20);
        closeButton.addActionListener(closeAction);
        closeButton.setBackground(Color.WHITE);
        closeButton.setForeground(Color.RED);

        //getContentPane().add(deleteButton);
        //deleteButton.setBounds(20, 70, 100, 20);
        getContentPane().add(searchButton);
        searchButton.setBounds(800, 15, 100, 20);
        searchButton.addActionListener(searchAction);
        searchButton.setToolTipText("Найти студентов по определенному признаку");
        searchButton.setBackground(Color.WHITE);
        searchButton.setForeground(Color.RED);

        getContentPane().add(moveButton);
        moveButton.setBounds(800, 35, 100, 20);
        moveButton.addActionListener(moveAction);
        moveButton.setToolTipText("Открыть страницу студента по номеру его студенческого билета");
        moveButton.setBackground(Color.WHITE);
        moveButton.setForeground(Color.RED);

        getContentPane().add(sortButton);
        sortButton.setBounds(800, 70, 130, 30);
        sortButton.addActionListener(sortAction);
        sortButton.setToolTipText("Отсортировать студентов по выбранному параметру");
        sortButton.setBackground(Color.WHITE);
        sortButton.setForeground(Color.RED);

    }

    private void setCheckBox(){
        getContentPane().add(checkBox1);
        checkBox1.setBounds(820, 110, 150, 20);
        checkBox1.setBackground(new Color(255, 255, 255));

        getContentPane().add(checkBox2);
        checkBox2.setBounds(820, 130, 150, 20);
        checkBox2.setBackground(new Color(255, 255, 255));
    }

    private ActionListener addAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            registrationWindow = FramesService.createFrame(FramesService.FramesType.REGISTRATION, null);
            Controller.hideFrame(getFrame());

            registrationWindow.addWindowListener(window);
        }
    };

    private ActionListener closeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            WorkOnDB.closeConnection();

            Controller.disposeFrame(getFrame());
        }
    };

    private ActionListener searchAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            errorLabel.setText("");

            String str = searchField.getText();

            if(str.isEmpty()){
                errorLabel.setText("Вы не ввели информацию для поиска!");
                return;
            }

            if(checkEquals() == false){
                errorLabel.setText("Введенные данные не корректны!");
                return;
            }

            students = StudentsService.searchStudents(searchField.getText());
            outputTable();
        }
    };

    private ActionListener sortAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(students == null){
                errorLabel.setText("Нет студентов для сортировики!");
                return;
            }

            if(!(checkBox1.isSelected() || checkBox2.isSelected())){
                errorLabel.setText("Вы не выбрали параметр для сортировки!");
                return;
            }

            if(checkBox1.isSelected() && checkBox2.isSelected()){
                errorLabel.setText("Вы выбрали 2 параметра для сортировки!");
                return;
            }else {
                errorLabel.setText("");
            }

            if(checkBox1.isSelected()){
                sortStudents = new SortByAlphabet();
                sortStudents.sortStudents(students);
                outputTable();
            }

            if(checkBox2.isSelected()){
                sortStudents = new SortByScore();
                sortStudents.sortStudents(students);
                outputTable();
            }
        }
    };

    private ActionListener moveAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(WorkOnDB.checkStudentCard(searchField.getText())){
                JFrame studentInformationFrame = FramesService.createFrame(FramesService.FramesType.STUDENT_INFORMATION, searchField.getText());
                Controller.hideFrame(getFrame());
                studentInformationFrame.addWindowListener(window);
            }else{
                errorLabel.setText("Студента с таким номером студенческого не существует!");
            }
        }
    };

    private boolean checkEquals(){
        boolean correct = true;

        if(StringException.checkOnString(searchField.getText()) == false) { correct = false; }

        return correct;
    }

    private WindowListener window = new WindowListener() {
        public void windowOpened(WindowEvent e) { }
        public void windowIconified(WindowEvent e) { }
        public void windowDeiconified(WindowEvent e) { }
        public void windowActivated(WindowEvent e) { }
        public void windowDeactivated(WindowEvent e) { }

        public void windowClosed(WindowEvent e) {
            Controller.showFrame(getFrame());
        }
        public void windowClosing(WindowEvent e) {
            Controller.showFrame(getFrame());
        }
    };

    private void setFonts(){
        //errorLabel.setFont(font);

        searchField.setFont(font);

        addButton.setFont(font);
        closeButton.setFont(font);
        //deleteButton.setFont(font);
        searchButton.setFont(font);
        moveButton.setFont(font);
        sortButton.setFont(font);

        //checkBox1.setFont(font);
        //checkBox2.setFont(font);
    }

    private String[][] getData(ArrayList<Student> students){
        String[][] data = new String[students.size()][8];

        for (int i = 0; i < students.size(); i++){

            data[i][0] = students.get(i).getSurname();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getFaculty();
            data[i][3] = students.get(i).getSpecialty();
            data[i][4] = students.get(i).getCourse();
            data[i][5] = students.get(i).getGroup();
            data[i][6] = students.get(i).getStudentCard();
            data[i][7] = String.valueOf(students.get(i).getScore());
        }

        return data;
    }

    private void outputTable(){
        String[][] data = getData(students);
        JTable studentsTable = new JTable(data, columnNames);
        studentsTable.setFocusable(false);

        JScrollPane scroll = new JScrollPane(studentsTable);
        getContentPane().add(scroll);
        scroll.setBounds(20, 70, 770, 380);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBackground(new Color(255, 255, 255));
    }

    public MainFrame(String name){
        super(name);

        this.setSize(960, 540);
        this.setResizable(false);
        this.setLocation(600, 300);
        Controller.showFrame(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));

        this.setLayout(null);

        setLabels();
        setFields();
        setButtons();
        setCheckBox();
        setFonts();
        outputTable();
    }
}
