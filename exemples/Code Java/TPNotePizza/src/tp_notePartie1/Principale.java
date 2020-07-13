/**
 * 
 */
package tp_notePartie1;

import tp_note.PanneauImages;

import javax.swing.*;
import java.awt.*;


/**
 * Classe a modifier selon l'enonce du TP
 *
 */
public class Principale {

	public static void main(String[] args) {
		
		String [] ingredients={"Fromage","Champignons","Chorizo","Oeuf","Oignons","Olives noires","Olives vertes","Roquette"};
		String[] fidelite= {"Nouveau client", "Cliente avec carte", "Client adherent"};
		JButton[] bIngr;


		ModeleCommande m = new ModeleCommande();
		ControlDebutCommande cdb = new ControlDebutCommande(m);
		ControlPizzaCour cpc = new ControlPizzaCour(m);
		ControleurFidelite cf = new ControleurFidelite(m);

		VueCommIm vci = new VueCommIm(m);
		VueCommText vct = new VueCommText();
		VueCommPrix vcp = new VueCommPrix();

		vci.addMouseListener(cpc);
		m.addObserver(vci);
		m.addObserver(vcp);
		m.addObserver(vct);
		
				
		/***********************************************************/

		JPanel pnord = new JPanel();
		pnord.setPreferredSize(new Dimension(935,50));
		JComboBox<String> choixFidelite = new JComboBox<String>(fidelite);		
		JButton  addPizzaCreme= new JButton(" Ajouter une pizza fond creme ");
		JButton addPizzaTomate= new JButton(" Ajouter une pizza fond tomate ");

		addPizzaCreme.addActionListener(cdb);
		addPizzaTomate.addActionListener(cdb);
		choixFidelite.addActionListener(cf);

		pnord.add(choixFidelite);
		pnord.add(addPizzaCreme);
		pnord.add(addPizzaTomate);

		/*********************************************************/

		JPanel pcentral= new JPanel(new BorderLayout());
		pcentral.add(vci,BorderLayout.CENTER);


		JPanel pingr= new JPanel(new GridLayout(1,0));
		bIngr= new JButton[8];
		for(int i=0;i<ingredients.length;i++){
			bIngr[i]=new JButton(ingredients[i]);	
			pingr.add(bIngr[i]);
		}					
		pingr.setPreferredSize(new Dimension(935,50));
		pcentral.add(pingr, BorderLayout.SOUTH);
		
		/*****************************************************/

		JPanel psud= new JPanel(new BorderLayout());
		vct.setText("<html><h3>Aucune commande en cours</h3></html>");
		vct.setPreferredSize(new Dimension(935,200));
		psud.add(vct,BorderLayout.CENTER);
		vcp.setText("<html><h3>Pas de commande en cours</h3></html>");
		psud.add(vcp, BorderLayout.SOUTH);



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
