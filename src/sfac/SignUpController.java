package sfac;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faizun
 */
public class SignUpController{

    /**
     * Initializes the controller class.
     */
    Connection connection;
    String First_Name, Last_Name, Email_ID, Password, Confirm_Password, userType="";
    DatabaseQuery dq = new DatabaseQuery();
    
    @FXML
    private Label signup;
    private Button backButton;
    private Button joinButton;
    private ToggleGroup ChooseType;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cPassword;

    @FXML
    private RadioButton facultyRButton;

    @FXML
    private RadioButton alumniRButton;

    @FXML
    private RadioButton studentRButton;
    
    @FXML
    void goToLoginByJoining(MouseEvent event) throws Exception  {
        if(checkInfo()){
            return;
        }  
        int r = dq.insertUserInfo(Email_ID, First_Name, Last_Name, Password, userType);
        if (r != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("SignUp Successful");
            alert.setContentText("Please log in");
            alert.showAndWait();
            Parent  SignIn = FXMLLoader.load(getClass().getResource("View/LogIn.fxml"));
            Scene signInScene = new Scene(SignIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(signInScene);
        window.show();
        }       
    }
    
    @FXML
    void typeSelected(ActionEvent event) {
        if(facultyRButton.isSelected()){
            userType = "Faculty";
        }
        if(alumniRButton.isSelected()){
            userType = "Alumni";
        }
        if(studentRButton.isSelected()){
            userType = "Student";
        }
    }
    
    @FXML
    void goToLoginPage(MouseEvent event) throws Exception  {
        Parent SignIn = FXMLLoader.load(getClass().getResource("View/LogIn.fxml"));
        Scene signInScene = new Scene(SignIn);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(signInScene);
        window.show();
    }
    
    public void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }
    
    private boolean checkInfo() throws SQLException{
        First_Name = firstName.getText();
        Last_Name = lastName.getText();
        Email_ID = email.getText();
        Password = password.getText();
        Confirm_Password = cPassword.getText();
        
        if(checkType()) return true;  
        
        if(checkFirstName(First_Name)) return true;  
        if(checkLastName(Last_Name)) return true;  
        if (dq.checkEmailForSignup(Email_ID, userType))  return true;  
        if (checkPassword(Password))  return true;  
        if (checkConfirmPassword(Confirm_Password))  return true;  
        return false;
    }
    
    /*
    It checks whether any of the radio button is selected or not.
    */
    private boolean checkType() {
        if(userType.equals("")){
            showError("Choose type");
            return true;
        }
        return false;
    }
    
    /*
    It checks if there is any valid value given as input.
    */
    private boolean checkFirstName(String First_Name) {
        if(First_Name.isEmpty()){
            showError("Enter your first name");
            return true;
        }
        return false;
    }
    
    /*
    It checks if there is any valid value given as input.
    */
    private boolean checkLastName(String Last_Name) {
        if(Last_Name.isEmpty()){
            showError("Enter your last name");
            return true;
        }
        return false;
        
    }
        
    /*
    This method will check if the user input contains value or it is empty.
    It also checks whether the password fulfills the conditions.
    */
    private boolean checkPassword(String Password) {
        CheckRegex checkReg = new CheckRegex();
        if (Password.isEmpty()) {
            showError("Enter your password");
            return true;
        }if(checkReg.checkPassword(Password)){
            showError("Your password must contain 1 uppercase letter, 1 lowercase letter, 1 numerical number .");
            return true;
        }
        return false;
    }
    
    /*
    This method will check if the user input contains value or it is empty.
    Then it checks whether it matches with the password.
    */
    private boolean checkConfirmPassword(String Confirm_Password){
        
        if (Confirm_Password.isEmpty()) {
            showError("Enter your confirm password");
            return true;
        }
        if(!Password.equals(Confirm_Password)){
            showError("Your password does not match with confirm password");
            return true;
        }
        return false;
    }    
    
}
