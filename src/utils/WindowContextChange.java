package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Context;

import java.io.IOException;

public class WindowContextChange {
    public void setUi(AnchorPane context,String location) throws IOException {
        Stage stage= (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".xml"))));
    }
}
