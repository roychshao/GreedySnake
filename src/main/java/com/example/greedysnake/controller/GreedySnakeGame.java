package com.example.greedysnake.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import com.example.greedysnake.domain.*;

public class GreedySnakeGame extends Application {

    private int UPDATE_INTERVAL = 100_000_000; // nanoseconds
    private long lastUpdate = 0;
    private Snake snake;
    private Food food;
    private int score = 0;
    private StackPane root;
    private GameField gameField = GameField.getInstance(600/20, 500/20);
    private VBox centerBox;
    private UI ui = UI.getInstance();
    private Button restartBtn = ui.getRestartBtn();
    private Button pauseBtn = ui.getPauseBtn();
    private Label scoreLabel = ui.getScoreLabel();
    private Label gameOverLabel = ui.getGameOverLabel();
    private AnimationTimer gameLoop;
    private Queue<Point> trapQueue;
    private boolean running = false;

    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        snake = new Snake();
        food = new Food();
        trapQueue = new LinkedList<>();
        Scene scene = new Scene(root, 800, 700);

        gameField.getGamePane().getChildren().addAll(snake, food);
        centerBox = new VBox(gameField.getGamePane());
        centerBox.setAlignment(Pos.CENTER);;

        root.getChildren().addAll(centerBox, ui.getVbox());

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL) {
                    onUpdate();
                    lastUpdate = now;
                }
            }
        };

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

        // restart
        restartBtn.setOnAction(event -> {
            root.getChildren().remove(gameOverLabel);
            // clear
            gameField.getGamePane().getChildren().clear();
            GameField.resetInstance();
            gameField = GameField.getInstance(600/20, 500/20);
            gameLoop.stop();
            centerBox.getChildren().clear();
            root.getChildren().clear();
            trapQueue.clear();
            // renew
            snake = new Snake();
            food = new Food();
            score = 0;
            scoreLabel.setText("SCORE:" + score);
            // add nodes again
            gameField.getGamePane().getChildren().addAll(snake, food);
            centerBox.getChildren().add(gameField.getGamePane());
            root.getChildren().addAll(centerBox, ui.getVbox());
            root.requestFocus();
            gameLoop.start();
            running = true;
            UPDATE_INTERVAL = 100_000_000;
            pauseBtn.setDisable(false);
            restartBtn.setDisable(true);
        });

        // pause
        pauseBtn.setOnAction(event -> {
            if (running) {
                gameLoop.stop();
                running = false;
                pauseBtn.setText("RESUME");
            } else {
                gameLoop.start();
                running = true;
                pauseBtn.setText("PAUSE");
            }
            root.requestFocus();
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0.1, Color.web("#56B6C2")), new Stop(1.0, Color.web("#C678DD")));
        root.setBackground(new Background(new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        gameLoop.start();
        running = true;
        pauseBtn.setDisable(false);
        restartBtn.setDisable(true);
    }

    private void onUpdate() {

        if (!trapQueue.isEmpty() && gameField.isEmpty(trapQueue.peek())) {
            Trap newTrap = new Trap(trapQueue.poll());
            gameField.usePosition(newTrap.getPosition());
            gameField.getGamePane().getChildren().add(newTrap);
            gameField.getTraps().add(newTrap);
        }

        if (!snake.move()) {
            gameOver();
        }

        if (snake.checkCollisionWithFood(food)) {
            score++;
            scoreLabel.setText("SCORE:" + score);

            // speedup the game every 10 score
            if (score != 0 && score % 10 == 0) {
                UPDATE_INTERVAL -= 5_000_000;
            }

            snake.grow();
            gameField.getGamePane().getChildren().remove(food);
            trapQueue.add(food.getPosition());
            food = new Food();
            gameField.getGamePane().getChildren().addAll(food);
        }
    }

    private void gameOver() {
        gameLoop.stop();
        running = false;
        gameOverLabel.setAlignment(Pos.CENTER);
        root.getChildren().add(gameOverLabel);
        pauseBtn.setDisable(true);
        restartBtn.setDisable(false);
    }
}
