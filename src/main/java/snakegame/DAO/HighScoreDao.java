package snakegame.DAO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class HighScoreDao {
    public void addScore(Score score){
        HighScore highScore = getHighScores();
        highScore.addScore(score);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(HighScore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            URL url = getClass().getResource("highscores.xml");
            File file = new File(url.toURI());
            OutputStream outputStream = new FileOutputStream(file);
            marshaller.marshal(highScore,file);
            marshaller.marshal(highScore,System.out);
            System.out.println(outputStream);
        } catch (JAXBException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
    public HighScore getHighScores(){
        HighScore highScore = new HighScore();
        try {
            JAXBContext jaxbContext =JAXBContext.newInstance(HighScore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream inputStream = getClass().getResourceAsStream("highscores.xml");
            highScore = (HighScore) unmarshaller.unmarshal(inputStream);
            inputStream.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}
