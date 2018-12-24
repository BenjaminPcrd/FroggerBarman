package Model;

public abstract class Obstacle extends Entite {

    public Obstacle(Mediator m, double x, double y, double w, double h, String imgPath) {
        super(m, x, y, w, h, imgPath);
    }
}
