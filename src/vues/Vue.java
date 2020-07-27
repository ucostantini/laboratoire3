package vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import modeles.ModeleImage;
import modeles.ModelePerspective;

public class Vue extends JPanel implements Observateur {

  private final ModeleImage image;
  private final ModelePerspective perspective;

  public Vue(ModeleImage image, ModelePerspective perspective) throws IOException {
    this.image = image;
    this.perspective = perspective;
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

  @Override
  public void update() {
    repaint();
  }

  public Dimension getPreferredSize() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return new Dimension(screenSize.width, screenSize.height);
  }


  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int x =
        (int) (this.size().getWidth() - (((BufferedImage) this.image.getImage()).getWidth() * .2))
            / 2;
    int y =
        (int) (this.size().getHeight() - (((BufferedImage) this.image.getImage()).getHeight() * .2))
            / 2;

    AffineTransform at = new AffineTransform();
    at.translate(x, y);
    at.scale(.2, .2);
    if (this.perspective.isInit()) {
      g2.setTransform(at);
      this.perspective.setInit(false);
      this.perspective.setCoordTransform(g2.getTransform());
    } else {
      g2.setTransform(this.perspective.getCoordTransform());
    }

    g2.drawImage(this.image.getImage(), 0, 0, this);

    g2.dispose();
  }


}
