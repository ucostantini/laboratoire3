package vues;

import java.awt.Graphics;
import javax.swing.JPanel;
import modeles.ModeleImage;
/**
 * Classe qui sert a afficher la vignette
 */
public class Vignette extends JPanel {

  private final ModeleImage modeleImage;
/**
	 * Contructeur de l'image a afficher
	 * @param image, image a afficher
	 */
  public Vignette(ModeleImage image) {
    this.modeleImage = image;

  }

	/**
	 * Cette methode qui s'occupe de l'affichage
	 */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(modeleImage.getImage(), 0, 0,
        (int) (modeleImage.getImage().getWidth(null) * 0.2),
        (int) (modeleImage.getImage().getHeight(null) * 0.2), null);
  }
}
