package simplewars.affichage;



import ressources.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import jeu.Controlleur;

import simplewars.event.ColourCaseListener;
import simplewars.map.*;
import simplewars.unit.*;
/**
 * Panel affichant une case de la grille
 * @author Benjamin CLAQUIN
 *
 */
public class AfficheurCellule extends JPanel implements MouseListener,ColourCaseListener,KeyListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Coordonnee coordonnee;
    private Cellule cellule;
    Controlleur controlleur;
    private boolean belongToChampDeMovement=false;
    private boolean onOver=false;

    
    
    private boolean displayShape = true;
    
    
   private final int BORDERBOLD = 3;


    public AfficheurCellule(Cellule cellule) {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.cellule=cellule;
        try {
        this.coordonnee=cellule.getCoordonnee();
        }
        catch (NullPointerException e){
            System.err.println("[AfficheurCellule] Vous essayez d'afficher une celule ne contenant pas de coordonees");
        }
        
    }
    
    /**
     * Paint la cellule
     */
    public void paintComponent(Graphics g) {
        
        
        paintCellule(g);
            
    }

    /**
     * Paint successivement les differents composants de la cellule a savoir :
     * 
     * le terrain
     * le batiment
     * l'unite
     * 
     * Si la cellule contient ces dernieres
     * Envoi un message d'erreur si une image est introuvable
     * @param g
     */
    public void paintCellule(Graphics g){
        if(cellule.contientTerrain()){
            g.setColor(cellule.getTerrain().getCouleur());
            g.fillRect(0, 0, getWidth(), getHeight());
            try {
                BufferedImage lel = cellule.getTerrain().getBufferedImage();
                g.drawImage(lel,0,0, getWidth(), getHeight(),this);

            }catch(Exception e){
                System.err.println("image batiment introuvable");
            }
            
            setBorder(BorderFactory.createLineBorder(Color.BLACK,BORDERBOLD));
           
        }
        if(this.belongToChampDeMovement){
            setImage(Images.selecJAUNE,g);
        }
        if(this.onOver){
            setImage(Images.selecBLEU,g);
        }
        if(cellule.contientBatiment()){
            Batiment u =  cellule.getBatiment();
            try {
                BufferedImage bI=u.getBufferedImage();
                g.drawImage(bI,2*BORDERBOLD, 2*BORDERBOLD, getWidth()-4*BORDERBOLD, getHeight()-4*BORDERBOLD,this);

            }catch(Exception e){
                System.err.println("image batiment introuvable");
            }
        }
        
        if(cellule.contientUnite()){
            
            Unite u = cellule.getUnit();
            if (u.getJoueur().equals(controlleur.getJoueurCourant())){
                   setImage(Images.selecVERT,g);
            }
            else {
                setImage(Images.selecROUGE,g);
            }
            
                   
                BufferedImage bI= displayShape? u.getImageForme():u.getBufferedImage();
                g.drawImage(bI,2*BORDERBOLD,2*BORDERBOLD, getWidth()-4*BORDERBOLD, getHeight()-4*BORDERBOLD,this);
                
        }
    }
    
    
	public void setImage(String chemin,Graphics g){
	    try {
	        BufferedImage champDeMouvement = ImageIO.read(new File(chemin));
	        g.drawImage(champDeMouvement,0,0, getWidth(), getHeight(),this);
	
	    }catch(Exception e){
	        System.err.println("image introuvable "+chemin);
	    }
	    
	}
    
	public void setImage(BufferedImage image,Graphics g){
	    g.drawImage(image,0,0, getWidth(), getHeight(),this);    
	}
	
    public void mouseClicked(MouseEvent arg0) {
    	controlleur.click(coordonnee);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        onOver=true;
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        onOver=false;
    }

    public void mousePressed(MouseEvent arg0) {
        
    }

    public void mouseReleased(MouseEvent arg0) {
        
    }
    
    public void setControl(Controlleur c)
    {
    	this.controlleur  =c;
    	controlleur.onStateRealized(this, coordonnee);
    }

    
    @Override
    public void colourCase(Coordonnee c) {
        
        if (c.equals(this.coordonnee)){
            this.belongToChampDeMovement=true;
            System.out.println("coord" +c);
            System.out.println(belongToChampDeMovement);
        }
    }

    @Override
    public void decolourCase(Coordonnee c) {
        if (c.equals(this.coordonnee))
            this.belongToChampDeMovement=false;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        displayShape=displayShape?false:true;
        System.out.println("lel");
    }


}
