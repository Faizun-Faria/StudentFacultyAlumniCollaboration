package sfac;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faizun
 */
public class Forgot_PassController{
    Statement statement;
    Connection connection;
    Encoding ed;
    DatabaseQuery dq;
    
    @FXML
    private TextField email_id;

    @FXML
    private TextField passw;

    @FXML
    private TextField cPassw;

    @FXML
    private Button changePass;

    CheckRegex checkReg = new CheckRegex();

    @FXML
    void changePassword(MouseEvent event) throws SQLException, Exception {
        String Password = passw.getText();
        String Email = email_id.getText();
        String Confirm_Password = cPassw.getText();

        if (checkPassword(Email, Password)) {
            return;
        }
        if (checkConfirmPassword(Password, Confirm_Password)) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Password changed");
        alert.showAndWait();

        Parent login = FXMLLoader.load(getClass().getResource("View/LogIn.fxml"));
        Scene signinScene = new Scene(login);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(signinScene);
        window.show();

    }

    

    private boolean checkPassword(String email, String pass) throws SQLException, Exception {
        if (pass.isEmpty()) {
            showError("Enter your password");
            return true;
        }
        if (checkReg.checkPassword(pass)) {
            showError("Your password must contain 1 uppercase letter, 1 lowercase letter, 1 numerical number .");
            return true;
        }
        ed = new Encoding();
        pass = ed.encrypt(pass);
        dq = new DatabaseQuery();
        if (dq.prevPassCheck(email, pass)) {
            return true;
        }
        return false;
    }

    private boolean checkConfirmPassword(String pass, String cPass) {
        if (cPass.isEmpty()) {
            showError("Enter your confirm password");
            return true;
        }
        if (!pass.equals(cPass)) {
            showError("Your password does not match with confirm password");
            return true;
        }
        return false;
    }

    private void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }
}
