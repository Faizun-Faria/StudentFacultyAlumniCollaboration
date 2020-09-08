/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfac;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faizun
 */
public class LogInController {

    String Email_ID = "", Password = "";
    UserSession us;
    DatabaseQuery dq;
    String userType;
    
    
    @FXML
    private Label login;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPassword;
    @FXML
    private Button join;

    int type=0;
    
    @FXML
    void addEmail(ActionEvent event) {
    }

    @FXML
    void addPass(ActionEvent event) {
    }
    
    public String getEmail() {
        return Email_ID;
    }
    
    void checkUserType(String Email_ID) {
        if(Email_ID.endsWith("@g.bracu.ac.bd")){
            userType = "Student";
        }else if(Email_ID.endsWith("@bracu.ac.bd")){
            userType = "Faculty";
        }else{
            userType = "Alumni";          
        }
    }

    
    
   
    //if false then it will work
    @FXML
    void loginToAccount(MouseEvent event) throws SQLException, IOException, NoSuchAlgorithmException, Exception {
        dq = new DatabaseQuery();
        Email_ID = email.getText();
        Password = password.getText();
        checkUserType(Email_ID);
        
        if (dq.checkEmail(Email_ID,userType)) {
            return;
        }
        if (dq.checkPassword(Password)) {
            return;
        }
        UserSession.getInstance(Email_ID, userType);
        Parent home;
        String dashboard = "View/" + userType + "Dashboard.fxml";
        home = FXMLLoader.load(getClass().getResource(dashboard)); 
        
        Scene homeScene = new Scene(home);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(homeScene);
        window.show();
    }
    @FXML
    void goToSignUp(MouseEvent event) throws SQLException, IOException {
        Parent signUp = FXMLLoader.load(getClass().getResource("View/SignUp.fxml"));
        Scene signUpScene = new Scene(signUp);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Sign Up");
        window.setScene(signUpScene);
        window.show();
    }
    
    @FXML
    void changePass(MouseEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("View/Forgot_Pass.fxml"));
        Scene homeScene = new Scene(home);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Password Recovery");
        window.setScene(homeScene);
        window.show();
    }
    public void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }
    
    
    
    
    private static int sessionID = 0;
    
    public static int getSessionID() {
        return sessionID;
    }
    
    public static void setSessionID(int sessionid) {
        sessionID = sessionid;
    }
    
    private int generateSessionID() {
        sessionID++;
        return sessionID;
    }



} 


