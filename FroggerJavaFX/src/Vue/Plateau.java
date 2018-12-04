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

        Boisson biere = new Boisson("biere");
        Bar bar = new Bar(10, 55, 20, 20, biere ,10,"images/voiturelpok11.png");
        b.ajouterBoisson(bar, bar.getBoisson());
        root.getChildren().add(bar);

        Client client = new Client(10, 20, 20, 20, 1, bar.getBoisson(),"images/voiturelpok11.png");
        root.getChildren().add(client);

        c.collisionClient(this,b,client);


    }

}
