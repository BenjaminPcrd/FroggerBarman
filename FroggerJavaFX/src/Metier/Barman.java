package Metier;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Barman extends Personnage {
    private static final int tPlateau = 4;
    private ArrayList<Boisson> plateau;
    Rectangle rect;

    public Barman(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.plateau = new ArrayList<>();

        rect = new Rectangle();
        rect.setFill(Color.BLUE);
        rect.setWidth(w);
        rect.setHeight(h);
        rect.setX(x);
        rect.setY(y);

        this.getChildren().add(rect);
    }

    public void ajouterBoisson(Bar bar) throws Exception {
        if (plateau.size() < tPlateau) {
            if (bar.getNbBoisson() > 0) {
                plateau.add(bar.getBoisson());
                bar.setNbBoisson(bar.getNbBoisson() - 1);
            } else {
                throw new Exception("bar vide");
            }
        } else {
            throw new Exception("plateau plein");
        }
    }

    public void enleverBoisson(Boisson b) throws Exception {
        if(plateau.contains(b)) {
            plateau.remove(b);
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
