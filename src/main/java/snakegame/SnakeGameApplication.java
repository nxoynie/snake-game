package snakegame;
import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import snakegame.results.GameResultDao;
import snakegame.view.GUI;
import util.guice.PersistenceModule;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
@Slf4j
public class SnakeGameApplication extends Application {
    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("snake-game"));
                    bind(GameResultDao.class);
                }
            }
    ));
    @Inject
    private FXMLLoader fxmlLoader;
    @Override
    public void start(Stage stage) throws IOException {
        log.info("Starting application...");
        context.init();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        stage.setTitle("Snake Game");
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
