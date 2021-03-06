package Controller;

import Model.*;
import Vue.Plateau;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Controller {
    private boolean up, down, left, right, space, enter;
    private double latence = 200;
    Plateau plateau;

    public Controller(Plateau plateau) {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.space = false;
        this.enter = false;
        this.plateau = plateau;
    }

    public void updateBarman(Scene scene, Barman b) {
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (up && b.getRect().getY() > 0 || b.getMediator().collisionTerrePlein(b) != null) {
                    b.move("UP");
                }
                if (down && b.getRect().getY() < scene.getHeight() - b.getRect().getHeight() || b.getMediator().collisionTerrePlein(b) != null) {
                    b.move("DOWN");
                }
                if (left && b.getRect().getX() > 0 || b.getMediator().collisionTerrePlein(b) != null) {
                    b.move("LEFT");
                }
                if (right && b.getRect().getX() < scene.getWidth() - b.getRect().getWidth() || b.getMediator().collisionTerrePlein(b) != null) {
                    b.move("RIGHT");
                }

                /*switch (b.getPlateau().size()) {
                    case 1:
                    case 2: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 3: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 4: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 5: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 6: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 7: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 8: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 9: b.setSpeed(b.getSpeed() - 0.2); break;
                    case 10: b.setSpeed(b.getSpeed() - 0.2); break;
                }*/
            }
        };
        move.start();
    }

    public void setEvenementClavier(Scene scene){
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.space = false;
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP: up = true; break;
                case DOWN: down = true; break;
                case RIGHT: right = true; break;
                case LEFT: left = true; break;
                case SPACE: space = true; break;
                case ENTER: enter = true; break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP: up = false; break;
                case DOWN: down = false; break;
                case RIGHT: right = false; break;
                case LEFT: left = false; break;
                case SPACE: space = false; break;
                case ENTER: enter = false; break;
            }
        });
    }

    public void moveVoiture(Scene scene, Voiture v) {
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                switch(v.getDirection()) {
                    case "LEFT":
                        v.move();
                        if (v.getRect().getX() < 0 - v.getRect().getWidth() - latence) {
                            v.getRect().setX(scene.getWidth());
                        }
                        break;
                    case "RIGHT":
                        v.move();
                        if (v.getRect().getX() > scene.getWidth() + latence) {
                            v.getRect().setX(0 - v.getRect().getWidth());
                        }
                        break;
                }
            }
        };
        move.start();
    }

    public void collisionVoiture(Scene scene, Barman b) {
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(b.getMediator().collisionVoiture(b) != null) {
                    b.resetPos(scene.getWidth()/2-25, scene.getHeight()-50);
                    b.viderPlateau();
                    System.out.println(b.getNbVie());
                }
            }
        };
        collision.start();

    }

    public void collisionEntreVoiture(ArrayList<Voiture> voitures, double ecart, double max, double min) {
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                /*for(Voiture v : voitures) {
                    if(v.getMediator().collisionVoiture(v) != null) {
                        this.stop();
                        Voiture v2 = (Voiture)v.getMediator().collisionVoiture(v);
                        if (v.getDirection() == "LEFT") {
                            v2.setSpeed(v2.getSpeed() - ecart);
                            v.setSpeed(v.getSpeed() + ecart);
                        }
                        if (v.getDirection() == "RIGHT") {
                            v2.setSpeed(v2.getSpeed() + ecart);
                            v.setSpeed(v.getSpeed() - ecart);
                        }
                        this.start();
                    }
                }*/
                for(Voiture v : voitures) {
                    if(v.getMediator().collisionVoiture(v) != null) {
                        Voiture v2 = (Voiture)v.getMediator().collisionVoiture(v);
                        this.stop();
                        switch (v.getDirection()) {
                            case "LEFT":
                                v2.setSpeed(Math.abs(v2.getSpeed() + ecart));
                                v.setSpeed(Math.abs(v.getSpeed() - ecart));
                                break;
                            case "RIGHT":
                                v2.setSpeed(Math.abs(v2.getSpeed() - ecart));
                                v.setSpeed(Math.abs(v.getSpeed() + ecart));
                                break;
                        }
                        if (v.getSpeed() > max || v.getSpeed() < min) {
                            v.setSpeed(max - min);
                        }
                        if (v2.getSpeed() > max || v2.getSpeed() < min) {
                            v2.setSpeed(max - min);
                        }
                        this.start();
                    }
                }
            }
        };
        collision.start();
    }



    public void collisionClient(Barman b){
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(b.getMediator().collisionClient(b) != null) {
                    Client client = (Client)b.getMediator().collisionClient(b);
                    if(enter) {
                        try {
                            b.enleverBoisson(client.getBoisson());
                            b.setSpeed(b.getSpeed() + 0.1); // modification de la vitese
                            client.setSatisfait(true);
                            client.setArrive(false); // enlever le texte
                            System.out.println("Boisson prise : " + client.getBoisson() + " satisfait ? " + client.isSatisfait());
                            client.getMediator().enleverEntite(client);
                            moveClient(client, 1300, 60, plateau.getMediator());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        enter = false;
                    }
                }
            }
        };
        collision.start();
    }



    public void moveClient(Client c, double x, double y, Mediator m){
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                c.moveTo(x, y);
                if (c.getRect().getX() >= x) { // ne fonctionne que quand on fait déplacer le client vers la droite à cause de cette ligne
                    c.setArrive(true); // afficher le texte
                    c.getImageView().setRotate(180);
                    if (m.collisionClient(c) != null){
                        if(x >= plateau.getScene().getHeight()) {
                            moveClient( c,  x - 20 ,  y,  m);
                        } else {
                            moveClient( c,  x + 50 ,  y,  m);
                        }
                    }
                    System.out.println("arrivé ? " + c.isArrive());
                    this.stop();
                }
            }
        };
        move.start();
    }


    public void collisionBar(Barman b){
        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                    if(b.getMediator().collisionBar(b) != null) {
                        Bar bar = (Bar)b.getMediator().collisionBar(b);
                        if(enter) {
                            try {
                                b.ajouterBoisson(bar, bar.getBoisson());
                                b.setSpeed(b.getSpeed() - 0.1); // modification de la vitesse
                                System.out.println("Boisson ajoutée : " + bar.getBoisson());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                           enter = false;
                        }
                    }
            }
        };
        collision.start();
    }
}
