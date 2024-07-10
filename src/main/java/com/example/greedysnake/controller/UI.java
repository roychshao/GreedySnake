package com.example.greedysnake.controller;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class UI {

    private static UI instance;
    private VBox vbox;
    private ToolBar topBar;
	private Label scoreLabel;
    private Label gameOverLabel;
    private Button restartBtn;
    private Button pauseBtn;

	public UI() {
        topBar = new ToolBar();
        restartBtn = new Button("RESTART");
        restartBtn.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px;");
        pauseBtn = new Button("PAUSE");
        pauseBtn.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px;");

        scoreLabel = new Label("SCORE:" + 0);
        scoreLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: black; -fx-padding: 10px;");

        gameOverLabel = new Label("Game Over");
        gameOverLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #E06C75; -fx-padding: 10px;");

        topBar.getItems().addAll(scoreLabel, restartBtn, pauseBtn);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#ABB2BF"), CornerRadii.EMPTY, Insets.EMPTY)));
        vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().add(topBar);
    };

    public Button getRestartBtn() {
        return restartBtn;
    }

    public Button getPauseBtn() {
        return pauseBtn;
    }

    public VBox getVbox() {
        return vbox;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getGameOverLabel() {
        return gameOverLabel;
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }
}
