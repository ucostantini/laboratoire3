package modeles;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import vues.Observateur;

/**
 * Cette classe est utilisé pour instancier la vignette
 */
public class ModeleImage implements Subject {

	private Image image;
	private final List<Observateur> abonnes;

	public ModeleImage() {
		this.abonnes = new ArrayList<>();

	}
	/**
	 * Cette methode sert a retourner l'image
	 * @return Image
	 */
	public Image getImage() {
		return this.image;
	}
	/**
	 * Sert a affecter l'image
	 * @param image, image qu'on veux
	 */
	public void setImage(Image image) {
		this.image = image;
		this.notifyObservers();
	}
	/**
	 * Utiliser pour "avertir" d'un changement
	 */
	@Override
	public void notifyObservers() {
		for (Observateur abonne : this.abonnes) {
			abonne.update();
		}
	}
	/**
	 * Ajoute des observateur
	 * @param observer
	 */
	@Override
	public void addObserver(Observateur observer) {
		this.abonnes.add(observer);
	}
}
