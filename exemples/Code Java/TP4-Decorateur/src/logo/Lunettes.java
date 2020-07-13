package logo;

public class Lunettes extends DecorateurAccessoire {

    public Lunettes(Personnage p) {
        this.p=p;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = p.getLogo();
        i.paintOver("img/Sunglasses.png",255,76);
        return i;
    }

    public double combienCaCoute() {
        return 0.75+p.combienCaCoute();
    }
}
