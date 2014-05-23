package simplewars.affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;






import jeu.Controlleur;
import jeu.OptionData;
import ressources.Audio;
import ressources.Images;
import simplewars.joueur.Joueur;
import simplewars.event.JoueurChangedEvent;
import simplewars.event.JoueurChangedListener;

/**
 * Panel comportant les informations de jeu tel que le joueur actuel etc...
 * 
 * @author Benjamin CLAQUIN
 * 
 */
public class PanelInformations extends JPanel implements JoueurChangedListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private JFrame aide = new JFrame();
    private JButton tourSuivant, regles, controles;
    private JLabel joueurActuel;
    private Dimension dimension = new Dimension(300, 300);
    boolean rouge = false;
    private Controlleur control;

    private EventListenerList listenersChangementJoueur;

    public PanelInformations(OptionData options) {
        listenersChangementJoueur = new EventListenerList();

        tourSuivant = new JButton("suivant");
     
        JPanel panneauInstructions = new JPanel();
        panneauInstructions.setLayout(new GridLayout(2, 1));
        
        controles = new JButton("Controles");
        controles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.playSound(Audio.REGLES);
			}
        });
        
        regles = new JButton("Regles");
        regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                aide();
            }

            private void aide() {
                Audio.playSound(Audio.AIDE);

                regles.setVisible(true);
            }
        });

        tourSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                changerJoueur();
            }
        });

        regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                
                
                
                JPanel jp = new JPanel();
                // aide.add()
                JLabel image = new JLabel(new ImageIcon(
                        Images.AIDE));
               
                JButton sortir = new JButton("Sortir");
                sortir.setFont( new Font("Times",1,40));
                sortir.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                       aide.dispose();
                    }
                });
                jp.add(sortir);
                jp.add(image);
                aide.add(jp);
                aide.pack();
                aide.setVisible(true);
            }
        });
        KeyListener daide=new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
		    	if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
		    		
		    	   	aide.dispose();
		    	}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
        	
        };
        addKeyListener(daide);
        
        joueurActuel = new JLabel(options.getNomJoueur1());

        for (JComponent c : new JComponent[] {tourSuivant, regles, controles, joueurActuel}) {
        	c.setFont(new Font("sansserif", Font.BOLD, 50));
        }
        
        tourSuivant.setFocusable(false);
        regles.setFocusable(false);
        controles.setFocusable(false);
        
        setLayout(new BorderLayout());

        add(joueurActuel, BorderLayout.CENTER);

        add(tourSuivant, BorderLayout.NORTH);
        
        panneauInstructions.add(regles);
        panneauInstructions.add(controles);
        
        add(panneauInstructions, BorderLayout.SOUTH);
        joueurActuel.setForeground(Color.GREEN);

    }

    public void changed(JoueurChangedEvent e) {
        Joueur j = e.getJoueur();
        if(!rouge) rouge = true;
        else rouge = false;
       
        
        joueurActuel.setText(j.getNom());
        
        if(!rouge)
        {
        	joueurActuel.setForeground(Color.GREEN);
        }
        else
        {
        	joueurActuel.setForeground(Color.RED);
        }
    }

    public void changerJoueur() {
        control.joueurSuivant();
        fireJoueurChangedEvent();
        
    }

    public void fireJoueurChangedEvent() {

        JoueurChangedEvent evt = new JoueurChangedEvent(this,
                control.getJoueurCourant());

        for (JoueurChangedListener l : listenersChangementJoueur
                .getListeners(JoueurChangedListener.class)) {
            l.changed(evt);
        }
    }

    public void addJoueurChangedListener(JoueurChangedListener l) {
        listenersChangementJoueur.add(JoueurChangedListener.class, l);
    }

    public void setControl(Controlleur c) {
        this.control = c;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimention) {
        this.dimension = dimention;
    }

}
