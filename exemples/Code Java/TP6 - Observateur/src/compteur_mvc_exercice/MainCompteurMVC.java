/**
 * 
 */
package compteur_mvc_exercice;

import java.awt.BorderLayout;

import javax.swing.*;

/**
 * Programme principale - construit l'IG
 *
 */
public class MainCompteurMVC {

	/**
	 * Constructeur - construit l'IG 
	 * et les éléments de l'architecture MVC
	 */
	public MainCompteurMVC() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Model 			model = new Model();			//Creation du modele
		
		Controler 		control= new Controler(model);  //Creation du controleur - le modele en parametre
		
	    VueGraphique 	vueg = new VueGraphique();  	//Creation d'une vue graphique
		model.addObserver(vueg);						// Ajout de la vue comme observateur du modele - aurait pu etre fait dans le constructeur de la vue

		VueConsole vc = new VueConsole();
		model.addObserver(vc);
		
		
		JFrame ig=new JFrame("Compteur MVC");

		JButton bplus 	= new JButton("Plus");
		bplus.addActionListener(control);
		
		JButton bmoins    = new JButton("Moins");
		bmoins.addActionListener(control); 
		
		ig.add(bplus,BorderLayout.NORTH);
		ig.add(vueg,BorderLayout.CENTER);
		ig.add(bmoins,BorderLayout.SOUTH);
		ig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ig.pack();
		ig.setVisible(true);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			new MainCompteurMVC();
	}

}
