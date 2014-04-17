package jeu;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import ressources.Images;
import ressources.Ressources;

import devintAPI.*;

/** Cette classe est un exemple d'interface pour les options
 * 
 */
public class Option extends FenetreAbstraite{

	private static final long serialVersionUID = 2906428985961751490L;
	
	public static final String NOMDEFAULTJOUEUR1 = "Joueur 1";
	public static final String NOMDEFAULTJOUEUR2 = "Joueur 2";
	public static final boolean MUSIQUEDEFAULT = true;
	
	private static final String TEXTJOUEUR1 = "Nom du Joueur 1";
	private static final String TEXTJOUEUR2 = "Nom du Joueur 2";
	private static final String TEXTEMUSIQUE = "Musique";
	private static final String TEXTVALIDER = "Valider";
	
	private JTextArea champNomJoueur1;
	private JTextArea champNomJoueur2;
	private JCheckBox boxMusique;
	
	// Donnees options
	private OptionData optionData;
	
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

    public void init() {
    	optionData = new OptionData();
    	optionData.readOptions();
    	
    	setLayout(new BorderLayout());
    	
    	JPanel panneauOptions = new JPanel();
    	panneauOptions.setLayout(new GridLayout(3, 2));
    	
    	
    	JLabel labelJoueur1 = new JLabel(TEXTJOUEUR1);
    	JLabel labelJoueur2 = new JLabel(TEXTJOUEUR2);
    	JLabel labelMusique = new JLabel(TEXTEMUSIQUE);
    	
    	champNomJoueur1 = new JTextArea(optionData.getNomJoueur1());
    	champNomJoueur2 = new JTextArea(optionData.getNomJoueur2());
    	
    	boxMusique = configureCheckbox();
    	boxMusique.setSelected(optionData.isqMusique());
    	
    	panneauOptions.add(labelJoueur1); panneauOptions.add (champNomJoueur1);
    	panneauOptions.add(labelJoueur2); panneauOptions.add (champNomJoueur2);
    	panneauOptions.add(labelMusique); panneauOptions.add(boxMusique);
    	
    	JButton boutonValider = new JButton(TEXTVALIDER);
    	boutonValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionData.setNomJoueur1(champNomJoueur1.getText());
				optionData.setNomJoueur2(champNomJoueur2.getText());
				optionData.setqMusique(boxMusique.isSelected());
				optionData.writeOptions();
				
				dispose();
			}
    	});
    	
    	this.add(panneauOptions, BorderLayout.CENTER);
    	this.add(boutonValider, BorderLayout.SOUTH);
    	
    	// Appliquer font
    	for (JComponent cmp : new JComponent[] {labelJoueur1, labelJoueur2, labelMusique,
    			champNomJoueur1, champNomJoueur2, boutonValider, boxMusique}) {
    		cmp.setFont(Ressources.FONT);
    	}
    }
    
    private JCheckBox configureCheckbox() {
    	Icon normal = new ImageIcon(Images.CHECKBOXUNCHECKED);
    	Icon selected = new ImageIcon(Images.CHECKBOXCHECKED);
    	JTable table = new JTable();
    	//table.setRowHeight(...);

    	TableCellRenderer renderer = table.getDefaultRenderer(Boolean.class);
    	JCheckBox checkBoxRenderer = (JCheckBox)renderer;
    	checkBoxRenderer.setIcon( normal );
    	checkBoxRenderer.setSelectedIcon( selected );

    	DefaultCellEditor editor = (DefaultCellEditor)table.getDefaultEditor(Boolean.class);
    	JCheckBox checkBoxEditor = (JCheckBox)editor.getComponent();
    	checkBoxEditor.setIcon( normal );
    	checkBoxEditor.setSelectedIcon( selected );
    	
    	return checkBoxEditor;
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
