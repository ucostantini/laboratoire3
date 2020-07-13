import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Scribble extends JPanel implements MouseListener, MouseMotionListener {
    Color c;
    int lastX, lastY;
    int firstX,firstY;
    ControleCouleur cc;
    int i;

    public Scribble() {
        this.c=Color.BLACK;
        addMouseListener(this);
        addMouseMotionListener(this);
        this.cc = new ControleCouleur(this);
        this.addKeyListener(cc);

    }
    public void paintComponent(Graphics g) {
        if(i==3) {
            super.paintComponent(g);
            removeMouseListener(this);
            removeMouseMotionListener(this);
            removeKeyListener(cc);
        } else if(i==2) {
            super.paintComponent(g);
            this.requestFocusInWindow();
            this.requestFocus();
        }


        g.setColor(c);
        System.out.println("FIRST :");
        System.out.println("X : "+firstX+" Y : "+firstY);

        System.out.println("LAST :");
        System.out.println("X : "+lastX+" Y : "+lastY);
        g.drawLine(firstX,firstY,lastX,lastY);
        firstX=lastX;
        firstY=lastY;

    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()== MouseEvent.BUTTON1) {
            this.firstX = e.getX();
            this.firstY = e.getY();
            this.lastX = firstX;
            this.lastY = firstY;
            this.i=1;
        }
        else if(e.getButton()== MouseEvent.BUTTON2) {
            this.i=2;
        } else if(e.getButton()== MouseEvent.BUTTON3) {
            this.i=3;
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.lastX = e.getX();
        this.lastY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    public void setC(Color c) {
        this.c = c;
    }

    public void setI(int i) {
        this.i = i;
    }
}
