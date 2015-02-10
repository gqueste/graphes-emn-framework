package arbre;

public class Sommet {

    /**
     * Valeur du sommet
     */
    private int valeur;

    /**
     * Index du sommet dans l'arbre binaire
     */
    private int index;

    public Sommet() {
    }

    public Sommet(int valeur, int index) {
        this.valeur = valeur;
        this.index = index;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Sommet{" +
                "valeur=" + valeur +
                ", index=" + index +
                '}';
    }
}