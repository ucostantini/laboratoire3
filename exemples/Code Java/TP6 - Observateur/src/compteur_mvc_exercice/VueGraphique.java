
package compteur_mvc_exercice;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;


/**
 * Une Vue du MVC - correspond au JLabel contenant la valeur du compteur
 *
 */
public class VueGraphique extends JLabel implements Observer  {

	/**
	 * Constructeur - positionne la valeur 0 au centre du JLabel
	 */
	public VueGraphique() {
		super("0",CENTER);
	}

	/**
	 * Actualisation du JLabel avec la nouvelle valeur du compteur obtenue grace au parametre o
	 * Methode lancee a chaque modification du modele
	 * 
	 * @param o Observable correspondant au modele
	 * @param arg Objet objet pouvant être envoyé dans le paramètre de l'appel à notifyObservers
	 */
	public void update(Observable o, Object arg) {
		this.setText(""+ ((Model)o).getCompteur());
		
	}

}
