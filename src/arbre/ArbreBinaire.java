package arbre;

import graph.IGraph;

public class ArbreBinaire implements IArbre, IGraph{
    /**
     * Tableau des sommets d'un arbre
     */
    private Sommet[] sommets;

    /**
     * Racine de l'arbre binaire
     */
    private Sommet racine;

    /**
     * Constructeur avec argument
     * @param racine valeur de la racine
     * @param profondeur profondeur de l'arbre
     */
    public ArbreBinaire(int racine, int profondeur) {
        int taille = 1;
        for(int i = 1; i <= profondeur; i++){
            taille += Math.pow(2,i);
        }
        this.racine = new Sommet(racine, 0);
    }

    /**
     * Constructeur vide
     */
    public ArbreBinaire() {
        this(0,1);
    }

    /**
     * Constructeur avec seulement la valeur de la racine (arbre à un élément)
     * @param racine valeur de la racine
     */
    public ArbreBinaire(int racine) {
        this(racine, 1);
    }

    @Override
    public Sommet getRacine() {
        return this.racine;
    }

    /**
     * Combien de fils à un noeud
     * @param s sommet
     * @return nombre de fils
     */
    @Override
    public int getNbFils(Sommet s) {
        int index = s.getIndex();
        if(2*index+2 < this.sommets.length){
            return 2;
        }else if(this.sommets.length > 2*index+1 && 2*index+2 >= this.sommets.length){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * Renvoie les fils d'un noeud
     * @param s sommet
     * @return Tableau de fils
     */
    @Override
    public Sommet[] getFils(Sommet s) {
        int index = s.getIndex();
        Sommet[] fils = new Sommet[this.getNbFils(s)];
        for (int i =  0 ; i<fils.length ; i++){
            fils[i] = this.sommets[2*index+1+i];
        }
        return fils;
    }

    @Override
    public Sommet getPere(Sommet s) {
        if((s.getIndex()-1)/2 >0){
            return this.sommets[(s.getIndex()-1)/2];
        }
        else{
            return null;
        }
    }

    /**
     * Pour ajouter un sommet : </br>
     *     Faire une copie des sommets </br>
     *     Ajouter +1 à la taille du tableau </br>
     *     Remettre les anciens sommets </br>
     *     Ajouter le nouveau sommet </br>
     *     Remonter l'arbre jusqu'a trouver sa place </br>
     * @param valeurNouveauSommet valeur du sommet à ajouter
     */
    @Override
    public void ajouterFeuille(int valeurNouveauSommet) {
        Sommet[] copie = this.sommets;
        Sommet nouveauSommet = new Sommet(valeurNouveauSommet, copie.length);
        this.sommets = new Sommet[copie.length+1];
        for(int i = 0; i < copie.length; i++){
            this.sommets[i] = copie[i];
        }
        this.sommets[copie.length] = nouveauSommet;
        this.remonter(nouveauSommet);
    }

    private void remonter(Sommet s) {
        Sommet pere;
        do{
            pere = this.getPere(s);
            if(pere!=null && s.getValeur() < this.getPere(s).getValeur()){
                Sommet s2 = new Sommet(s.getValeur(), s.getIndex());
                this.sommets[s.getIndex()] = pere;
                s2.setIndex(pere.getIndex());
                this.sommets[pere.getIndex()] = s2;
                pere.setIndex(s.getIndex());
                remonter(s);
            }
        }while(pere!=null && s.getValeur() < this.getPere(s).getValeur());
    }

    @Override
    public void supprimerFeuille(Sommet s) {
        this.descendre(s);
        Sommet[] copie = this.sommets.clone();
        this.sommets = new Sommet[this.sommets.length-1];
        for (int i = 0 ; i<this.sommets.length ; i ++ ){
            this.sommets[i] = copie[i];
        }
    }

    //echange un sommet et son fils jusqu'à atteindre une feuille
    private void descendre(Sommet s) {
        int nbFils = 0;
        do{
            System.out.println("s est : "+s+". Il a pour index : " + s.getIndex());
            nbFils = this.getNbFils(s);
            System.out.println("   le sommet s a : " + nbFils+"  fils");

            Sommet[] fils = this.getFils(s);
            if(nbFils!=0){
                Sommet s2 = new Sommet(s.getValeur(),s.getIndex());
                System.out.println("   on échange s avec : " + fils[0]);
                this.sommets[s2.getIndex()] = fils[0];
                s.setIndex(fils[0].getIndex());
                this.sommets[fils[0].getIndex()] = s;
                fils[0].setIndex(s2.getIndex());
            }

        }while(nbFils!=0);

    }

    //supprime la racine de l'arbre
    public int[] removeRacine(){
        int[] res = {this.racine.getIndex(),this.racine.getValeur()};
        this.supprimerFeuille(this.racine);
        return res;
    }

    @Override
    public int getNbNodes() {
        return this.sommets.length;
    }

    /**
     * Pas besoin
     * @return null
     */
    @Override
    public int[][] toAdjacencyMatrix() {
        return null;
    }
}