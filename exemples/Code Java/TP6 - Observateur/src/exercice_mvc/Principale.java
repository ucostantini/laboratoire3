package exercice_mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Principale {


	 public static void main(String[] args) { 

         Model m = new Model();

         //NORD
         JPanel panneauDeControle= new JPanel(new GridLayout(1,2));
         panneauDeControle.add(new JLabel("Donner une chaîne "+"    ",JLabel.CENTER));

		 Controler ct = new Controler(10,m);
		 ct.setPreferredSize(new Dimension(200,30));

         panneauDeControle.add(ct);

		 //CENTRE
		 CenterView cv = new CenterView(m,5,5);

         //SUD
         JPanel panMaxMin = new JPanel(new GridLayout(2,2));
         panMaxMin.add(new JLabel("Plus grand mot ",JLabel.CENTER));

         BigView bv = new BigView(10);
         bv.setPreferredSize(new Dimension(200,30));
         panMaxMin.add(bv);

         panMaxMin.add(new JLabel("Plus petit mot ", JLabel.CENTER));
         SmallView sv = new SmallView(10);
         panMaxMin.add(sv);

         m.addObserver(cv);
         m.addObserver(bv);
         m.addObserver(sv);


         //JFRAME
         JFrame f=new JFrame();
         f.getContentPane().add(ct,BorderLayout.NORTH);
         f.getContentPane().add(new JScrollPane(cv),BorderLayout.CENTER);
         f.getContentPane().add(panMaxMin,BorderLayout.SOUTH);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setSize(new Dimension(400,400));
         f.setVisible(true);

	}

} 


