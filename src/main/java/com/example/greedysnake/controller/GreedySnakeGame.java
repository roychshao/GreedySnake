package com.example.greedysnake.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import java.awt.Point;

import com.example.greedysnake.domain.*;

public class GreedySnakeGame extends Application {

    private static final long UPDATE_INTERVAL = 80_000_000; // nanoseconds
    private long lastUpdate = 0;
    private Snake snake;
    private Food food;
    private int score = 0;
    private StackPane root;
    private GameField gameField = GameField.getInstance(600/20, 500/20);
    private UI ui = UI.getInstance();
    private AnimationTimer gameLoop;

    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        snake = new Snake();
        food = new Food();
        Scene scene = new Scene(root, 800, 700);
        
        gameField.getGamePane().getChildren().addAll(snake, food);
        VBox centerBox = new VBox(gameField.getGamePane());
        centerBox.setAlignment(Pos.CENTER);;

        root.getChildren().addAll(centerBox, ui.getTop());

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL) {
                    onUpdate();
                    lastUpdate = now;
                }
            }
        };

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        gameLoop.start();

        // key listener
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    snake.setDirection(new Point(0, -1));
                    break;
                case DOWN:
                    snake.setDirection(new Point(0, 1));
                    break;
                case LEFT:
                    snake.setDirection(new Point(-1, 0));
                    break;
                case RIGHT:
                    snake.setDirection(new Point(1, 0));
                    break;
                default:
                    break;
            }
        });
    }

    private void onUpdate() {
        
        if (!snake.move()) {
            gameOver();
        }
        
        if (snake.checkCollisionWithFood(food)) {
            score++;
            ui.setScore(score); 
            snake.grow();
            gameField.getGamePane().getChildren().remove(food);
            gameField.releasePosition(food.getPosition());
            food = new Food();
            gameField.getGamePane().getChildren().add(food);
        }
    }

    private void gameOver() {
        gameLoop.stop();
        System.out.println("Game Over!");
    }
}
