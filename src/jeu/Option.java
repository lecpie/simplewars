package jeu;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ressources.Ressources;

import devintAPI.*;

/** Cette classe est un exemple d'interface pour les options
 * 
 */
public class Option extends FenetreAbstraite{

	private static final String NOMDEFAULTJOUEUR1 = "Joueur 1";
	private static final String NOMDEFAULTJOUEUR2 = "Joueur 2";
	
	private static final String TEXTJOUEUR1 = "Nom du Joueur 1";
	private static final String TEXTJOUEUR2 = "Nom du Joueur 2";
	private static final String TEXTVALIDER = "Valider";
	
	private static final String KEYJ1 = "joueur1";
	private static final String KEYJ2 = "joueur2";
	
	private JTextArea champNomJoueur1;
	private JTextArea champNomJoueur2;
	
	
	// Donnees options
	private String nomJoueur1;
	private String nomJoueur2;
	
    public Option(String title) {
    	super(title);
    }
    
    // un label
	// est une variable d'instance car il doit être accessible 
	// dans la méthode changeColor, qui gère les préférences
	private JTextArea lb1;
    
	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		return "../ressources/sons/accueilOption.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/accueilOption.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavAide() {
		return "../ressources/sons/aide.wav";
	}
	
	private void loadOptions() {
		try {
			File ficOptions = new File(Ressources.OPTIONS);
			
			if (!ficOptions.exists()) {
				return;
			}
			
			BufferedReader br = new BufferedReader(new FileReader(ficOptions));
			
			for (String line; (line = br.readLine()) != null; ) {
				int firstSpace = line.indexOf(' ');
				String[] opt = { line.substring(0, firstSpace), line.substring(firstSpace + 1) };
				switch (opt[0]) {
				case KEYJ1:
					nomJoueur1 = opt[1];
					break;
				case KEYJ2:
					nomJoueur2 = opt[1];
					break;
				}
			}
			
			br.close();

		} catch (Exception e) {
			System.err.println("Erreur de chargement des options");
		}
		
	}

    public void init() {
    	loadOptions();
    	
    	setLayout(new BorderLayout());
    	
    	JPanel panneauOptions = new JPanel();
    	panneauOptions.setLayout(new GridLayout(2, 1));
    	
    	JPanel panneauNoms = new JPanel();
    	panneauNoms.setLayout(new GridLayout(2, 2));
    	
    	JLabel labelJoueur1 = new JLabel(TEXTJOUEUR1);
    	JLabel labelJoueur2 = new JLabel(TEXTJOUEUR2);
    	champNomJoueur1 = new JTextArea((nomJoueur1 == null) ? NOMDEFAULTJOUEUR1 : nomJoueur1);
    	champNomJoueur2 = new JTextArea((nomJoueur2 == null) ? NOMDEFAULTJOUEUR2 : nomJoueur2);
    	
    	panneauNoms.add(labelJoueur1); panneauNoms.add (champNomJoueur1);
    	panneauNoms.add(labelJoueur2); panneauNoms.add (champNomJoueur2);
    	

    	
    	panneauOptions.add(panneauNoms);
    	
    	
    	JButton boutonValider = new JButton(TEXTVALIDER);
    	boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ecrireOptions();
			}
    	});
    	
    	this.add(panneauOptions, BorderLayout.CENTER);
    	this.add(boutonValider, BorderLayout.SOUTH);
    	
    	// Appliquer font
    	for (JComponent cmp : new JComponent[] {labelJoueur1, labelJoueur2, 
    			champNomJoueur1, champNomJoueur2, boutonValider}) {
    		cmp.setFont(Ressources.FONT);
    	}
    }
    
    private void ecrireOptions() {
		try {
    	
	    	File ficOptions = new File(Ressources.OPTIONS);
			
			if (!ficOptions.exists()) {
				ficOptions.createNewFile();
			}
		
			BufferedWriter br = new BufferedWriter(new FileWriter(ficOptions));
			
			br.write(KEYJ1 + " " + champNomJoueur1.getText());
			br.newLine();
			
			br.write(KEYJ2 + " " + champNomJoueur2.getText());
			br.newLine();
			
			br.close();
		
		} catch (Exception e) {
			e.getMessage();
		}
    }
    
	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fenêtre
	 * Cette fonction est appelée par la fonction "changeColor" de la classe "Preferences"
	 * à chaque fois que l'on presse F3 
	 * 
	 * on change la couleur du texte principal
	 **/
	public  void changeColor() {
    	// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		lb1.setBackground(pref.getCurrentBackgroundColor());
		lb1.setForeground(pref.getCurrentForegroundColor());
	}

}
