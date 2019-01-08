package Model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Observable;

public final class Scores {
    private static ObservableList<Integer> mesScores;

    public static void add(int time) {
        mesScores.add(time);
    }

    public static ObservableList<Integer> getMesScores() {
        return mesScores;
    }
}
