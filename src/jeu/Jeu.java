package jeu;

import javax.swing.*;

import ressources.Audio;
import ressources.Maps;
import simplewars.affichage.PanelCarte;
import simplewars.affichage.PanelInformations;
import simplewars.map.Map;
import simplewars.map.MapReader;
import t2s.SIVOXDevint;
import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

import java.awt.*;
import java.awt.event.*;

/** Cette classe est un exemple d'interface de jeu.
 *  Elle �tend DevintFrame pour avoir un Frame et r�agir aux �v�nements claviers
 * Impl�mente ActionListener pour r�agir au clic souris sur le bouton.
 * On surchage la m�thode "keyPressed" pour associer une action � la touche F3
 * 
 * @author helene
 *
 */

public class Jeu extends FenetreAbstraite {

	private static final long serialVersionUID = 1470491642343208529L;
	
	// un label
	// est une variable d'instance car il doit �tre accessible 
	// dans la m�thode changeColor, qui g�re les pr�f�rences
	private JTextArea lb1;
	
	private SIVOXDevint musique;
	
	OptionData options;
	
	// appel au constructeur de la classe m�re
    public Jeu(String title) {
    	super(title);
    	
    	musique = new SIVOXDevint();
    	
    	options = new OptionData();
    	options.readOptions();
    	
    	int iMap = 0;
    	
	    MapReader mapReader = new MapReader();
		Map map = mapReader.readMap(Maps.getMap(iMap));
		
		PanelCarte panelCarte = new PanelCarte(map);
		PanelInformations panelInfo = new PanelInformations(options);
	    	    
	    Controlleur c = new Controlleur(map, this, panelInfo, options);
	    
	    panelCarte.setControl(c);
	    panelInfo.setControl(c);
	    
	    
	    if (options.isqMusique()) {
	        musique.loopWav(Audio.MUSIQUE);
	    }
        this.setLayout(new BorderLayout());

        setBackground(Color.white);

        //panelCarte.setPreferredSize(new Dimension(100,100));
        this.add(panelCarte,BorderLayout.CENTER);
        this.add(panelInfo,BorderLayout.EAST);
        
        setVisible(true);
     }
    
	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		return "../ressources/sons/accueil.wav";
	}
	
	// renvoie le fichier wave contenant la r�gle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/aideF1.wav";
	}
	
	// renvoie le fichier wave contenant la r�gle du jeu
	protected  String wavAide() {
		return Audio.AIDE;
	}

    // d�finition de la m�thode abstraite "init()"
    // initialise le frame 
    protected void init() {
    	
   }
 
    // �v�nements clavier
    public void keyPressed(KeyEvent e) {
    	// appel � la m�thode m�re qui g�re les �v�nements ESC, F1, F3, F4
    	super.keyPressed(e);
    	// cas particulier pour ce jeu : la touche F5
    	if (e.getKeyCode()==KeyEvent.VK_F5){
    	   	voix.playText("Vous venez d'appuyer sur EFFE 5");
    	}
    }
    
	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fen�tre
	 * Cette fonction est appel�e par la fonction "changeColor" de la classe "Preferences"
	 * � chaque fois que l'on presse F3 
	 * 
	 * on change la couleur du texte principal
	 **/
	public  void changeColor() {
    	// on r�cup�re les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		lb1.setBackground(pref.getCurrentBackgroundColor());
		lb1.setForeground(pref.getCurrentForegroundColor());
	}

}
