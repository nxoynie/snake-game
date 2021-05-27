package snakegame.Dao;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import lombok.extern.slf4j.Slf4j;
import snakegame.SnakeGameApplication;
import snakegame.util.JaxbHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * The HighScoreDAO implements the methods to read and white XML file.
 */
@Slf4j
public class HighScoreDao {

    /**
     * Add to score to XML file.
     * @param score score
     * @param window window
     */
    public void addScore(Score score, Window window){
        HighScore highScore = getHighScores(SnakeGameApplication.window);
        highScore.addScore(score);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save File", "*.xml"));
        File selected = fileChooser.showSaveDialog(window);

        if (selected != null) {
            try {
                JaxbHelper.toXML(highScore, new FileOutputStream(selected));
            } catch (JAXBException | FileNotFoundException e) {
                log.info("Something went wrong.");
            }
        } else {
            log.info("Something went wrong, user didn't choose file.");
        }
    }

    /**
     * Read the elements of the XML file.
     * @param window window
     * @return Highscore list.
     */
    public HighScore getHighScores(Window window){
        HighScore highScore = new HighScore();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Save File", "*.xml"));
        File selected = fileChooser.showOpenDialog(window);

        if (selected != null) {
            try {
                highScore = JaxbHelper.fromXML(HighScore.class, new FileInputStream(selected));

            } catch (JAXBException | FileNotFoundException e) {
                log.info("During operation an error occurred.");
            }
        } else {
            log.info("User didn't choose file.");
        }

        return highScore;
    }
}