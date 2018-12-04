package Vue;

import Controller.Controller;
import Model.Barman;
import Model.Boisson;
import Model.Voiture;
import Model.Client;
import Model.Bar;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Plateau {

    Group root;

    public void enlever(Object o){
        root.getChildren().remove(o);
    }


    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        primaryStage.setTitle("Barman Frogger");
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);

        Controller c = new Controller();

        Barman b = new Barman(scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 1.3, "images/loic.png");
        root.getChildren().add(b);

       /*b.getRect().xProperty().bind(b.getImageView().xProperty());
        b.getRect().yProperty().bind(b.getImageView().yProperty());
        b.getImageView().xProperty().bind(b.getRect().xProperty());
        b.getImageView().yProperty().bind(b.getRect().yProperty());*/


        Voiture v1 = new Voiture(0, 500, 200, 100, 2.2, "images/voiture1.png");
        Voiture v2 = new Voiture(0, 375, 200, 100, 2.7, "images/voiture2.png");
        Voiture v3 = new Voiture(0, 250, 200, 100, 2.8, "images/voiture3.png");
        Voiture v4 = new Voiture(0, 125, 200, 100, 2.4, "images/voiture4.png");


        root.getChildren().add(v1);
        root.getChildren().add(v2);
        root.getChildren().add(v3);
        root.getChildren().add(v4);

        c.MoveVoiture(scene, v1, "RIGHT");
        c.MoveVoiture(scene, v2, "RIGHT");
        c.MoveVoiture(scene, v3, "LEFT");
        c.MoveVoiture(scene, v4, "LEFT");


        c.update(scene, b);

        c.Collision(scene, b, v1);
        c.Collision(scene, b, v2);
        c.Collision(scene, b, v3);
        c.Collision(scene, b, v4);=
        c.MoveVoiture(scene, v, "LEFT");
        c.Collision(scene, b, v);

        Boisson biere = new Boisson("biere");
        Bar bar = new Bar(10, 55, 20, 20, biere ,10,"images/voiturelpok11.png");
        b.ajouterBoisson(bar, bar.getBoisson());
        root.getChildren().add(bar);

        Client client = new Client(10, 20, 20, 20, 1, bar.getBoisson(),"images/voiturelpok11.png");
        root.getChildren().add(client);

        c.collisionClient(this,b,client);


    }

}
