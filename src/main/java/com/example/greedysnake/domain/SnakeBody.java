package com.example.greedysnake.domain;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import java.awt.Point;

public class SnakeBody extends Region {
    private Point position;

    public SnakeBody(Point position) {
        this.position = position;
        setPrefSize(20, 20);
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        relocate(position.x * 20, position.y * 20);
    }

    public void setPosition(Point position) {
        this.position = position;
        relocate(position.x * 20, position.y * 20);
    }

    public Point getPosition() {
        return position;
    }
}
