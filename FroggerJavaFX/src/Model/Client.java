package Model;

import javafx.animation.AnimationTimer;

public class Client extends Personnage {
    private Boisson boisson;

    public Client(Mediator m, double x, double y, double w, double h, double speed, Boisson boisson, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.boisson = boisson;
    }

    public void moveTo(double xDest, double yDest) {
        if (getRect().getX() > xDest) {
            move("LEFT");
        }
        if (getRect().getX() < xDest) {
            move("RIGHT");
        }
        if (getRect().getY() > yDest) {
            move("UP");
        }
        if (getRect().getY() < yDest) {
            move("DOWN");
        }
    }

    public Boisson getBoisson() {
        return boisson;
    }
}
