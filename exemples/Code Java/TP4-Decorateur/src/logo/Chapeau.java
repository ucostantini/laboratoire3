package logo;

public class Chapeau extends DecorateurAccessoire {

    public Chapeau(Personnage p) {
        this.p=p;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = p.getLogo();
        i.paintOver("img/Chapeau.png",280,42);
        return i;
    }

    public double combienCaCoute() {
        return 0.5+p.combienCaCoute();
    }
}
