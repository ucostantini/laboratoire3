package logo;

public abstract class DecorateurAccessoire extends Personnage {
    protected Personnage p;

    public abstract double combienCaCoute();

    @Override
    public abstract MyImage getLogo();
}
