import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScribbleGUI extends JPanel {
    private Scribble sb;

    public ScribbleGUI() {
        this.sb = new Scribble();
        this.sb.setPreferredSize(new Dimension(700, 700));


        JButton b1 = new JButton("effacer");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sb.setI(2);
                repaint();
            }
        });
        this.add(b1);

        final JComboBox<String> c = new JComboBox<>(new String[]{" noir ", " vert ", " bleu ", " jaune ", " gris ", " violet ", " rose ", " rouge "});
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (c.getSelectedIndex()) {
                    case 0:
                        sb.setC(Color.BLACK);
                        break;
                    case 1:
                        sb.setC(Color.GREEN);
                        break;
                    case 2:
                        sb.setC(Color.BLUE);
                        break;
                    case 3:
                        sb.setC(Color.YELLOW);
                        break;
                    case 4:
                        sb.setC(Color.GRAY);
                        break;
                    case 5:
                        sb.setC(new Color(188, 37, 233));
                        break;
                    case 6:
                        sb.setC(Color.PINK);
                        break;
                    case 7:
                        sb.setC(Color.RED);
                        break;
                }

                sb.requestFocusInWindow();
                sb.requestFocus();

            }
        });
        this.add(c);

        this.add(this.sb);


    }

    public Scribble getSb() {
        return sb;
    }
}
