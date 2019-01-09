package Model;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public final class Scores implements Serializable {
    private static List<String> mesScores = new ArrayList<>();

    public static void add(int time) {
        mesScores.add(String.valueOf(time));
    }

    public static List<String> getMesScores() {
        Collections.sort(mesScores);
        return mesScores;
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
            mesScores = (List<String>) input.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
