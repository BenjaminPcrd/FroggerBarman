package Model;

public class Bar extends Entite {
    private Boisson boisson;
    private int nbBoisson;

    public Bar(Mediator m, double x, double y, double w, double h, Boisson boisson, int nbBoisson, String imgPath) {
        super(m, x, y, w, h, imgPath);
        this.boisson = boisson;
        this.nbBoisson = nbBoisson;
    }

    public Boisson getBoisson() {
        return boisson;
    }

    public int getNbBoisson() {
        return nbBoisson;
    }

    public void setNbBoisson(int nbBoisson) {
        this.nbBoisson = nbBoisson;
    }

}
