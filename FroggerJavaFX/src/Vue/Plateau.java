package Vue;

import Controller.Controller;
import Model.Barman;
import Model.Voiture;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Plateau {
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Barman Frogger");
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);
        Controller c = new Controller();
        Barman b = new Barman(scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 1, "images/loic.png");
        root.getChildren().add(b);

       /* b.getRect().xProperty().bind(b.getImageView().xProperty());
        b.getRect().yProperty().bind(b.getImageView().yProperty());*/
       b.getImageView().xProperty().bind(b.getRect().xProperty());
       b.getImageView().yProperty().bind(b.getRect().yProperty());

        Voiture v = new Voiture(710, 360, 200, 75, 1, "images/voiture11.png");
        root.getChildren().add(v);

        c.update(scene, b);
        c.MoveVoiture(scene, v, "LEFT");

        c.Collision(scene, b, v);
    }

}
