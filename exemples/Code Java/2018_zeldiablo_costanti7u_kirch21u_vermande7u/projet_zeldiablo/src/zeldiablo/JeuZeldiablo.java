package zeldiablo;

import moteurJeu.Commande;
import moteurJeu.Jeu;

public class JeuZeldiablo  implements Jeu{
    private Labyrinthe laby;
    private Personnage perso;

    public JeuZeldiablo() {
        //construction d un labyrinthe par defaut
        this.laby= new Labyrinthe();
        this.perso = new Personnage(4,0);
    }

    public void deplacerPersonnage(Direction d){
        boolean deplace = false;
        if(d == null)
            return;
        switch (d){
            case NORD:
                if(perso.getY()>0)
                    deplace = !laby.getTab_cases()[perso.getX()][perso.getY()-1].estMur();
                break;
            case SUD:
                if(perso.getY()<9)
                    deplace = !laby.getTab_cases()[perso.getX()][perso.getY()+1].estMur();
                break;
            case EST:
                if(perso.getX()<9)
                    deplace = !laby.getTab_cases()[perso.getX()+1][perso.getY()].estMur();
                break;
            case OUEST:
                if(perso.getX()>0)
                    deplace = !laby.getTab_cases()[perso.getX()-1][perso.getY()].estMur();
                break;
        }
        if(deplace)
            perso.seDeplacer(d);
    }

    @Override
    public String toString() {
        String res = laby.toString();
        int pos = this.perso.getY()*11;
        pos+=this.perso.getX();
        res = res.substring(0, pos) + "P" + res.substring(pos+1);
        return res;
    }

    @Override
    public void evoluer(Commande commandeUser) {
        if(commandeUser.haut)
            deplacerPersonnage(Direction.NORD);
        else if(commandeUser.bas)
            deplacerPersonnage(Direction.SUD);
        else if(commandeUser.gauche)
            deplacerPersonnage(Direction.OUEST);
        else if(commandeUser.droite)
            deplacerPersonnage(Direction.EST);
    }

    @Override
    public boolean etreFini() {
        return false;
    }

    public Labyrinthe getLaby() {
        return laby;
    }

    public Personnage getPerso() {
        return perso;
    }
}
