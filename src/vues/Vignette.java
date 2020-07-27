package vues;

import java.awt.Graphics;
import javax.swing.JPanel;
import modeles.ModeleImage;

public class Vignette extends JPanel {

  private final ModeleImage modeleImage;

  public Vignette(ModeleImage image) {
    this.modeleImage = image;

  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(modeleImage.getImage(), 0, 0,
        (int) (modeleImage.getImage().getWidth(null) * 0.2),
        (int) (modeleImage.getImage().getHeight(null) * 0.2), null);
  }
}
