package Metier;

public class Personnage extends Entite {

    private double speed;

    public Personnage(double x, double y, double w, double h, double speed, String imgPath) {
        super(x, y, w, h, imgPath);
        this.speed = speed;
        if (this.getImageView() != null) {
            this.getImageView().setY(this.getRect().getY());
            this.getImageView().setX(this.getRect().getX());

        }
    }
    @Override
    public void move(String direction){
        switch (direction) {
            case "DOWN":
                this.getRect().setY(this.getRect().getY() + this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setY(this.getRect().getY());
                    this.getImageView().setRotate(180);
                }
                break;
            case "UP":
                this.getRect().setY(this.getRect().getY() - this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setY(this.getRect().getY());
                    this.getImageView().setRotate(0);
                }
                break;
            case "RIGHT":
                this.getRect().setX(this.getRect().getX() + this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(90);
                }
                break;
            case "LEFT":
                this.getRect().setX(this.getRect().getX() - this.speed);
                if (this.getImageView() != null) {
                    this.getImageView().setX(this.getRect().getX());
                    this.getImageView().setRotate(270);
                }
                break;

        }
    }
}
