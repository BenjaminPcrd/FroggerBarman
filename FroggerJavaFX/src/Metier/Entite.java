package Metier;

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
        this.rect.setWidth(w);
        this.rect.setHeight(h);
        this.rect.setX(x);
        this.rect.setY(y);
        this.getChildren().add(rect);
        Image img;
        try {
            img = new Image(new FileInputStream(imgPath));
            imageView = new ImageView(img);
            imageView.setFitWidth(w);
            imageView.setFitHeight(h);
            this.getChildren().add(imageView);
            this.rect.setVisible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.rect.setFill(Color.WHITE);
        }



    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public abstract void move(String mouvement);

}
