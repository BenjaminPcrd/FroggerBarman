package Model;

import java.io.Serializable;

public class Score implements Serializable {
    private int temps;
    private String lvl;

    public Score(int temps, String lvl) {
        this.temps = temps;
        this.lvl = lvl;
    }

    public int getTemps() {
        return temps;
    }

    public String getLvl() {
        return lvl;
    }

    @Override
    public String toString() {
        return temps + " secondes";
    }
}
