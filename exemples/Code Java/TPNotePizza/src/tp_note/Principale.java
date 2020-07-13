/**
 * 
 */
package tp_note;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Classe a modifier selon l'enonce du TP
 *
 */
public class Principale {

	public static void main(String[] args) {
		
		String [] ingredients={"Fromage","Champignons","Chorizo","Oeuf","Oignons","Olives noires","Olives vertes","Roquette"};
		String[] fidelite= {"Nouveau client", "Cliente avec carte", "Client adhérent"};
		JButton[] bIngr;
		
				
		// JPanel situe au nord de l'IG contenant les 2 boutons 
		// permettant le choix de la base des pizzas et le niveau de fidelite du client
		JPanel pnord = new JPanel();
		pnord.setPreferredSize(new Dimension(935,50));
		JComboBox<String> choixFidelite = new JComboBox<String>(fidelite);		
		JButton  addPizzaCreme= new JButton(" Ajouter une pizza fond creme ");
		JButton addPizzaTomate= new JButton(" Ajouter une pizza fond tomate ");
		pnord.add(choixFidelite);
		pnord.add(addPizzaCreme);
		pnord.add(addPizzaTomate);
		
		
		// JPanel au centre de l'IG contenant la vision du choix des pizzas
		// ainsi que les boutons pour ajouter des ingredients
		JPanel pcentral= new JPanel(new BorderLayout());
		//--> Le panneau avec la vision des images des pizzas
		PanneauImages visionComm = new PanneauImages();
		pcentral.add(visionComm,BorderLayout.CENTER);
		//--> Le panneau contenant les boutons des ingredrients
		JPanel pingr= new JPanel(new GridLayout(1,0));
		bIngr= new JButton[8];
		for(int i=0;i<ingredients.length;i++){
			bIngr[i]=new JButton(ingredients[i]);	
			pingr.add(bIngr[i]);
		}					
		pingr.setPreferredSize(new Dimension(935,50));
		pcentral.add(pingr, BorderLayout.SOUTH);
		
		
		// JPanel au sud de l'IG dans lequel se trouve l'affichage
		// du contenu de la commande et son prix 
		JPanel psud= new JPanel(new BorderLayout());
		JLabel commtxt= new JLabel("<html><h3>Aucune commande en cours</h3></html>");
		commtxt.setPreferredSize(new Dimension(935,200));
		psud.add(commtxt,BorderLayout.CENTER);
		psud.add(new JLabel("<html><h3>Pas de commande en cours</h3></html>",JLabel.CENTER), BorderLayout.SOUTH);







		/*************************************
		 * Construction de l'IG dans une JFrame
		 *************************************/
		JFrame frame=new JFrame("Commande de pizzas");
					
		frame.add(pnord,BorderLayout.NORTH); 
		frame.add(pcentral,BorderLayout.CENTER);
		frame.add(psud, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(935,670));
		frame.setVisible(true);
	}

}
