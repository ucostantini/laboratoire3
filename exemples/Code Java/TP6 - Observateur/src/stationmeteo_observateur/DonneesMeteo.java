package stationmeteo_observateur;

import java.util.*;

/**
 * Classe informant les observateurs de differentes mesures meteo
 * implemente Sujet
 *
 */
public class DonneesMeteo implements Sujet {
	
	/**
	 * Liste des observateurs 
	 */
	private ArrayList<Observateur> observateurs;
	
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
	 * 
	 *  se charge de la creation d'une liste vide d'Observateur
	 */
	public DonneesMeteo() {
		this.observateurs = new ArrayList<Observateur> ();
	}
	
	
	/**
	 * Ajoute un observateur a la liste 
	 */
	public void enregistrerObservateur(Observateur o) {
		this.observateurs.add(o);
	}
	
	
	/**
	 * Supprime un observateur a la liste
	 */
	public void supprimerObservateur(Observateur o) {
		int i = this.observateurs.indexOf(o);
		if (i >= 0) {
			this.observateurs.remove(i);
		}
	}
	
	
	/**
	 * Informe tous les observateurs de la liste des
	 * modifications des mesures meteo en appelant leurs methodes actualiser
	 */
	public void notifierObservateurs() {
		for (int i = 0; i < this.observateurs.size(); i++) {
			Observateur observer = this.observateurs.get(i);
			observer.actualiser(temperature, humidite, pression);
		}
	}
	
	
	/**
	 * Modifie les attributs en ajoutant de nouvelles mesures
	 */
	public void setMesures(float temperature, float humidite, float pression) {
		this.temperature = temperature;
		this.humidite = humidite;
		this.pression = pression;
		notifierObservateurs();
	}
	
	
	
	/**
	 * methodes d'observation 
	 *
	 */
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidite() {
		return humidite;
	}
	
	public float getPression() {
		return pression;
	}
}
