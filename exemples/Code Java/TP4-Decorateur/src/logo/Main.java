package logo;



public class Main
{
   
    public static void main(String args[])
    {

        Personnage p = new CrazyFrog();
        p = new Smiley(new Lunettes(new Texte("OUAIIIIS",300,300,p)));

        p.getLogo().display();  // Permet l'affichage dans une fenetre de l'image associee
        
        
    }
        
}
