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
		Graphics2D g2d = (Graphics2D) g;
		int largeurImage = ((BufferedImage) this.image.getImage()).getWidth();
		int hauteurImage = ((BufferedImage) this.image.getImage()).getHeight();
		int positionDebutX = (int) ((this.getWidth() - largeurImage) / 2);
		int positionDebutY = (int) ((this.getHeight() - hauteurImage) / 2);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(positionDebutX, positionDebutY);

		// Si l application est initialisee, alors on applique a l'image les cordoonnees "de base"
		// Sinon on recupere les coordonnes transformees par une commande (zoom, translation...)
		if (this.perspective.isInitialisationApplication()) {
			g2d.setTransform(affineTransform);
			this.perspective.setInitialisationApplication(false);
			this.perspective.setTransformationCoordonnees(g2d.getTransform());
		} else {
			g2d.setTransform(this.perspective.getTransformationCoordonnees());
		}
		g2d.drawImage(this.image.getImage(), 0, 0, this);
		g2d.dispose();
	}

}
