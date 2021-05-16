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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import snakegame.Dao.HighScore;
import snakegame.Dao.HighScoreDao;
import snakegame.Dao.Score;
import snakegame.view.GUI;


import javax.inject.Inject;
import java.io.IOException;

/**
 * This class is building the welcome Menu and then calls for GUI class which builds the graphical interface.
 */

@Slf4j
public class SnakeGameApplication extends Application {
    @Inject
    private FXMLLoader fxmlLoader;

    /**
     * Adds a title to the game, creates root from FXML file and creates a Scenes using that as the Scene for the Stage, then shows it to the User.
     * @param stage Stage set up.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        log.info("Starting application...");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        stage.setTitle("Snake Game");

        stage.setScene(new Scene(root));

        stage.show();
    }

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label highscoreLabel;

    @FXML
    private void showHighscore(){
        HighScoreDao highScoreDao = new HighScoreDao();
        HighScore hs = new HighScore();
        hs = highScoreDao.getHighScores();
        String highscoretext = "";
        for(Score sc : hs.getHighscore()){
            String text = sc.getName() + " : " + sc.getScore() + "\n";
            highscoretext += text;
        }
        highscoreLabel.setText(highscoretext);
        highscoreLabel.setVisible(true);
    }

    /**
     * After the User gives a player name and presses the Start Button, calls the GUI class and the game begins.
     * @param actionEvent Action after pressing the Start Button.
     * @throws IOException
     */
    @FXML
    private void startButtonClicked(ActionEvent actionEvent) throws IOException {
        if (playerNameTextField.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
        } else {
            Node source = (Node) (actionEvent.getSource());


            Stage stage = (Stage) (source.getScene().getWindow());


            stage.setScene(GUI.getGameScene(playerNameTextField.getText()));
            log.info("The players name is set to {}, loading game scene", playerNameTextField.getText());
        }
    }

        public static void main (String[]args){
        log.info("The game has started.");
            launch(args);
        }

    }

