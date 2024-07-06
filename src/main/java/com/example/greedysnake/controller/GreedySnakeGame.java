package com.example.greedysnake.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.awt.Point;

import com.example.greedysnake.domain.*;

public class GreedySnakeGame extends Application {

    private static final long UPDATE_INTERVAL = 80_000_000; // nanoseconds
    private long lastUpdate = 0;
    private Snake snake;
    private Food food;
    private Pane root;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        snake = new Snake();
        food = new Food();

        root.getChildren().addAll(snake, food);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL) {
                    onUpdate();
                    lastUpdate = now;
                }
            }
        }.start();

        primaryStage.setScene(scene);
        primaryStage.show();

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
        snake.move();
        if (snake.checkCollisionWithSelf()) {
            // TODO: game over
            System.out.println("GAMEOVER");
        }
        if (snake.checkCollisionWithWall()) {
            // TODO: game over
            System.out.println("GAMEOVER");
        }
        if (snake.checkCollisionWithFood(food)) {
            snake.grow();
            root.getChildren().remove(food);
            food = new Food();
            root.getChildren().add(food);
        }
    }
}
