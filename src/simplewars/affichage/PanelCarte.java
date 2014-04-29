package simplewars.affichage;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simplewars.map.Coordonnee;
import simplewars.map.Map;

import javax.swing.JPanel;

import jeu.Controlleur;

public class PanelCarte extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private int largeur,hauteur;
    private AfficheurCellule[][] tableauDeCellules;
    
    public AfficheurCellule[][] getTableauDeCellules() {
        return tableauDeCellules;
    }

    public void setTableauDeCellules(AfficheurCellule[][] tableauDeCellules) {
        this.tableauDeCellules = tableauDeCellules;
    }

    /**
     * Panel sur lequel est affiche la carte de jeu
     * @param map
     */
    public PanelCarte(Map map){
        
        
        largeur = map.getLargeur();
        hauteur = map.getHauteur();
                
        tableauDeCellules = new AfficheurCellule[largeur][hauteur];
        
        setLayout(new GridLayout(largeur,hauteur));
        for (int i = 0; i <largeur ; i++) {
            for (int j = 0; j <largeur ; j++){
                AfficheurCellule aC = new AfficheurCellule(map.getCellule(new Coordonnee(i,j)));
                tableauDeCellules[i][j] = aC;
                add(aC);
            }
        }
        
    }
    
    public void setControl (Controlleur c) {
      
        
        //Propagation du controlleur aux cellules
        for (int i = 0; i < tableauDeCellules.length ; i++) {
            for (int j = 0; j < tableauDeCellules[i].length ; j++){
                tableauDeCellules[i][j].setControl(c);

            }
        }     
    }


    public void changeDisplayShape (){
        for (int i = 0; i < tableauDeCellules.length ; i++) {
            for (int j = 0; j < tableauDeCellules[i].length ; j++){
                tableauDeCellules[i][j].displayShape();
            }
        }  
    }
}
