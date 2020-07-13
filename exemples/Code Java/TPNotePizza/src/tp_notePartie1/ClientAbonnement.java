package tp_notePartie1;

public class ClientAbonnement implements StrategyFidelite {
    @Override
    public double getTaux() {
        return 0.7;
    }
}
