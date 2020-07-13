package tp_notePartie2;

public class ClientAbonnement implements StrategyFidelite {
    @Override
    public double getTaux() {
        return 0.7;
    }
}
