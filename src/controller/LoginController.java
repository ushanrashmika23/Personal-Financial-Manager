package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public AnchorPane context;
    private String password="admin";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username="admin";

    private void setUi(String location) throws IOException {
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));

    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
//        if(txtPassword.getText().equals(password) && txtUsername.getText().equals(username)){
//            System.out.println("logged");
//            setUi("dashboardForm");
//        }else{
//            System.out.println("incorrect");
//            new Alert(Alert.AlertType.WARNING,"Password doesn't match.").show();
//        }
        setUi("dashboardForm");
    }
}
