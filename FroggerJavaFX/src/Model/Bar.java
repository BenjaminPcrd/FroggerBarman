package Model;
import javafx.scene.text.Text;

public class Bar extends Entite {
    private Boisson boisson;
    private int nbBoisson;
    private Text text;
    public Bar(Mediator m, double x, double y, double w, double h, Boisson boisson, int nbBoisson, String imgPath) {
        super(m, x, y, w, h, imgPath);
        this.boisson = boisson;
        this.nbBoisson = nbBoisson;
        this.text = new Text (getRect().getX(), getRect().getY(), boisson.toString());
       // this.text.setVisible(false);
        this.getChildren().add(text);

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
