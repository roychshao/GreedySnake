package com.example.greedysnake.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.greedysnake.domain.Trap;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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

    private static volatile GameField instance;
    private List<Point> emptyPosition;
    private List<Trap> traps;
    private Random random;
    private Pane gamePane;
    private static int width;
    private static int height;

    public GameField() {}

    private GameField(int w, int h) {

        this.emptyPosition = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.random = new Random();
        width = w;
        height = h;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.emptyPosition.add(new Point(x, y));
            }
        }

        gamePane = new Pane(); 
        gamePane.setPrefSize(600, 500);
        gamePane.setBackground(new Background(new BackgroundFill(Color.web("#282C34"), new CornerRadii(15), new Insets(5, 5, 5, 5))));
        gamePane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        gamePane.setBorder(new Border(new BorderStroke(Color.web("#ABB2BF"), BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
    }

    public static void resetInstance() {
        instance = new GameField(width, height);
    }

    public static GameField getInstance(int width, int height) {
        GameField result = instance;
        if (result == null) {
            synchronized (GameField.class) {
                result = instance;
                if (result == null) {
                    instance = result = new GameField(width, height);
                }
            }
        }
        return result;
    }

    public synchronized Point getAndUseAnEmptyPosition() {
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

    public boolean isEmpty(Point p) {
        return this.emptyPosition.contains(p);
    }

    public List<Trap> getTraps() {
        return traps;
    }

    public Pane getGamePane() {
        return gamePane;
    }
}
