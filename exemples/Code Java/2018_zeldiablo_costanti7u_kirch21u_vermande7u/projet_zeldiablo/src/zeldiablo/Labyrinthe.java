package zeldiablo;

public class Labyrinthe {

    private Case[][] tab_cases;

    public Labyrinthe(){
        tab_cases = new Case[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++)
                tab_cases[j][i] = new Case(i == 0 || i == 9 || j == 0 || j == 9);
        }
        tab_cases[4][0] = new Case(false);
    }

    public Case[][] getTab_cases() {
        return tab_cases;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                res += tab_cases[j][i].estMur() ? "X" : ".";
            }
            res += "\n";
        }
        return res;
    }

}
