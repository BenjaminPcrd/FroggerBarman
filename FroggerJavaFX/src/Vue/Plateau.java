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

        Barman b = new Barman(scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 2, "images/loiic.png");
        root.getChildren().add(b);
        Controller c = new Controller();


        Voiture v = new Voiture(710, 360, 100, 200, 1, "images/voiture1.png");
        root.getChildren().add(v);

        c.update(scene, b);
        c.MoveVoiture(scene, v, "LEFT");

        c.Collision(scene, b, v);
    }

}
