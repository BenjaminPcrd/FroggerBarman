package sample;

import Metier.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.util.PrimitiveIterator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Barman Frogger");
        primaryStage.setScene(new Scene(root, 1280, 720, Color.BLACK));
        //primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Dimension size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        System.out.println(width);
        System.out.println(height);

        Barman b = new Barman(width/2-25, height-50, 50, 50, 2);
        root.getChildren().add(b);
        bouger(primaryStage, b, width, height);
        /*try {
            Image image = new Image(new FileInputStream("../../images/loic.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        Image image = new Image(new FileInputStream("images/loic.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(b.getX());
        imageView.setY(b.getY());
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        root.getChildren().add(imageView);
        //test();

    }

    public void bouger(Stage primaryStage, Barman b, double width, double height) {
        AnimationTimer moveUp = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (b.getRect().getY() > 0) {
                    b.getRect().setY(b.getRect().getY() - b.getSpeed());
                }
            }
        };
        AnimationTimer moveDown = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (b.getRect().getY() < height - b.getRect().getHeight()) {
                    b.getRect().setY(b.getRect().getY() + b.getSpeed());
                }

            }
        };
        AnimationTimer moveLeft = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (b.getRect().getX() > 0) {
                    b.getRect().setX(b.getRect().getX() - b.getSpeed());
                }
            }
        };
        AnimationTimer moveRight = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (b.getRect().getX() < width - b.getRect().getWidth()) {
                    b.getRect().setX(b.getRect().getX() + b.getSpeed());
                }
            }
        };

        EventHandler<KeyEvent> eventHandlerStart = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        moveUp.start();
                        break;
                    case DOWN:
                        moveDown.start();
                        break;
                    case LEFT:
                        moveLeft.start();
                        break;
                    case RIGHT:
                        moveRight.start();
                        break;
                }
            }
        };
        EventHandler<KeyEvent> eventHandlerStop = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        moveUp.stop();
                        break;
                    case DOWN:
                        moveDown.stop();
                        break;
                    case LEFT:
                        moveLeft.stop();
                        break;
                    case RIGHT:
                        moveRight.stop();
                        break;
                }
            }
        };
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, eventHandlerStart);
        primaryStage.addEventFilter(KeyEvent.KEY_RELEASED, eventHandlerStop);
    }

    public void test() {
        Boisson b1 = new Boisson("Biere");
        Boisson b2 = new Boisson("Coca");
        Bar barBiere = new Bar(0, 0, 0, 0, b1, 10);
        Bar barCoca = new Bar(0, 0, 0, 0, b2, 10);
        Barman loic = new Barman(0, 0, 0, 0, 0);
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
