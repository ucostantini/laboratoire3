import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleCouleur implements KeyListener {
    Scribble sc;

    public ControleCouleur(Scribble s) { this.sc=s; }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        char keyChar = e.getKeyChar();
        System.out.println("COULEUR");
        switch( keyChar ) {
            case 'r':
                this.sc.setC(Color.RED);
                System.out.println("ROUGE");
                break;
            case 'v':
                this.sc.setC(Color.GREEN);
                break;
            case 'b':
                this.sc.setC(Color.BLUE);
                break;
            case 'j':
                this.sc.setC(Color.YELLOW);
                break;
            case 'g':
                this.sc.setC(Color.GRAY);
                break;
            case 'm':
                this.sc.setC(new Color(188, 37,233));
                break;
            case 'z':
                this.sc.setC(Color.PINK);
                break;
            case 'n':
                this.sc.setC(Color.BLACK);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
