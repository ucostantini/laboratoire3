package zeldiablo;

public enum Direction {
    /**
     * Enumeration des directions possible
     */
    NORD,
    SUD,
    EST,
    OUEST;

    public static Direction castChar(char c){
        switch (c){
            case 'N':
                return NORD;
            case 'S':
                return SUD;
            case 'E':
                return EST;
            case 'O':
                return OUEST;
            default:
                return null;
        }
    }
}
