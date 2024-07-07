package com.example.greedysnake.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.awt.Point;

public class GameField {

    private static GameField instance;
    private List<Point> emptyPosition;
    private Random random;
    private Pane gamePane;

    public GameField() {}

    public GameField(int width, int height) {

        this.emptyPosition = new ArrayList<>();
        this.random = new Random();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.emptyPosition.add(new Point(x, y));
            }
        }

        gamePane = new Pane(); 
        gamePane.setPrefSize(600, 500);
        gamePane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        gamePane.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public static GameField getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameField(width, height);
        }
        return instance;
    }

    public Point getAndUseAnEmptyPosition() {
        int randomIdx = random.nextInt(emptyPosition.size());
        Point emptyPoint = emptyPosition.get(randomIdx);
        this.emptyPosition.remove(randomIdx);
        return emptyPoint;
    }

    public void usePosition(Point p) {
        this.emptyPosition.remove(p);
    }

    public void releasePosition(Point p) {
        this.emptyPosition.add(p);
    }

    public Pane getGamePane() {
        return gamePane;
    }
}
