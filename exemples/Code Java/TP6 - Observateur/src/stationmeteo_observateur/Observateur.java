package stationmeteo_observateur;

/**
 * Interface pour les differents observateurs de Sujet
 *
 */
public interface Observateur {
	public void actualiser(float temperature, float humidite, float pression);
}
