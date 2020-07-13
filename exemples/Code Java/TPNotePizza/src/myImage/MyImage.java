package myImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * La classe MyImage permet de creer des images a partir d'un chemin et d'y
 * superposer d'autres images dont le nom est donne
 * 
 * <ul>
 * <li>le constrtucteur construit une image a partir d'un fichier
 * <li>la methode superposer permet d'ajouter un calque au dessus de l'image
 * <li>la methode dessinerDansComposant permet de dessiner le resultat dans un
 * composant
 * </ul>
 * 
 * <p>
 * Les images generees ont une taille fixe determinee par la valeur de
 * l'attribut static TAILLE - par defaut (200,200)
 */
public class MyImage {

	/**
	 * attribut de classe qui limite la taille des images manipulees
	 */
	public static int TAILLE = 200;

	/**
	 * l'objet image stockee dans MyImage
	 */
	BufferedImage image;

	/**
	 * constructeur de la classe MyImage. Permet de charger une image.
	 * 
	 * @param path
	 *            le chemin permettant d'accéder à l'image
	 */
	public MyImage(String path) {

		try {
			BufferedImage chargement = ImageIO.read(new File(path));
			// on redimensionne
			this.image = new BufferedImage(TAILLE, TAILLE, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = this.image.createGraphics();
			g.drawImage(chargement, 0, 0, TAILLE, TAILLE, null);
			g.dispose();
		} catch (Exception e) {
			System.out.println("Erreur image introuvable " + path);
			System.out.println(e);
			e.printStackTrace();

			// en cas d'erreur, construit une image par defaut
			this.image = this.getImageErreur();
		}
	}

	/**
	 * creer une image par defaut a utiliser en cas d'erreur
	 * 
	 * @return image de centre carre rouge avec diagonales noires
	 */
	private BufferedImage getImageErreur() {
		BufferedImage erreur = new BufferedImage(TAILLE, TAILLE, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = erreur.getGraphics();
		g.setColor(Color.red);
		g.fillRect(TAILLE / 4, TAILLE / 4, TAILLE / 2, TAILLE / 2);
		g.setColor(Color.black);
		g.drawLine(0, 0, TAILLE, TAILLE);
		g.drawLine(0, TAILLE, TAILLE, 0);
		g.drawString("erreur !", TAILLE / 4, TAILLE / 2);
		g.dispose();
		return (erreur);
	}

	/**
	 * Affichage de l'image contenu dans MyImage dans un JPanel dont on passe le
	 * graphics
	 * 
	 * @param g
	 *            graphics dans lequel dessiner
	 * @param x
	 *            position en x dans le composant
	 * @param y
	 *            position en y dans le composant
	 */
	public void dessinerDansComposant(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	/**
	 * Superposition d'une autre image sur l'image courante
	 * 
	 * @param imageAAjouter
	 *            designe le chemin ou trouver l'image a ajouter au dessus de
	 *            l'image courante stockee dans this
	 */
	public void superposer(String imageAAjouter) {
		// on charge l'image
		MyImage im = new MyImage(imageAAjouter);

		// on la superpose dans l'image courante
		Graphics g = this.image.getGraphics();
		g.drawImage(im.image, 0, 0, null);
		g.dispose();
	}
}