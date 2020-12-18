package DataBase;

import java.sql.*;

public class WorkOnDB {
    private static Connection connection;
    private Driver driver;

    public WorkOnDB() {
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void establishConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/TRITPO?useSSL=false&serverTimezone=UTC",
                    login = "root",
                    password = "410derrick1988";

            connection = DriverManager.getConnection(URL, login, password);
            if (!connection.isClosed()) {
                return;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void closeConnection(){
        try{
            if(!connection.isClosed()){
                connection.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static boolean checkStudent(String login, String password){
        String get = "SELECT login, password from tritpo.students";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get);
            while(resultSet.next()){
                if(login.equals(resultSet.getString(1))&&password.equals(resultSet.getString(2)))
                    return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static boolean checkStudentCard(String card){
        String get = "select studentCard from tritpo.students";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get);
            while(resultSet.next()){
                if(card.equals(resultSet.getString(1)))
                    return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}