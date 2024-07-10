
package com.example.greedysnake.domain;

import javafx.scene.shape.Polygon;
import java.awt.Point;

public class Trap extends Polygon {

    private Point position;

    public Trap(Point position) {
        this.position = position;
        getPoints().addAll( new Double[] {
            (double) (this.position.x * 20.0 + 9.0), (double) (this.position.y * 20.0) - 11.55 + 13.0,
            (double) (this.position.x * 20.0) - 9.0 + 10.0, (double) (this.position.y * 20.0) + 5.77 + 13.0,
            (double) (this.position.x * 20.0) + 9.0 + 10.0, (double) (this.position.y * 20.0) + 5.77 + 13.0
        });
        setStyle("-fx-stroke: #E06C75; -fx-stroke-width: 2; -fx-fill: #E06C75;");
    }

    public Point getPosition() {
        return position;
    }
}
