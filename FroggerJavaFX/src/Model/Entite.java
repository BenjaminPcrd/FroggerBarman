package Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;

public abstract class Entite extends Group {
    private Rectangle rect;
    private ImageView imageView;

    public Entite(double x, double y, double w, double h, String imgPath) {
        this.rect = new Rectangle();
        this.rect.setWidth(w - 20);
        this.rect.setHeight(h - 20);
        this.rect.setTranslateX(10);
        this.rect.setTranslateY(10);
        this.rect.setX(x);
        this.rect.setY(y);
        this.getChildren().add(rect);
        try {
            this.imageView = new ImageView(new Image(new FileInputStream(imgPath)));
            this.imageView.setFitWidth(w);
            this.imageView.setFitHeight(h);
            this.getChildren().add(this.imageView);
            this.rect.setVisible(false);
            /*this.imageView.setX(x);
            this.imageView.setY(y);
            /*imageView.xProperty().bind(rect.xProperty());
            imageView.yProperty().bind(rect.yProperty());*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.rect.setFill(Color.WHITE);
        }



    }



    public ImageView getImageView() {
        return imageView;
    }

    public Rectangle getRect() {
        return rect;
    }

    public abstract void move(String mouvement);

}
