package sample;

import Metier.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.KeyListener;
import java.util.PrimitiveIterator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Barman Frogger");
        primaryStage.setScene(new Scene(root, 1280, 720, Color.BLACK));
        primaryStage.setResizable(false);
        primaryStage.show();

        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        Barman b = new Barman(width/2-25, height-50, 50, 50, 2);
        root.getChildren().add(b);
        bouger(primaryStage, b);
        //test();

    }

    public void bouger(Stage primaryStage, Barman b) {
        AnimationTimer moveUp = new AnimationTimer() {
            @Override
            public void handle(long now) {
                b.getRect().setY(b.getRect().getY() - b.getSpeed());
            }
        };
        AnimationTimer moveDown = new AnimationTimer() {
            @Override
            public void handle(long now) {
                b.getRect().setY(b.getRect().getY() + b.getSpeed());

            }
        };
        AnimationTimer moveLeft = new AnimationTimer() {
            @Override
            public void handle(long now) {
                b.getRect().setX(b.getRect().getX() - b.getSpeed());
            }
        };
        AnimationTimer moveRight = new AnimationTimer() {
            @Override
            public void handle(long now) {
                b.getRect().setX(b.getRect().getX() + b.getSpeed());
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
