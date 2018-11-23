package Metier;

public class Obstacle extends Entite {
    private double speed;

    public Obstacle(double x, double y, double w, double h, double speed) {
        super(x, y, w, h);
        this.speed = speed;
    }
}
