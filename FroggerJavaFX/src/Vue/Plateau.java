package Vue;

import Controller.Controller;
import Model.*;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class Plateau {
    private Group root;
    private Scene scene;
    private Controller controller;
    private ApplicationMediator mediator;
    private int compteur;
    private Text score;
    private Text nbVie;

    private ObjectProperty lvl = new SimpleObjectProperty();
    //public final Object getLvl() { return lvl.get(); }
    //public final void setLvl(String value) { lvl.set(value); }
    public ObjectProperty lvlProperty() { return lvl; }

    public Plateau() {
        this.root = new Group();
        this.scene = new Scene(root, 1280, 720);
        this.controller = new Controller(this);
        this.mediator = new ApplicationMediator();
        this.compteur = 0;

    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Barman Frogger");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        ImageView background = new ImageView(new Image(new FileInputStream("images/fond.png")));
        root.getChildren().add(background);
        controller.setEvenementClavier(scene);
        Button menu = new Button("Retour au menu");
        root.getChildren().add(menu);
        menu.setOnAction(actionEvent -> {
            Main main = new Main();
            try {
                main.start(primaryStage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });


        final KeyFrame KeyFrame0s = new KeyFrame(Duration.ZERO);
        final KeyFrame KeyFrame1s = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                compteur++;
                score.setText(String.valueOf(compteur) );
            }
        });
        final Timeline timeline = new Timeline(KeyFrame0s, KeyFrame1s);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();

        this.score = new Text(scene.getWidth() - 50, 50, String.valueOf(compteur));
        root.getChildren().add(score);

        Niveau lvl;
        switch((String)lvlProperty().get()) {
            case "Facile":
                System.out.println("Facile");
                lvl = new Niveau(
                        1,
                        4,
                        0.5,
                        1,
                        false,
                        1,
                        5,
                        2,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            case "Normal":
                System.out.println("Normal");
                lvl = new Niveau(
                        1,
                        9,
                        1,
                        1,
                        false,
                        2,
                        10,
                        3,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            case "Hardcore":
                System.out.println("Hardcore");
                lvl = new Niveau(
                        1,
                        6,
                        1.5,
                        1.6,
                        true,
                        3,
                        16,
                        5,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            case "DrunkMode":
                System.out.println("DrunkMode");
                lvl = new Niveau(
                        1,
                        10,
                        1.4,
                        1.4,
                        true,
                        4,
                        8,
                        6,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            default:
                System.out.println("Facile");
                lvl = new Niveau(
                        1,
                        4,
                        0.5,
                        1,
                        false,
                        2,
                        5,
                        3,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
        }




        AnimationTimer testWin = new AnimationTimer() {
            long prevTime = 0;
            @Override
            public void handle(long l) {
                if (l - prevTime >= 1_000_000_000) {
                    //System.out.println("gagné ? " + lvl.isWin());
                    if (lvl.isWin()) {
                        this.stop();
                        timeline.stop();
                        String win = "Gagné !! score : " + compteur + " secondes";
                        Scores.add(new Score(compteur, (String)lvlProperty().get()));
                        Scores.saveMesScores("saveScores.bin");

                        Text text = new Text(scene.getWidth()/2 - 300, scene.getWidth()/2 - 300, win);
                        text.setFont(Font.font(48));
                        root.getChildren().add(text);
                        AnimationTimer cpt = new AnimationTimer() {
                            long prevTime = 0;
                            int sec = 0;
                            @Override
                            public void handle(long l) {
                                if (l/1000 - prevTime/1000 >= 1_000_000) {
                                    sec++;
                                    if(sec >= 5) {
                                        this.stop();
                                        System.out.println("esfs");
                                        menu.fire();
                                    }
                                    prevTime = l ;
                                }
                            }
                        };
                        cpt.start();
                    }
                    prevTime = l ;
                }
            }
        };
        testWin.start();



        nbVie = new Text(scene.getWidth() - 80, 50,  String.valueOf(lvl.getBarman().getNbVie()));
        root.getChildren().add(nbVie);
        AnimationTimer testDefeat = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lvl.isLose()) {
                    this.stop();
                    timeline.stop();
                    String lose = "Game Over";
                    Text text = new Text(scene.getWidth()/2 - 150, scene.getHeight()/2 - 50, lose);
                    text.setFont(Font.font(48));
                    root.getChildren().add(text);
                    AnimationTimer cpt = new AnimationTimer() {
                        long prevTime = 0;
                        int sec = 0;
                        @Override
                        public void handle(long l) {
                            if (l/1000 - prevTime/1000 >= 1_000_000) {
                                sec++;
                                if(sec >= 5) {
                                    this.stop();
                                    menu.fire();
                                }
                                prevTime = l ;
                            }
                        }
                    };
                    cpt.start();
                }
                nbVie.setText(String.valueOf(lvl.getBarman().getNbVie()));
            }
        };
        testDefeat.start();

    }

    public ApplicationMediator getMediator() {
        return mediator;
    }

    public Scene getScene() {
        return scene;
    }
}

