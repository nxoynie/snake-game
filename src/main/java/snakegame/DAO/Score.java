package snakegame.DAO;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Score {
    private SimpleStringProperty score;
    private SimpleStringProperty date;
    public Score() {
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
    }
    public Score(String score, String date) {
        this.score = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.score.set(score);
        this.date.set(date);
    }
    public String getScore() {
        return score.get();
    }
    @XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }
    public String getDate() {
        return date.get();
    }
    @XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", date=" + date +
                '}';
    }
}
