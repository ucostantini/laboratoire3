
package compteur_mvc_exercice;

import java.util.Observable;

/**
 * Classe correspondant au modele de l'architecture MVC 
 * de l'exemple compteur_MVC
 *
 */
public class Model extends Observable {

	/**
	 * compteur 
	 */
	private int compteur;
	
	/**
	 * Constructeur - met le compteur à 0
	 */
	public Model(){
		this.compteur=0;
	}
	
	/**
	 * modifie la valeur de compteur en fonction du parametre
	 * 
	 * @param valeur entiere à ajouter au compteur
	 */
	public void modifier(int incr){
		this.compteur+=incr;
		this.setChanged();		//Informe du changement
		this.notifyObservers(); //Appel de update sur toutes les vues
	}

	/**
	 * @return the compteur
	 */
	public int getCompteur() {
		return this.compteur;
	}
	
}
