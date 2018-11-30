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

    public void update(Scene scene, Barman b, Voiture v) {
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
                double left = b.getRect().getX();
                double right = b.getRect().getX() + b.getRect().getWidth();
                double top = b.getRect().getY();
                double bottom = b.getRect().getY() + b.getRect().getHeight();

                double oleft = v.getRect().getX();
                double oright = v.getRect().getX() + v.getRect().getWidth();
                double otop = v.getRect().getY();
                double obottom = v.getRect().getY() + v.getRect().getHeight();

                if( !(left >= oright ||
                        right <= oleft ||
                        top >= obottom ||
                        bottom <= otop)) {
                    b.getRect().setX(scene.getWidth()/2-25);
                    b.getRect().setY(scene.getHeight()-50);
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

    public void MoveVoiture(Scene scene, Voiture v, String direction){
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch(direction) {
                    case "LEFT":
                        v.move("LEFT");
                        if (v.getRect().getX() < 0) {
                            v.getRect().setX(scene.getWidth());
                        }
                        break;
                    case "RIGHT":
                        v.move("RIGHT");
                        if (v.getRect().getX() > scene.getWidth()) {
                            v.getRect().setX(0 - v.getRect().getWidth());
                        }
                        break;
                }
            }
        };
        move.start();
    }
}
