package Model;

public class Obstacle extends Entite {
    private double speed;

    public Obstacle(Mediator m, double x, double y, double w, double h, double speed, String imgPath) {
        super(m, x, y, w, h, imgPath);
        this.speed = speed;
    }

    @Override
    public void move(String direction) {
        if (this.getImageView() != null) {
            this.getImageView().setY(this.getRect().getY());
        }
        switch(direction) {
            case "LEFT":
                this.getRect().setX(this.getRect().getX() - this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(0);
                }
                break;
            case "RIGHT":
                this.getRect().setX(this.getRect().getX() + this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(180);
                }
                break;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void changerVitesse() {
        if(speed < 0.5) {
            speed += 0.3;
        } else {
            speed -= 0.3;
        }
    }
}
