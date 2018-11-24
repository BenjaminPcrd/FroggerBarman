package Metier;

public class Obstacle extends Entite {
    private double speed;

    public Obstacle(double x, double y, double w, double h, double speed, String imgPath) {
        super(x, y, w, h, imgPath);
        this.speed = speed;
    }

    @Override
    public void move(String mouvement) {

    }
}
