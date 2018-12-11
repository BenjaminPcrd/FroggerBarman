package Vue;

import Controller.Controller;
import Model.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.security.krb5.SCDynamicStoreConfig;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {
    private Group root;
    private ApplicationMediator mediator;
    private int level;

    public Plateau(int level) {
        this.level = level;
    }

    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        primaryStage.setTitle("Barman Frogger");
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        mediator = new ApplicationMediator();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);

        Controller c = new Controller();

        Barman b = new Barman(mediator, scene.getWidth()/2-25, scene.getHeight()-50, 50, 50, 0.7, "images/loic.png");
        mediator.ajouterEntite(b);
        root.getChildren().add(b);
        c.update(scene, b);
        /*b.getRect().xProperty().bind(b.getImageView().xProperty());
        b.getRect().yProperty().bind(b.getImageView().yProperty());
        b.getImageView().xProperty().bind(b.getRect().xProperty());
        b.getImageView().yProperty().bind(b.getRect().yProperty());*/

        level1(scene, c, b);

        c.update(scene, b);


        Boisson biere = new Boisson("biere");
        Bar bar = new Bar(mediator, 199, scene.getHeight()-60, 50, 50, biere, 10,"images/bar.png");
        //b.ajouterBoisson(bar, bar.getBoisson());
        mediator.ajouterEntite(bar);
        root.getChildren().add(bar);

        Client client = new Client(mediator, 9, scene.getHeight()-50, 50, 50, 1, bar.getBoisson(),"images/loic.png");
        mediator.ajouterEntite(client);
        root.getChildren().add(client);

        c.collisionClient(this, b);
        c.collisionBar(scene, b);

    }



    public void level1(Scene scene, Controller c, Barman b) {
        int nbVoiture = 4;
        Random r = new Random();
        List<Voiture> voitures = new ArrayList<>();
        for(int i = 1; i <= nbVoiture; i++) {
            voitures.add(new Voiture(mediator, i * 200, 125 * i, 200, 100, 1 + (1.5 - 0.5) * r.nextDouble(), "images/voiture" + i + ".png"));

        }
        for(Voiture v : voitures) {
            mediator.ajouterEntite(v);
            root.getChildren().add(v);
        }

        c.collisionObstacle(scene, b);

        c.moveVoiture(scene, voitures.get(0), "RIGHT");
        c.moveVoiture(scene, voitures.get(1), "RIGHT");
        c.moveVoiture(scene, voitures.get(2), "LEFT");
        c.moveVoiture(scene, voitures.get(3), "LEFT");
    }

    public void enlever(Object o){
        root.getChildren().remove(o);
    }

    public void resetRoot() {
        for(Object o : root.getChildren()) {
            if(o instanceof Voiture) {
                enlever(o);
            }
        }
    }

}

