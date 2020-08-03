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
/**
 * Classe qui sert a afficher les vues
 */
public class Vue extends JPanel implements Observateur {

	private final ModeleImage image;
	private final ModelePerspective perspective;
/**
	 * Contructeur des vues à afficher
	 * @param image, image a afficher
	 * @param perspective, les perspective
	 */
	public Vue(ModeleImage image, ModelePerspective perspective) {
		this.image = image;
		this.perspective = perspective;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
/**
	 * Cette methode s'occupe de mettre à jour l'affichage
	 */
	@Override
	public void update() {
		repaint();
	}
	/**
	 * Cette methode s'occupe d'ajuster les dimensions
	 */
	public Dimension getPreferredSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension(screenSize.width, screenSize.height);
	}
	/**
	 * Cette methode s'occupe d'afficher les deux perspective
	 */
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
