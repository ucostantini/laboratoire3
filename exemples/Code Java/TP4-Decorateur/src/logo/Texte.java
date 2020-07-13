package logo;

public class Texte extends DecorateurAccessoire {
    private String text;
    private int x;
    private int y;

    public Texte(String text, int x, int y, Personnage p) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.p=p;
    }

    @Override
    public double combienCaCoute() {
        return 1;
    }

    @Override
    public MyImage getLogo() {
        MyImage i = p.getLogo();
        i.textOver(text,x,y);
        return i;
    }
}
