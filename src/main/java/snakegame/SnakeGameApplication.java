package snakegame;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import snakegame.view.GUI;

import java.io.IOException;

public class SnakeGameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        stage.setTitle("Snake");

        stage.setScene(new Scene(root));

        stage.show();
    }
    @FXML
    private void startButtonClicked(ActionEvent actionEvent) {

        Node source = (Node) (actionEvent.getSource());


        Stage stage = (Stage) (source.getScene().getWindow());


        stage.setScene(GUI.getGameScene());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
