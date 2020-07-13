package stationmeteo_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Classe permettant l'affichage des previsions  meteo en fonction 
 * des elements fournis  par la classe DonneesMeteo (Observable) selon le patron Observable/Observer
 *
 */
public class AffichagePrevisions implements Observer, Affichage {
	/**
	 * Pression courante fournie par DonneesMeteo
	 */
	private float pressionCourante = 1012;

	/**
	 * Avant derniere pression courante fournie par DonneesMeteo
	 */
	private float dernierePression;

	/**
	 * Constructeur
	 * 
	 * @param objet de Observable
	 */
	public AffichagePrevisions(Observable observable) {
		observable.addObserver(this); // enregistrement de l'instance courante comme Observer de l'objet Observable
	}

	/**
	 * Mise a jour des attributs en fonction des informations transmises par l'objet Observable 
	 *  qui appelle cette methode a chaque modification par notifyObservers
	 *  @param observable 
	 *  @arg Object element pouvant etre transmis en supplement par l'objet Observable
	 */
	public void update(Observable observable, Object arg) {
		if (observable instanceof DonneesMeteo) {
			DonneesMeteo donneesMeteo = (DonneesMeteo)observable;
			this.dernierePression = this.pressionCourante;
			this.pressionCourante = donneesMeteo.getPression();
			this.afficher();
		}
	}

	/**
	 * Affichage specifique visualisant les previsions meteo 
	 */
	public void afficher() {
		System.out.print("Prevision : ");
		if (this.pressionCourante > this.dernierePression) {
			System.out.println("Amelioration en cours !");
		} else if (this.pressionCourante == this.dernierePression) {
			System.out.println("Le temps se rafraichit");
		} else if (this.pressionCourante < this.dernierePression) {
			System.out.println("Depression bien installee");
		}
	}
}
