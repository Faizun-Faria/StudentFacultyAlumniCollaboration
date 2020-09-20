package sfac;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class PostDescriptionController {

    SimpleDateFormat formatter;
    Date date;
    DatabaseQuery dq; 
    LogInController lc;
    
    
    @FXML
    private TextField orgName;

    @FXML
    private TextField description;

    @FXML
    private TextField position;

    @FXML
    private Button postToDashboard;

    @FXML
    private Button backButton;

    @FXML
    void goToBack(MouseEvent event) throws IOException {
        Parent home;
        home = FXMLLoader.load(getClass().getResource("View/AlumniDashboard.fxml"));
        Scene homeScene = new Scene(home);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(homeScene);
        window.show();
    }

    private void showError(String err) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(err);
        alert.showAndWait();
    }

    public boolean checkOrganizationName(String orgName) {
        if (orgName.isEmpty()) {
            showError("Enter organization name");
            return true;
        }
        return false;
    }

    public boolean checkPosition(String pos) {
        if (pos.isEmpty()) {
            showError("Enter position");
            return true;
        }
        return false;
    }

    public boolean checkDescription(String des) {
        if (des.isEmpty()) {
            showError("Enter description");
            return true;
        }
        return false;
    }

    @FXML
    void goToDashboardByPosting(MouseEvent event) throws IOException, SQLException {
        
        String org_name = orgName.getText();
        String pos = position.getText();
        String des = description.getText();
        if (checkOrganizationName(org_name)) {
            return;
        }
        if (checkPosition(pos)) {
            return;
        }
        if (checkDescription(des)) {
            return;
        }
        dq = new DatabaseQuery();
        date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = formatter.format(date); // after 1 week date
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatter.parse(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 7);
        String delete_date = formatter.format(c.getTime());
        formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(date);
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_time = formatter.format(date);
        int r = dq.postJob(org_name,pos,des,time,delete_date,date_time);
        if (r != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Posted");
            alert.setContentText("Your post has been submitted");
            alert.showAndWait();
            Parent home = FXMLLoader.load(getClass().getResource("View/AlumniDashboard.fxml"));
            Scene homeScene = new Scene(home);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Job offerings");
            window.setScene(homeScene);
            window.show();
        } 
    }

}
