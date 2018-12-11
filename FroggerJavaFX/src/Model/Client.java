package Model;

public class Client extends Personnage {
    private Boisson boisson;

    public Client(Mediator m, double x, double y, double w, double h, double speed, Boisson boisson, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.boisson = boisson;
    }

    public Boisson getBoisson() {
        return boisson;
    }
}
