package com.example.greedysnake.domain;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.awt.Point;
import com.example.greedysnake.controller.GameField;

public class Food extends Circle {

    private Point position;
    private GameField gameField = GameField.getInstance(600/20, 500/20);

    public Food() {
        Point emptyPoint = gameField.getAndUseAnEmptyPosition();
        this.position = emptyPoint;
        setRadius(10); // Half of the previous square size
        setFill(Color.GREEN);
        relocate(position.x * 20, position.y * 20);
    }

    public Point getPosition() {
        return position;
    }
}
