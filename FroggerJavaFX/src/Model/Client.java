package Model;

import javafx.animation.AnimationTimer;

public class Client extends Personnage {
    private Boisson boisson;

    public Client(Mediator m, double x, double y, double w, double h, double speed, Boisson boisson, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.boisson = boisson;
    }

    public void moveTo(double xDest, double yDest) {
        if (getRect().getY() < yDest && getRect().getY() != yDest) {
            move("DOWN");
        }
        if (getRect().getY() > yDest && getRect().getY() != yDest) {
            move("UP");
        }
        if (getRect().getX() > xDest && getRect().getX() != xDest) {
            move("LEFT");
        }
        if (getRect().getX() < xDest && getRect().getX() != xDest) {
            move("RIGHT");
        }
    }

    public Boisson getBoisson() {
        return boisson;
    }
}
