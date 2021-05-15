package snakegame.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import snakegame.DAO.HighScoreDao;
import snakegame.DAO.Score;

import java.util.ArrayList;

public class HighScoreController {
    private ArrayList<Score> highScoreArrayList;
    private ObservableList<Score> list;
    private HighScoreDao highScoreDao = new HighScoreDao();
    public TableView<Score> listView = new TableView<>();
    public Button highscore = new Button("Eredmények");
    public Label scoreLabel = new Label("Score:");
    public Label scoreText = new Label("0");
    private void initTable() {
        TableColumn name = new TableColumn("Score");
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory<>("score"));

        TableColumn score = new TableColumn("Date");
        score.setMinWidth(50);
        score.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        name.setStyle("-fx-font-family: 'Clear-sans'; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: gray; -fx-alignment: center; -fx-background-color: transparent;");
        score.setStyle("-fx-font-family: 'Clear-sans'; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: gray; -fx-background-color: transparent;");
        listView.getColumns().addAll(name, score);

        highScoreArrayList = highScoreDao.getHighScores().getHighscore();
        list = FXCollections.observableArrayList();
        for (Score highScore : highScoreArrayList) {
            list.add(highScore);
        }

        listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        listView.setItems(list);
        listView.refresh();
    }

    public void refreshTable() {
        highScoreArrayList = highScoreDao.getHighScores().getHighscore();
        list = FXCollections.observableArrayList();
        for (Score highScore : highScoreArrayList) {
            list.add(highScore);
        }
        listView.setItems(list);
        listView.refresh();
    }
}