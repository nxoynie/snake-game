package game;

import com.sun.glass.ui.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Snake Game");

        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));

        stage.setScene(new Scene(root));

        stage.show();
    }
    @FXML
    private void startButtonClicked(ActionEvent actionEvent){
        Node source = (Node) (actionEvent.getSource());

        Stage stage = (Stage) (source.getScene().getWindow());

        stage.setScene(GUI.getGameScene());
    }

    public static void main(String[] args) {

        Application.launch(args);
    }
}
