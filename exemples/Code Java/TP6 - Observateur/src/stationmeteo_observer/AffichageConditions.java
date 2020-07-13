package stationmeteo_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Classe permettant l'affichage des conditions meteo en fonction 
 * des elements fournis  par la classe DonneesMeteo (Observable) selon le patron Observable/Observer
 *
 */
public class AffichageConditions implements Observer, Affichage {
	/**
	 * Temperature fournie par Sujet 
	 */
	private float temperature;
	
	/**
	 *  Humidite fournie par Sujet
	 */
	private float humidite;
	
	
	/**
	 * Constructeur
	 * 
	 * @param objet de Observable
	 */
	public AffichageConditions(Observable observable) {
		observable.addObserver(this); // enregistrement de l'instance courante comme Observer de l'objet Observable
	}
	
	
	/**
	 * Mise a jour des attributs en fonction des informations transmises par l'objet Observable 
	 *  qui appelle cette methode a chaque modification par notifyObservers
	 *  @param observable 
	 *  @arg Object element pouvant etre transmis en supplement par l'objet Observable
	 */
	public void update(Observable obs, Object arg) {
		if (obs instanceof DonneesMeteo) {
			DonneesMeteo donneesMeteo = (DonneesMeteo)obs;
			this.temperature = donneesMeteo.getTemperature(); //utilisation des méthodes d'observation de l'objet Observable
			this.humidite = donneesMeteo.getHumidite();
			this.afficher();
		}
	}
	
	
	/**
	 * Affichage specifique visualisant les conditions meteo courantes
	 */
	public void afficher() {
		System.out.println("\nConditions actuelles: " + this.temperature 
			+ " degres C et " + this.humidite + "% d'humidite");
	}
}
