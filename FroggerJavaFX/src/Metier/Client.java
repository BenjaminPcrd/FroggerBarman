package Metier;

public class Client extends Personnage {
    private Boisson b;

    public Client(double x, double y, double w, double h, Boisson b) {
        super(x, y, w, h);
        this.b = b;
    }
}
