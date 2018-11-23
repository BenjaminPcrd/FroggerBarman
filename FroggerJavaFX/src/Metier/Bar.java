package Metier;

public class Bar extends Entite {
    private Boisson boisson;
    private int nbBoisson;

    public Bar(double x, double y, double w, double h, Boisson boisson, int nbBoisson) {
        super(x, y, w, h);
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
