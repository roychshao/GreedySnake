package com.example.greedysnake.domain;

import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.awt.Point;
import com.example.greedysnake.controller.GameField;

public class Snake extends Pane {

    private LinkedList<SnakeBody> body;
    private Point direction;
    private Point lastTail;
    private GameField gameField = GameField.getInstance(800/20, 600/20);


    public Snake() {
        this.body = new LinkedList<>();
        this.body.add(new SnakeBody(new Point(200/20, 400/20)));
        this.direction = new Point(1, 0);
       getChildren().addAll(body); 
    }

    public boolean move() {
        Point nextHead = new Point(body.getFirst().getPosition().x + direction.x, body.getFirst().getPosition().y + direction.y);
        if (checkCollisionWithWall(nextHead) || checkCollisionWithSelf(nextHead))
            return false;

        // Store the last tail position
        lastTail = body.getLast().getPosition();
        gameField.releasePosition(lastTail);

        // Move each body part to the position of the next body part
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setPosition(body.get(i - 1).getPosition());
        }

        // Move the head in the direction of movement
        Point newHeadPosition = new Point(body.getFirst().getPosition().x + direction.x, body.getFirst().getPosition().y + direction.y);
        body.getFirst().setPosition(newHeadPosition);
        gameField.usePosition(newHeadPosition);
        return true;
    }

    public void grow() {
        // Add a new point in the last tail position
        SnakeBody newTail = new SnakeBody(lastTail);
        body.addLast(newTail);
        gameField.usePosition(newTail.getPosition());
        getChildren().add(newTail);
    }

    public boolean checkCollisionWithSelf(Point p) {
        for (int i = 1; i < body.size(); ++i) {
            if (p.x == body.get(i).getPosition().x &&
                p.y == body.get(i).getPosition().y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithWall(Point p) {
        if (p.x >= 600/20 ||
            p.y >= 500/20 ||
            p.x < 0 ||
            p.y < 0) {
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithFood(Food food) {
        if (body.getFirst().getPosition().x == food.getPosition().x && body.getFirst().getPosition().y == food.getPosition().y) {
            System.out.println("snake: " + body.getFirst().getPosition());
            System.out.println("food: " + food.getPosition());
            return true;
        }
        return false;
    }

    public void setDirection(Point newDirection) {
        // ignore the opposite direction
        if ((newDirection.x == 1 && direction.x == -1) ||
            (newDirection.x == -1 && direction.x == 1) ||
            (newDirection.y == 1 && direction.y == -1) ||
            (newDirection.y == -1 && direction.y == 1)) {
            return;
        }
        this.direction = newDirection;
    }
}
