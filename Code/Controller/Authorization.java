package Controller;

import DataBase.WorkOnDB;

public class Authorization {
    public static int authentificate(String login, String password, int check){
        String log = "s";
        String pas = "s";

        if(check == 1){
            if(WorkOnDB.checkStudent(login, password)){
                return 1;
            } else {
                return 0;
            }
        }else{
            if(login.equals(log) && password.equals(pas)){
                return 2;
            }else{
                return 0;
            }
        }
    }
}
