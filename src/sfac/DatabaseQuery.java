/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfac;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import javafx.scene.control.Alert;
/**
 *
 * @author Faizun
 */
public class DatabaseQuery {

    
    
    DatabaseConnection dc;
    Connection connection;
    PreparedStatement ps;
    Statement stm;
    ResultSet res;
    String query;
    Encoding ed;
    CheckRegex checkReg;
    UserSession us;
    String userType = "", userData = "", email_id="", password="";
   
    StudentDashboardController sdc;
    AlumniDashboardController adc;
    FacultyDashboardController fdc;
    
    
    private void conn(){
        dc = new DatabaseConnection();
        connection = dc.getConnection();
        us = UserSession.getInstance();
    }
    
    public void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }
    
    /*
    LoginController class calls this method to check whether the user giving some input or blank input.
    This method also checks whether the email given by the user is in the database or not.    
    */
    public boolean checkEmail(String email_id, String userType) throws SQLException { 
        conn();
        this.email_id = email_id;
        userData = userType.toUpperCase() + "_DATA";
        query = "SELECT * FROM " + userData + " WHERE EMAIL_ID = '" + email_id + "'";
        if (email_id.isEmpty()) {
            showError("Enter your email");
            return true;
        } 
        ps = connection.prepareStatement(query);
        res = ps.executeQuery();
        if (!res.next()) {
            showError("Invalid email");
            return true;
        }
        return false;
    }
    
    /*
    LoginController class calls this method to check whether the password given by the user is empty or it contains value.
    If the password is non empty, this method encodes the given password input and matches it with the encoded password stored in the database.
    */
    public boolean checkPassword(String password) throws SQLException, NoSuchAlgorithmException, Exception {
        conn();
        if (password.isEmpty()) {
            showError("Enter your password");
            return true;
        }
        query = "SELECT * FROM " + userData + " WHERE EMAIL_ID = '" + email_id + "'";
        
        ps = connection.prepareStatement(query);
        res = ps.executeQuery();
        if (res.next()){
            String passwordDB = res.getString(4);
            password = new Encoding().getHash(password);
            if (!password.equals(passwordDB)) {
                showError("Wrong password");
                return true;
            }
        }
        return false;
    }
    
    /*
    This method is called from SignUpController class.
    The checkings are:
    1. Whether the user input is empty or it contains value.
    2. As the email format is different according to the user type, it checks
    whether the given input format (alumni, student, teacher) matches with the user 
    type selected earlier. 
    3. It also checks whether the email already exist in the database. If it does, 
    then the user needs to use another email for creating an account as one email 
    can not be used more than once to register.  
    */
    public boolean checkEmailForSignup(String email, String userType) throws SQLException {
        conn();
        if (email.isEmpty()) {
            showError("Enter your email");
            return true;
        }
        
        userData = userType.toUpperCase() + "_DATA";
        query = "SELECT * FROM " + userData + " WHERE EMAIL_ID = '" + email + "'";
        
        ps = connection.prepareStatement(query);
        res = ps.executeQuery();
        if (res.next()) {
            showError("This email already exists");
            return true;
        }
        if(checkReg.checkEmail(email,userType)){
            showError("Incorrect email format");
            return true;
        }
        return false;
    }
    
    /*
    This method is called from SignUpController class.
    This method inserts all the information of the user into the database.
    */
    public int insertUserInfo(String Email_ID, String First_Name, String Last_Name, String Password, String userType) throws Exception{      
        conn();
        ed = new Encoding();
        Password = ed.encrypt(Password);
        userData = userType.toUpperCase() + "_DATA";
        query = "INSERT INTO "+ userData +" VALUES ("
                + "'" + Email_ID + "'," 
                + "'" + First_Name + "'," 
                + "'" + Last_Name + "',"
                + "'" + Password + "'" + ")";
        
        String addingInfoToPrev="INSERT INTO PREVIOUS_PASSWORDS VALUES ("
                + "'" + Email_ID + "'," 
                + "'" + Password + "'" + ")";
        
        stm = connection.createStatement();
        int r = stm.executeUpdate(query);
        stm.executeUpdate(addingInfoToPrev);
        return r;
    }
    /*
    This method is called from PostDescriptionController class.
    This method inserts all the information about the job post including the author's name and time into the database.
    Only alumni can post jobs.
    */
    public int postJob(String org_name,String pos,String des,String time,String delete_date,String date_time) throws SQLException{
        conn();
        query = "SELECT FIRST_NAME FROM ALUMNI_DATA WHERE EMAIL_ID ='" + us.getEmail() + "'";
        ps = connection.prepareStatement(query);
        res = ps.executeQuery();
        String nameOfAuthor ="";
        if(res.next()){
            nameOfAuthor = res.getString(1);
        }      
        
        query = "INSERT INTO POST VALUES ("
                + "'" + org_name + "',"
                + "'" + pos + "',"
                + "'" + des + "',"
                + "'" + time + "',"
                + "'" + delete_date + "',"
                + "'" + date_time + "',"
                + "'" + nameOfAuthor + "'" + ")";
        stm = connection.createStatement();
        int r = stm.executeUpdate(query);
        return r;
    }
    /*
    This method is called from forgot_passController class.
    This method checks whether the user is setting any new value as a password or any previous one.  
    */
    
    public boolean prevPassCheck(String email, String pass) throws SQLException {
        String prevPasswordCheck = "SELECT * FROM PREVIOUS_PASSWORDS WHERE EMAIL_ID = '" + email + "'AND PASSWORD = '" + pass + "'";
        conn();
        ps = connection.prepareStatement(prevPasswordCheck);
        res = ps.executeQuery();
        if (res.next()) {
            showError("You can not choose previous one. Choose another password");
            return true;
        }
        return false;
    }
    
    /*
    This method is called from forgot_passController class.
    This method updates new password in the database table.  
    */
    public void changePass(String Pass) throws Exception{
        String encPass = ed.encrypt(Pass);
        conn();
        String Email = us.getEmail();
        String userType = us.getUserType();
        query = "INSERT INTO PREVIOUS_PASSWORDS VALUES ("
                + "'" + Email + "',"
                + "'" + encPass + "'" + ")";
        stm = connection.createStatement();
        stm.executeUpdate(query);
        
        query = "UPDATE "+userType+"_DATA SET PASSWORD = '" + encPass + "'WHERE EMAIL_ID = '" + Email + "'";
        stm = connection.createStatement();
        stm.executeUpdate(query);
    }
    
    
    Stack<String> stack = new Stack<String>();
    
    
    public void getPosts(String userType) throws SQLException{
        conn();
        query = "SELECT * FROM POST";
        stm = connection.createStatement();
        res = stm.executeQuery(query);
        while (res.next()) {
            stack.push(res.getString(6));
        }
        while (!stack.empty()) {
            query = "SELECT * FROM POST WHERE post_date_time = '" + stack.peek() + "'";
            int cnt = stack.size();
            stack.pop();
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String org_name = rs.getString(1);
                String pos = rs.getString(2);
                String des = rs.getString(3);
                String time = rs.getString(4);
                String delete_date = rs.getString(5);
                String date_time = rs.getString(6);
                String nameOfAuthor = rs.getString(7);
                if (userType.equals("Student")) new StudentDashboardController().showPost(org_name, pos, des, date_time, nameOfAuthor, cnt);
                else if (userType.equals("Faculty")) new FacultyDashboardController().showPost(org_name, pos, des, date_time, nameOfAuthor, cnt);
                else if (userType.equals("Alumni")) new AlumniDashboardController().showPost(org_name, pos, des, date_time, nameOfAuthor, cnt);
            }
        }
        res.close();
        stm.close();
        connection.close();
    }
}
