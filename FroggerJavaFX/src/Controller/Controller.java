package Controller;

import Model.*;
import Vue.Plateau;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class Controller {
    private boolean up, down, left, right, space;
    private double latence = 200;

    public Controller() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.space = false;
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
        evenementClavier(scene);
    }

    public void evenementClavier(Scene scene){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
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
                case SPACE:
                    space = true;

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
                case SPACE:
                    space = false;
            }
        });
    }



    public void MoveVoiture(Scene scene, Voiture v, String direction) {
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch(direction) {
                    case "LEFT":
                        v.move("LEFT");
                        if (v.getRect().getX() < 0 - v.getRect().getWidth() - latence) {
                            v.getRect().setX(scene.getWidth());
                        }
                        break;
                    case "RIGHT":
                        v.move("RIGHT");
                        if (v.getRect().getX() > scene.getWidth() + latence) {
                            v.getRect().setX(0 - v.getRect().getWidth());
                        }
                        break;
                }
            }
        };
        move.start();
    }

    public void Collision(Scene scene, Barman b, Voiture v) {
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(b.getRect().intersects(v.getRect().getBoundsInLocal())){
                    b.resetPos(scene.getWidth()/2-25, scene.getHeight()-50);
                }
            }
        };
        collision.start();
    }

    public void collisionClient(Plateau p, Barman b, Client client){
        AnimationTimer collision = new AnimationTimer() {

            boolean pris = false;
            @Override
            public void handle(long l) {
                if (!pris)  {
                    if (b.getRect().intersects(client.getRect().getBoundsInLocal())) {
                        try {
                            pris = b.enleverBoisson(client.getBoisson());
                            System.out.println("boisson prise");
                            p.enlever(client);


                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        };
        collision.start();
    }

    public void collisionBar(Scene scene, Barman b, Bar bar){

        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(b.getRect().intersects(bar.getRect().getBoundsInLocal())) {
                    if (space){
                        prendreBoisson(b, bar);
                    }
                }
            }

        };
        collision.start();
        //evenementClavier(scene);
    }

    public void prendreBoisson(Barman b, Bar bar){
        try {
            b.ajouterBoisson(bar, bar.getBoisson());
            System.out.println("Boisson ajoutée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
