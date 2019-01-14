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

import java.io.FileInputStream;

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
                        6,
                        0.7,
                        1.5,
                        false,
                        2,
                        8,
                        3,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            case "Hardcore":
                System.out.println("Hardcore");
                lvl = new Niveau(
                        1,
                        8,
                        1,
                        2,
                        true,
                        3,
                        15,
                        5,
                        mediator, root, controller, scene);
                lvl.generer();
                break;
            case "DrunkMode":
                System.out.println("DrunkMode");
                lvl = new Niveau(
                        1,
                        8,
                        1.7,
                        2.7,
                        true,
                        4,
                        20,
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
                        String win = "Gagné avec " + compteur + " secondes";
                        Scores.add(new Score(compteur, (String)lvlProperty().get()));
                        Scores.saveMesScores("saveScores.bin");

                        HBox hBox = new HBox();
                        hBox.setTranslateX(scene.getWidth()/2 - 100);
                        hBox.setTranslateY(scene.getHeight()/2 - 100);
                        root.getChildren().add(hBox);

                        for (int i = 0; i < win.toCharArray().length; i++) {
                            char letter = win.charAt(i);

                            Text text = new Text(String.valueOf(letter));
                            text.setFont(Font.font(48));
                            text.setOpacity(0);

                            hBox.getChildren().add(text);

                            FadeTransition ft = new FadeTransition(Duration.seconds(1), text);
                            ft.setToValue(1);
                            ft.setDelay(Duration.seconds(i * 0.15));
                            ft.play();
                        }
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
                    System.out.println("perdu");
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

