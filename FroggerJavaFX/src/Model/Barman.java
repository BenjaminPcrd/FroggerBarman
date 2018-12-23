package Model;


import java.util.ArrayList;

public class Barman extends Personnage {
    private int taillePlateau;
    private ArrayList<Boisson> plateau;

    public Barman(Mediator m, double x, double y, double w, double h, double speed, String imgPath) {
        super(m, x, y, w, h, speed, imgPath);
        this.plateau = new ArrayList<>();
        this.taillePlateau = 4;
    }

    public void ajouterBoisson(Bar bar, Boisson boisson) throws Exception {
        if (plateau.size() < taillePlateau) {
            if (bar.getNbBoisson() > 0) {
                plateau.add(boisson);
                bar.setNbBoisson(bar.getNbBoisson() - 1);
            } else {
                throw new Exception("bar vide");
            }
        } else {
            throw new Exception("plateau plein, " + taillePlateau + " boissons maximum");
        }
    }

    public void enleverBoisson(Boisson b) throws Exception {
        if(plateau.contains(b)) {
            plateau.remove(b);
        } else {
            throw new Exception("la boisson n'est pas sur le plateau");
        }

    }


    public void resetPos(double x, double y) {
        this.getRect().setX(x);
        this.getRect().setY(y);
        if (this.getImageView() != null) {
            this.getImageView().setX(this.getRect().getX());
            this.getImageView().setY(this.getRect().getY());
            this.getImageView().setRotate(0);
        }
    }

    public void viderPlateau() {
        plateau.clear();
        System.out.println("Le plateau s'est renversé !");
    }

    public ArrayList<Boisson> getPlateau() {
        return plateau;
    }

    public int getTaillePlateau() {
        return taillePlateau;
    }

    public void setTaillePlateau(int taillePlateau) {
        this.taillePlateau = taillePlateau;
    }
}
