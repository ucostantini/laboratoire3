package logo;

public class Smiley extends DecorateurAccessoire {

    public Smiley(Personnage p) {
        this.p=p;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = p.getLogo();
        i.paintOver("img/Smiley.png",260,210);
        return i;
    }

    public double combienCaCoute() {
        return 0.4+p.combienCaCoute();
    }
}
