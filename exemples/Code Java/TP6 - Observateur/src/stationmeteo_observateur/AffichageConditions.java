package stationmeteo_observateur;

/**
 * Classe permettant l'affichage des conditions meteo courantes
 * fournies par la classe StationMeteo selon le patron Observateur
 *
 */
public class AffichageConditions implements Observateur, Affichage {
	
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
	 * @param objet de Sujet 
	 */
	public AffichageConditions(Sujet donneesMeteo) {
		
		donneesMeteo.enregistrerObservateur(this); // enregistrement de l'instance courante comme Observateur de Sujet
	}
	
	/**
	 * Actualise les attributs 
	 * methode appelee par le sujet à chaque modification
	 * 
	 * @param temperature 
	 * @param humidite
	 * @pression pression 
	 */
	public void actualiser(float temperature, float humidite, float pression) {
		this.temperature = temperature;
		this.humidite = humidite;
		this.afficher();
	}
	
	/**
	 * Affichage specifique visualisant les conditions meteo courantes
	 */
	public void afficher() {
		System.out.println("\nConditions actuelles: " + this.temperature 
			+ " degres C et " + this.humidite + "% d'humidite");
	}
}
