package arbre; 

import java.util.ArrayList;
import java.util.List;

public class Arbre {
    private String id;
    private List<Arbre> sousArbres;

    public Arbre(String id) {
	this.id = id;
	this.sousArbres = new ArrayList<Arbre>();
    }

    public String getId() {
	return id;
    }

    public List<Arbre> getSousArbres() {
	return sousArbres;
    }

    public void add(Arbre a) {
	sousArbres.add(a);
    }

    public String toString() {
	return id+" "+ sousArbres;
    }

    public static void main(String[] args) {
	Arbre a = new Arbre("1");
	Arbre a1 = new Arbre("1.1");
	Arbre a2 = new Arbre("1.2");
	a.add(a1);
	a.add(a2);
	a.add(new Arbre("1.3"));
	
	a1.add(new Arbre("1.1.1"));
	a1.add(new Arbre("1.1.2"));	
	
	Arbre a3 = new Arbre("1.2.1");
	a2.add(a3);
	a3.add(new Arbre("1.2.1.1"));

	System.out.println(a);
	
	/*
	 * a0 represente l'arbre ci-dessous
	 * 
	 * 			1
	 *                    /	| \
	 *                   /  |  \
	 *                  /   |   \
	 *                1.1  1.2  1.3 
	 *                / |    \     
	 *               /  |     \      
	 *              /   |      \     
	 *           1.1.1  1.1.2  1.2.1
	 *                           |
	 *                           |
	 *                         1.2.1.1
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

    }

}
