
package tp_note;

import java.awt.Graphics;

import javax.swing.JPanel;
import myImage.MyImage;

/**
 * A modifier
 *
 */
public class PanneauImages extends JPanel {

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		MyImage mim= new MyImage("images/fond_creme.png");
		mim.superposer("images/ing_fromage.png");  //enlever le commentaire pour superposer l'ingredient fromage sur la pizza
		
		for(int i=0 ; i<4 ; i++){                    //Affiche 4 fois la meme pizza de base dans le JPanel courant
			mim.dessinerDansComposant(g, (i+1)*25+ i*200,25); 
			g.drawRect((i+1)*25+ i*200,25, 200,200); // Affichage d'un rectangle entourant l'image
		}
	}
}
