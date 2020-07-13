package stationmeteo_observateur;


/**
 * Classe permettant l'affichage de statistiques meteo en fonction 
 * des elements fournis  par la classe DonneesMeteo selon le patron Observateur
 *
 */
public class AffichageStats implements Observateur, Affichage {
	
	/**
	 * Temperature maximale
	 */
	private float tempMax = 0.0f;
	
	/**
	 * Temperature minimale
	 */
	private float tempMin = 200;
	
	/**
	 * Somme des temperature 
	 */
	private float somTemp= 0.0f;
	
	
	/**
	 * Nombre des valeurs lues
	 */
	private int nbLectures;
	

	/**
	 * Constructeur
	 * 
	 * @param donneesMeteo de DonneesMeteo
	 */
	public AffichageStats(DonneesMeteo donneesMeteo) {
		
		donneesMeteo.enregistrerObservateur(this); // enregistrement de l'instance courante comme Observateur de DonneesMeteo
	}

	public void actualiser(float temp, float humidite, float pression) {
		this.somTemp += temp;
		this.nbLectures++;

		if (temp > this.tempMax) {
			this.tempMax = temp;
		}
 
		if (temp < this.tempMin) {
			this.tempMin = temp;
		}

		this.afficher();
	}

	/**
	 * Affichage specifique visualisant les statistiques meteo sur plusieurs lectures
	 */
	public void afficher() {
		System.out.println("Nbre de lectures : "+this.nbLectures+" Temperature Moy/Max/Min = " + (this.somTemp / this.nbLectures)
			+ "/" + this.tempMax + "/" + this.tempMin);
	}
}
