package Model;

public class Client extends Personnage {
    private Boisson boisson;

    public Client(double x, double y, double w, double h, double speed, Boisson boisson, String imgPath) {
        super(x, y, w, h, speed, imgPath);
        this.boisson = boisson;
    }
}
