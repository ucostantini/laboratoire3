
package compteur_mvc_exercice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur du MVC - 
 * Apres un clic sur un des boutons 
 * le controleur demande au modele de
 * se modifier
 */
public class Controler implements ActionListener {
	/**
	 * Le modèle est attribut du controleur
	 */
	private Model model;
	
	/**
	 * Constructeur
	 * 
	 * @param m l'objet Model 
	 */
	public Controler(Model m) {
		this.model=m;
	}

	/**
	 * Methode issue de l'interface Actionlistener
	 * lancee quand un evenement a lieu sur un des 
	 * composants sous ecoute - ici les 2 boutons
	 * 
	 * @param e ActionEvent obtenu après clic 
	 * sur un des deux boutons
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Plus")) 
			model.modifier(1);
	  if(e.getActionCommand().equals("Moins"))	
			model.modifier(-1);	
	}
}
