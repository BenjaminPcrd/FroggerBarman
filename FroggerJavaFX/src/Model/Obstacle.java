package Model;

public class Obstacle extends Entite {
    private double speed;

    public Obstacle(double x, double y, double w, double h, double speed, String imgPath) {
        super(x, y, w, h, imgPath);
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
                    this.getImageView().setRotate(270);
                }
                break;
            case "RIGHT":
                this.getRect().setX(this.getRect().getX() + this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(90);
                }
                break;
        }
    }
}
