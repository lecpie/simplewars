/*  Classe de menu de lancement de l'exemple de jeu.
 *  Cette classe hérite de la classe abstraite MenuAbstrait en définissant les méthodes :
 *     - nomOptions qui renvoie la liste des options possibles pour le menu 
 *     - lancerOption qui associe une action à chaque option du menu
 *     - wavAccueil() qui renvoie le nom du fichier wav lu lors de l'accueil dans le menu
 *     - wavAide() qui renvoie le nom du fichier wav lu lors de l'activation de la touche F1
 */

package jeu; 

import ressources.Audio;
import devintAPI.MenuAbstrait;

public class MenuJeu extends MenuAbstrait {
	private static final long serialVersionUID = 8675124682625403226L;

	/** constructeur
	 * @param title : le nom du jeu 
	 */
	public MenuJeu(String title) {
		super(title);
	}

	/** renvoie le nom des options du menu
     * vous pouvez définir autant d'options que vous voulez
     **/
	protected String[] nomOptions() {
		String[] noms = {"Jouer","Options","Quitter"};
		return noms;
	}

	/** lance l'action associée au bouton n°i
	 * la numérotation est celle du tableau renvoyé par nomOption
	 */
	protected void lancerOption(int i) {
		switch (i){  
		case 0 : new Jeu(nomJeu);break;
		case 1 : new Option(nomJeu + ": gestion des options");break;
		case 2 : System.exit(0);
		default: System.err.println("action non définie");
		}
	} 

	// renvoie le fichier wave contenant le message d'accueil
	// ces fichiers doivent être placés dans ressources/sons/
	protected  String wavAccueil() {
		return Audio.ACCUEIL;
	}

	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return Audio.REGLES;
	}
	
}
