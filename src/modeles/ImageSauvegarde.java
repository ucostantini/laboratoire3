package modeles;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

public class ImageSauvegarde implements Serializable{
	int zoom1;
	AffineTransform translation1;
	int zoom2;
	AffineTransform translation2;
	
	public ImageSauvegarde( int zoom1, AffineTransform translation1, int zoom2, AffineTransform translation2 ) {
		this.zoom1 = zoom1;
		this.translation1 = translation1;		
		this.zoom2 = zoom2;
		this.translation2 = translation2;		
	}
	
	
	public int getZoom1() {
		return zoom1;
	}
	
	public AffineTransform getTranslation1() {
		return translation1;
	}
	
	public int getZoom2() {
		return zoom2;
	}
	
	public AffineTransform getTranslation2() {
		return translation2;
	}
}
