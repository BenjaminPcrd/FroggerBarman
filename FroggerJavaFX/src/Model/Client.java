package Model;

import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;

public class Client extends Personnage {
    private Boisson boisson;
    private boolean arrive;
    private boolean satisfait;
    private Text text;

    public Client(Mediator m, double x, double y, double w, double h, double speed, Boisson boisson, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.boisson = boisson;
        this.arrive = false;
        this.satisfait = false;
        this.text = new Text (getRect().getX(), getRect().getY(), boisson.toString());
        this.text.setVisible(false);
        this.getChildren().add(text);

    }

    public void moveTo(double xDest, double yDest) {
        text.setX(getRect().getX());
        text.setY(getRect().getY());
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

    public boolean isArrive() {
        return arrive;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
        text.setVisible(arrive);
    }

    public boolean isSatisfait() {
        return satisfait;
    }

    public void setSatisfait(boolean satisfait) {
        this.satisfait = satisfait;
    }
}
