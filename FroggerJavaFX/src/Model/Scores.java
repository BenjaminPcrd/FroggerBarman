package Model;

import javafx.collections.ObservableList;

import java.util.*;

public final class Scores {
    private static List<String> mesScores = new ArrayList<>();

    public static void add(int time) {
        mesScores.add(String.valueOf(time));
    }

    public static List<String> getMesScores() {
        Collections.sort(mesScores);
        return mesScores;
    }
}
