package Model;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public final class Scores implements Serializable {
    private static List<Score> mesScores = new ArrayList<>();

    public static void add(Score score) {
        mesScores.add(score);
    }

    public static List<Score> getMesScores(String lvl) {
        List<Score> result = new ArrayList<>();
        for(Score s : mesScores) {
            if(s.getLvl().equals(lvl)) {
                result.add(s);
            }
        }
        return result;
    }

    public static void saveMesScores(String path) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path))) {
            output.writeObject(mesScores);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadMesScores(String path) {

        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(path))) {
            mesScores = (List<Score>) input.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
