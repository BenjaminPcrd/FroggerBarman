package Metier;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Barman extends Personnage {
    private static final int tPlateau = 4;
    private ArrayList<Boisson> plateau;
    private Rectangle rect;
    private double speed;

    public Barman(double x, double y, double w, double h, double speed) {
        super(x, y, w, h);
        this.plateau = new ArrayList<>();
        this.speed = speed;

        rect = new Rectangle();
        rect.setFill(Color.WHITE);
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
