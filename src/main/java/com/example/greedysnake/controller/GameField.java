package com.example.greedysnake.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;

public class GameField {

    private static GameField instance;
    private List<Point> emptyPosition;
    private Random random;
    private int width;
    private int height;

    public GameField() {}

    public GameField(int width, int height) {

        this.emptyPosition = new ArrayList<>();
        this.random = new Random();
        this.width = width;
        this.height = height;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.emptyPosition.add(new Point(x, y));
            }
        }
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
        usePosition(randomIdx);
        return emptyPoint;
    }

    public void usePosition(int idx) {
        instance.emptyPosition.remove(idx);
    }

    public void releasePosition(Point p) {
        instance.emptyPosition.add(p);
    }
}
