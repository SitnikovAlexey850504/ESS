package Student;

import java.sql.*;
import java.util.ArrayList;

import static DataBase.WorkOnDB.getConnection;

public class StudentsService {
    private static ArrayList<Student> students = null;

    public static void addStudent(Student student, String login, String password){
        String surname = student.getSurname();
        String name = student.getName();
        String patronymic = student.getPatronymic();
        String faculty = student.getFaculty();
        String specialty = student.getSpecialty();
        String course = student.getCourse();
        String group = student.getGroup();
        String form = student.getForm();
        String studentCard = student.getStudentCard();

        Connection connection = getConnection();

        String insert = "INSERT INTO tritpo.students(surname, name, patronymic, faculty, specialty, course, studentGroup, form, studentCard, login, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, faculty);
            preparedStatement.setString(5, specialty);
            preparedStatement.setString(6, course);
            preparedStatement.setString(7, group);
            preparedStatement.setString(8, form);
            preparedStatement.setString(9, studentCard);
            preparedStatement.setString(10, login);
            preparedStatement.setString(11, password);

            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> getStudentsCards(String str){
        String get = "SELECT surname, name, patronymic, faculty, specialty, course, studentGroup, form, studentCard, login, password from tritpo.students";
        ArrayList<String> studentCards = new ArrayList<>();

        Connection connection = getConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get);
            while(resultSet.next()){
                if(str.equals(resultSet.getString(1)) || str.equals(resultSet.getString(2)) || str.equals(resultSet.getString(4)) ||
                        str.equals(resultSet.getString(5)) || str.equals(resultSet.getString(6)) || str.equals(resultSet.getString(7))){
                    studentCards.add(resultSet.getString(9));
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return studentCards;
    }

    public static ArrayList<Student> searchStudents(String str){
        ArrayList<String> studentCards = getStudentsCards(str);

        String get = "SELECT surname, name, patronymic, faculty, specialty, course, studentGroup, form, studentCard, mark1, mark2, mark3, mark4, mark5 from tritpo.students";
        ArrayList<Student> students = new ArrayList<>();

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get);
            while (resultSet.next()){
                for(int i = 0; i < studentCards.size(); i++){
                    if(studentCards.get(i).equals(resultSet.getString(9))) {
                        Student student = new Student();

                        student.setSurname(resultSet.getString(1));
                        student.setName(resultSet.getString(2));
                        student.setPatronymic(resultSet.getString(3));
                        student.setFaculty(resultSet.getString(4));
                        student.setSpecialty(resultSet.getString(5));
                        student.setCourse(resultSet.getString(6));
                        student.setGroup(resultSet.getString(7));
                        student.setForm(resultSet.getString(8));
                        student.setStudentCard(resultSet.getString(9));
                        student.setScore(resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getInt(14));

                        students.add(student);
                    }
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return students;
    }

    public static void changeStudentInformation(String newString, String card, String str) {
        String update = "update tritpo.students set " + str + " = ? where studentCard = '" + card + "'";

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, newString);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteStudent(String card){
        String delete = "DELETE FROM tritpo.students where studentCard = '" + card + "'";

        Connection connection = getConnection();

        try{
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(delete);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
