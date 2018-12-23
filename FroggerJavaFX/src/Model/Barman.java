package Model;


import java.util.ArrayList;

public class Barman extends Personnage {
    private static final int tPlateau = 4;
    private ArrayList<Boisson> plateau;

    public Barman(Mediator m, double x, double y, double w, double h, double speed, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.plateau = new ArrayList<>();

    }

    public void ajouterBoisson(Bar bar, Boisson boisson) throws Exception {
        if (plateau.size() < tPlateau) {
            if (bar.getNbBoisson() > 0) {
                plateau.add(boisson);
                bar.setNbBoisson(bar.getNbBoisson() - 1);
            } else {
                throw new Exception("bar vide");
            }
        } else {
            throw new Exception("plateau plein");
        }
    }

    public boolean enleverBoisson(Boisson b) throws Exception {
        if(plateau.contains(b)) {
            plateau.remove(b);
            return true;
        } else {
            throw new Exception("la boisson n'est pas sur le plateau");
        }

    }

    public void viderPlateau() {
        plateau.clear();
    }

    public ArrayList<Boisson> getPlateau() {
        return plateau;
    }

}
