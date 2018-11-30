package Controller;

import Model.Barman;
import Model.Voiture;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class Controller {
    private boolean up, down, left, right;
    private double latence = 200;

    public Controller() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }

    public void update(Scene scene, Barman b) {
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (up && b.getRect().getY() > 0) {
                    b.move("UP");
                }
                if (down && b.getRect().getY() < scene.getHeight() - b.getRect().getHeight()) {
                    b.move("DOWN");
                }
                if (left && b.getRect().getX() > 0) {
                    b.move("LEFT");
                }
                if (right && b.getRect().getX() < scene.getWidth() - b.getRect().getWidth()) {
                    b.move("RIGHT");
                }
            }
        };
        move.start();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    up = true;
                    break;
                case DOWN:
                    down = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
                case LEFT:
                    left = true;
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    up = false;
                    break;
                case DOWN:
                    down = false;
                    break;
                case RIGHT:
                    right = false;
                    break;
                case LEFT:
                    left = false;
                    break;
            }
        });
    }

    public void MoveVoiture(Scene scene, Voiture v ){
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (v.getDirection() == "LEFT") {
                    v.move("LEFT");
                    if(v.getRect().getX() + v.getRect().getWidth() + latence < 0) {
                        v.getRect().setX(scene.getWidth());
                    }

                }
                if (v.getDirection() == "RIGHT" && v.getRect().getX() < scene.getWidth() - v.getRect().getWidth()) {
                    v.move("RIGHT");
                }
            }
        };
        move.start();
    }
}
