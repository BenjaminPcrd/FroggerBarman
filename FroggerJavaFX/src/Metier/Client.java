package Metier;

public class Client extends Personnage {
    private Boisson boisson;

    public Client(double x, double y, double w, double h, Boisson boisson) {
        super(x, y, w, h);
        this.boisson = boisson;
    }
}
