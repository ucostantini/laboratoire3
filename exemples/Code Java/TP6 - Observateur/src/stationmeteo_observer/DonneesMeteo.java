package stationmeteo_observer;
	
import java.util.Observable;
import java.util.Observer;

/**
 * Classe DonneesMeteo informant les observateurs de differentes mesures meteo
 * extends Observable
 *
 */
public class DonneesMeteo extends Observable {
	/**
	 * Temperature 
	 */
	private float temperature;
	
	/**
	 * Humidite
	 */
	private float humidite;
	
	/**
	 * Pression
	 */
	private float pression;
	
	
	/**
	 * Constructeur
	 */	
	public DonneesMeteo() { }
	
	/**
	 * Modifie les attributs en ajoutant de nouvelles mesures
	 * et prévient les objets Observer des changements
	 */
	public void setMesures(float temperature, float humidite, float pression) {
		this.temperature = temperature;
		this.humidite = humidite;
		this.pression = pression;
		setChanged();	  //positionne changed a true
		notifyObservers();//appel les methodes update des objets Observer
	}
	
	
	
	/**
	 * 
	 * @return float temperature
	 */
	public float getTemperature() {
		return temperature;
	}
	
	/**
	 * 
	 * @return float hulidite
	 */
	public float getHumidite() {
		return humidite;
	}
	
	/**
	 * 
	 * @return float pression
	 */
	public float getPression() {
		return pression;
	}
}
