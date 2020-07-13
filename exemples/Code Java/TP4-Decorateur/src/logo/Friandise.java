package logo;

public class Friandise extends DecorateurAccessoire {

    public Friandise(Personnage p) {
        this.p=p;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = p.getLogo();
        i.paintOver("img/Candy.png",441,202);
        return i;
    }

    public double combienCaCoute() {
        return 0.8+p.combienCaCoute();
    }
}
