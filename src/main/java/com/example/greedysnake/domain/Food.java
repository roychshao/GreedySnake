package com.example.greedysnake.domain;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import java.awt.Point;
import com.example.greedysnake.controller.GameField;


public class Food extends Region {

    private Point position;
    private GameField gameField = GameField.getInstance(800/20, 600/20);

    public Food() {
        Point emptyPoint = gameField.getAndUseAnEmptyPosition();
        System.out.println(emptyPoint);
        this.position = emptyPoint;
        setPrefSize(20, 20);
        setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        relocate(position.x * 20, position.y * 20);
    }

    public Point getPosition() {
        return position;
    }
}
