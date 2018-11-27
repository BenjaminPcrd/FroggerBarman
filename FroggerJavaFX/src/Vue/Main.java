package Vue;

import Metier.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("Vue.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Barman Frogger");
        Scene scene = new Scene(root, 1280, 720, Color.SKYBLUE);
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        System.out.println(width);
        System.out.println(height);

        Barman b = new Barman(width/2-25, height-50, 50, 50, 2, "images/loic.png");
        root.getChildren().add(b);
        bouger(scene, b);


        //test();

    }

    private boolean up, down, right, left;
    public void bouger(Scene scene, Barman b) {
        AnimationTimer move = new AnimationTimer() {
            @Override
            public void handle(long now) {
                /*if (b.getRect().getY() > 0
                    && b.getRect().getY() < scene.getHeight() - b.getRect().getHeight()
                    && b.getRect().getX() > 0
                    && b.getRect().getX() < scene.getWidth() - b.getRect().getWidth()) {*/
                    if (up) {
                        b.move("UP");
                    }
                    if (down) {
                        b.move("DOWN");
                    }
                    if (right) {
                        b.move("RIGHT");
                    }
                    if (left) {
                        b.move("LEFT");
                    }
                }
            //}
        };

        move.start();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    up = true;
                    break;
                case DOWN:
                    down = true;
                    break;
                case RIGHT:
                    right = true;
                    break;
                case LEFT:
                    left = true;
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    up = false;
                    break;
                case DOWN:
                    down = false;
                    break;
                case RIGHT:
                    right = false;
                    break;
                case LEFT:
                    left = false;
                    break;

            }
        });
    }

    public void test() {
        Boisson b1 = new Boisson("Biere");
        Boisson b2 = new Boisson("Coca");
        Bar barBiere = new Bar(0, 0, 0, 0, b1, 10, null);
        Bar barCoca = new Bar(0, 0, 0, 0, b2, 10, null);
        Barman loic = new Barman(0, 0, 0, 0, 0, "images/loifffc.png");
        try {
            loic.ajouterBoisson(barBiere);
            loic.ajouterBoisson(barBiere);
            loic.ajouterBoisson(barCoca);
            loic.ajouterBoisson(barCoca);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            loic.ajouterBoisson(barCoca);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(barBiere.getNbBoisson());
        System.out.println(loic.getPlateau());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
