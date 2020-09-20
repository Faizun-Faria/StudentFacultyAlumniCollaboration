package sfac;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faizun
 */
public class AlumniDashboardController implements Initializable {

    @FXML
    private Button postButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Pane display;
    
    DatabaseQuery dq;
    UserSession us;
    int res;
    ArrayList <Button> blist;

    final ScrollPane sp = new ScrollPane();
    final VBox vb = new VBox();
    Connection connection;

    static String org = "";
    static String posi = "";
    static String descr = "";
    static String ti = "";
    static String da = "";
    static String tiSt = "";
    static String alu = "";
    static String nameOfAuthor = "";

    @FXML
    void loggingOut(MouseEvent event) throws IOException {
        Parent home;
        home = FXMLLoader.load(getClass().getResource("View/LogIn.fxml"));
        Scene homeScene = new Scene(home);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Dashboard");
        window.setScene(homeScene);
        window.show();
        UserSession.getInstance().logoutSession();
    }

    @FXML
    void newpost(MouseEvent event) {
        Parent home;
        try {
            home = FXMLLoader.load(getClass().getResource("View/PostDescription.fxml"));
            Scene homeScene = new Scene(home);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Job offering");
            window.setScene(homeScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AlumniDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox box = new VBox();
        box.getChildren().add(sp);
        dq = new DatabaseQuery();
        try {
            dq.getPosts(UserSession.getInstance().getUserType());
        } catch (SQLException ex) {
            Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sp.setContent(vb);
        sp.setFitToHeight(true);
        sp.setPrefSize(447, 364);
        sp.setVmax(200);
        sp.setHmax(3);
        sp.setHvalue(1);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        display.getChildren().add(sp);
    }

    public void showPost(String org_name, String pos, String des, String date_time, String nameOfAuthor, int cnt) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(35, 100, 10, 10));
        grid.setMinSize(200, 150);
        grid.setHgap(5);
        grid.setVgap(5);

        Text username = new Text(nameOfAuthor);
        username.setStyle("-fx-font-weight: bold");
        grid.add(username, 0, 0);

        Text time = new Text(date_time);
        time.setStyle("-fx-font-style: italic");
        grid.add(time, 0, 1);

        Text oName = new Text(org_name);
        oName.setStyle("-fx-font-weight: regular");
        grid.add(oName, 0, 2);

        Text posit = new Text(pos);
        posit.setStyle("-fx-font-weight: regular");
        grid.add(posit, 0, 3);

        Text post = new Text(des);
        post.setStyle("-fx-font-weight: regular");
        grid.add(post, 0, 4);

        if (cnt % 2 == 0) {
            grid.setStyle("-fx-background-color: #d1c4e9;");
        } else {
            grid.setStyle("-fx-background-color: #90caf9;");
        }
        vb.getChildren().add(grid);
    }
}
