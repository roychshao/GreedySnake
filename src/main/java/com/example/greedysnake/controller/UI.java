package com.example.greedysnake.controller;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class UI {

    private static UI instance;
    private VBox top;

	private Label scoreLabelInstance;


	public UI() {
        scoreLabelInstance = new Label("SCORE:" + 0);
        scoreLabelInstance.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #333333; -fx-padding: 10px;");
        top = new VBox();
        top.setAlignment(Pos.TOP_CENTER);
        top.getChildren().add(scoreLabelInstance);
        top.setAlignment(Pos.TOP_CENTER);
    };

    public void setScore(int score) {
        Label scoreLabelInstance = (Label) top.getChildren().get(0);
        if (scoreLabelInstance != null) {
            scoreLabelInstance.setText("SCORE:" + score);
        }
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public VBox getTop() {
        return top;
    }

    public Label getScoreLabelInstance() {
        return scoreLabelInstance;
    }
}
