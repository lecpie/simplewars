package simplewars.map;

import simplewars.unit.Unite;

public class Cellule {

    //coordonee de la cellule sur la grille
	private Coordonnee coord;
	
	// unite eventuellement presente sur la cellule
	private Unite unit;
	
	// terrain sur la cellule
	private Terrain terrain;
	
	// batiment eventuellement present sur la cellule
	private Batiment batiment;
	
	// Constructeurs
	
	/**
	 * Constructeur de la classe cellule a partir de coordonnee et de terrain, elements minimums
	 * @param coord coordonnees de la cellule
	 * @param terrain terrain de la cellule
	 */
	public Cellule (Coordonnee coord, Terrain terrain) {
		this.coord = coord;
		this.terrain = terrain;
	}
	
	// Getters
	
	/**
	 * Renvoie le batiment de cette cellule
	 * @return le batiment de cette cellule
	 */
	public Batiment getBatiment() {
		return batiment;
	}
	
	/**
	 * Renvoie le terrain de la cellule
	 * @return le terrain de la cellule
	 */
	public Terrain getTerrain() {
		return terrain;
	}
	
	/**
	 * Renvoie l'unite presente sur la cellule
	 * @return
	 */
	public Unite getUnit() {
		return unit;
	}
	
	/**
	 * Renvoie les coordonnees de la case
	 * @return les coordonnees de la case
	 */
	public Coordonnee getCoordonnee() {
		return coord;
	}
	
	// Setters
	
	/**
	 * Affecte un nouveau batiment a une cellule
	 * @param batiment le nouveau batiment
	 */
	public void setBatiment (Batiment batiment) {
		this.batiment = batiment;
	}
	
	/**
	 * Affecte un nouveau terrain a une cellule
	 * @param terrain le nouveau terrain
	 */
	public void setTerrain (Terrain terrain) {
		this.terrain = terrain;
	}
	
	/**
	 * Affecter une unite a la cellule
	 * @param unit la nouvelle unite
	 */
	public void setUnit(Unite unit) {
		this.unit = unit;
	}
	
	/**
	 * Change les coordonnees de la case
	 * @param coord les nouvelles coordonnees de la case
	 */
	public void setCoordonnee (Coordonnee coord) {
		this.coord = coord;
	}

	
	/**
	 * Supprimer l'unite de la cellule
	 */
	public void supprimeUnite(){
	    unit=null;
	}
	
	/**
	 * @return Si il ya une unite sur cette case
	 */
	public boolean contientUnite(){
	    return unit!=null;
	}
	
	/**
	 * @return Si il ya un batiment sur cette case
	 */
	public boolean contientBatiment(){
	    return batiment!=null;
	}
	
	/**
	 * 
	 * @return Si cette case contient un terrain
	 */
	public boolean contientTerrain(){
	    return terrain!=null;
	}
	public boolean estVideetPrat()
	{
		if(!contientUnite() && !contientBatiment() && terrain.isPraticable())
		{
			return true;
		}
		return false;
	}
	/**
	 *  Affiche une representation en chaine de charactere du contenu d'une cellule
	 */
	/*public String toString(){
	    String retour="[   ";
	       if (contientTerrain())
	           retour+="terrain   :"+terrain;
	       if (contientBatiment())
	           retour+="batiment   :"+batiment;
	       if (contientUnite())
               retour+="unit   :"+unit;
	       retour+="]";
	       return retour+="    ]";
	       
	}*/
}



