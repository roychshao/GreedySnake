package com.example.greedysnake.domain;

import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.awt.Point;

public class Snake extends Pane {

    private LinkedList<SnakeBody> body;
    private Point direction;
    private Point lastTail;


    public Snake() {
        this.body = new LinkedList<>();
        this.body.add(new SnakeBody(new Point(0, 0)));
        this.direction = new Point(0, 1);
       getChildren().addAll(body); 
    }

    public void move() {
        // Store the last tail position
        lastTail = body.getLast().getPosition();

        // Move each body part to the position of the next body part
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setPosition(body.get(i - 1).getPosition());
        }

        // Move the head in the direction of movement
        Point newHeadPosition = new Point(body.getFirst().getPosition().x + direction.x, body.getFirst().getPosition().y + direction.y);
        body.getFirst().setPosition(newHeadPosition);
    }

    public void grow() {
        // Add a new point in the last tail position
        SnakeBody newTail = new SnakeBody(lastTail);
        body.addLast(newTail);
        getChildren().add(newTail);
    }

    public boolean checkCollisionWithSelf() {
        for (int i = 1; i < body.size(); ++i) {
            if (body.getFirst().getPosition().x == body.get(i).getPosition().x &&
                body.getFirst().getPosition().y == body.get(i).getPosition().y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionWithWall() {
        if (body.getFirst().getPosition().x >= 800/20 ||
            body.getFirst().getPosition().y >= 600/20 ||
            body.getFirst().getPosition().x < 0 ||
            body.getFirst().getPosition().y < 0) {
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithFood(Food food) {
        if (body.getFirst().getPosition().x == food.getPosition().x && body.getFirst().getPosition().y == food.getPosition().y) {
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
