package Model;

public class Voiture extends Obstacle {

    private String direction;
    public Voiture(Mediator m, double x, double y, double w, double h, double speed, String direction, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.direction = direction;

    }

    public String getDirection() {
        return direction;
    }
}
