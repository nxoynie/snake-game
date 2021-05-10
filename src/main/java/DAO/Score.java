package DAO;


import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Score {
    private SimpleStringProperty date;
    private SimpleStringProperty score;

    public Score(){
        this.date = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
    }

    public Score(String date, String score){
        this.date = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.date.set(date);
        this.score.set(score);
    }

    public String getDate() {
        return date.get();
    }

    public String getScore() {
        return score.get();
    }
@XmlElement
    public void setDate(String date) {
        this.date.set(date);
    }
@XmlElement
    public void setScore(String score) {
        this.score.set(score);
    }

    @Override
    public String toString() {
         return "Performance{" + "date=" + date + ", score=" + score + "}";
    }
}
