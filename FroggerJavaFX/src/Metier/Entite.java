package Metier;

import javafx.scene.Group;

public class Entite extends Group {
    private double x;
    private double y;
    private double w;
    private double h;

    public Entite(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
