package DAO;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;

@XmlRootElement(name = "highscores")
public class HighScore {
    private ArrayList<Score> highscore;

    public HighScore() {
        this.highscore = new ArrayList<Score>();
    }

    public ArrayList<Score> getHighScore() {
        Collections.sort(highscore,(highscore1, highscore2) -> {
            return Integer.parseInt(highscore2.getScore()) - Integer.parseInt(highscore1.getScore());
        });
        if (highscore.size() > 10) {
            for (int i = 0; i < highscore.size(); i++){
                highscore.subList(10, highscore.size()).clear();
            }
        }
        return highscore;
    }
    @XmlElement

    public void setHighscore(ArrayList<Score> scores) {
        this.highscore = scores;
    }

    public void addScore(Score score){
        highscore.add(score);
    }
}
