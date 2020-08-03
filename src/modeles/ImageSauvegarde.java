package modeles;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

/**
 * Cette classe sera utilisée pour garder les information de l'image à sauvegarder
 */
public class ImageSauvegarde implements Serializable{
	private String imagePath;
	int zoom1;
	AffineTransform translation1;
	int zoom2;
	AffineTransform translation2;

	/**
	 * Contructeur de l'image qui sera sauvegarder
	 * @param imagePath, path de l'image
	 * @param zoom1, zoom le l'image 1
	 * @param translation1, translation de l'image 1
	 * @param zoom2, zoom de l'image 2
	 * @param translation2, translation de l'image 2
	 */
	public ImageSauvegarde(String imagePath, int zoom1, AffineTransform translation1, int zoom2, AffineTransform translation2 ) {
		this.imagePath = imagePath;
		this.zoom1 = zoom1;
		this.translation1 = translation1;		
		this.zoom2 = zoom2;
		this.translation2 = translation2;		
	}
	
	/**
	 * Cette methode sert a récupérer l'imagePath
	 * @return int, le zoom de l'image
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Cette methode sert a récupérer le zoom de la perspective1
	 * @return int, le zoom de l'image
	 */
	public int getZoom1() {
		return zoom1;
	}

	/**
	 * Cette methode sert a récupérer le translate de la perspective1
	 * @return AffineTransform, la translation de l'image
	 */
	public AffineTransform getTranslation1() {
		return translation1;
	}

	/**
	 * Cette methode sert a récupérer le zoom de la perspective2
	 * @return int, le zoom de l'image
	 */
	public int getZoom2() {
		return zoom2;
	}

	/**
	 * Cette methode sert a récupérer le translate de la perspective2
	 * @return AffineTransform, la translation de l'image
	 */
	public AffineTransform getTranslation2() {
		return translation2;
	}
}
