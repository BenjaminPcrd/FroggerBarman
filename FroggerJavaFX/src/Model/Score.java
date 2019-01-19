package Model;

import java.io.Serializable;

public class Score implements Serializable, Comparable {
    private int temps;
    private String lvl;
    private String pseudo;

    public Score(int temps, String lvl, String pseudo) {
        this.temps = temps;
        this.lvl = lvl;
        this.pseudo = pseudo;
    }

    public int getTemps() {
        return temps;
    }

    public String getLvl() {
        return lvl;
    }

    public String getPseudo() { return pseudo; }

    @Override
    public String toString() {
        return pseudo + " " + temps + " secondes";
    }

    @Override
    public int compareTo(Object o) {
        Score other = (Score)o;
        return other.getTemps() >= this.getTemps() ? -1 : 0;
    }
}
