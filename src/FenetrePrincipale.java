import controleurs.Controleur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modeles.ModeleImage;
import modeles.ModelePerspective;
import vues.Vignette;
import vues.Vue;

/**
 * Classe qui sert a instancier la fenetre principale
 */
public class FenetrePrincipale extends JFrame {

	private static final String TITRE_FENETRE = "Laboratoire 3";
	private static final Dimension DIMENSION = new Dimension(1200, 800);

	private FenetrePrincipale() throws Exception {

		ModeleImage image = new ModeleImage();
		ModelePerspective perspective1 = new ModelePerspective();
		ModelePerspective perspective2 = new ModelePerspective();

		//Les perspective et la vignette
		Vue vue1 = new Vue(image, perspective1);
		Vue vue2 = new Vue(image, perspective2);
		Vignette vignette = new Vignette(image);

		image.addObserver(vue1);
		image.addObserver(vue2);
		image.addObserver(vignette);

		perspective1.addObserver(vue1);
		perspective2.addObserver(vue2);

		Controleur controleur = new Controleur(vue1, vue2, image, perspective1, perspective2);

		JPanel panel = new JPanel(new GridLayout(1, 3, 10, 20));
		panel.add(vue1);
		panel.add(vue2);
		panel.add(vignette);

		this.add(controleur, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);

		this.pack();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(TITRE_FENETRE);
		this.setSize(DIMENSION);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

	}
	//Main
	public static void main(String[] args) throws Exception {
		FenetrePrincipale f = new FenetrePrincipale();

	}
}
