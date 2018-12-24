package Model;

public class Voiture extends Obstacle {
    private String direction;
    private double speed;

    public Voiture(Mediator m, double x, double y, double w, double h, double speed, String direction, String imgPath) {
        super(m, x, y, w, h, imgPath);
        this.direction = direction;
        this.speed = speed;
    }

    public void move() {
        if (this.getImageView() != null) {
            this.getImageView().setY(this.getRect().getY());
        }
        switch(direction) {
            case "LEFT":
                this.getRect().setX(this.getRect().getX() - speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(0);
                }
                break;
            case "RIGHT":
                this.getRect().setX(this.getRect().getX() + speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(180);
                }
                break;
        }
    }

    public String getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
