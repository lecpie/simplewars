package simplewars.unit;

import ressources.Audio;
import ressources.Images;

import java.awt.image.BufferedImage;

import simplewars.joueur.Joueur;

public class Piquier extends Unite{

    private static final int NBDEPLACEMENT = 1;
    
    private static final BufferedImage ImageGauche = Images.piq_G;
    private static final BufferedImage ImageDroite = Images.piq_D;
    private static final BufferedImage ImageForme = Images.piq;
    private static final BufferedImage ImageForme_c = Images.piq_c;
    
    private static final String ABREV = "piq";
        
    public String getSoundSelected() {
    	return Audio.SONPIQUIER;
    }
    
    public Piquier (Joueur joueur){
        super(joueur);
    }
    
    public int getNbDeplacement() {
        return NBDEPLACEMENT;
    }

    public String toString(){
        try {
        return "Piquer du joueur "+joueur;
        }
        catch (NullPointerException e) {
            System.err.println("lel, un piquier n'a pas de joueur");
        }
        return "error";
    }

    public String getNomAbrev() {
        return ABREV;
    }
    
    @Override
    public BufferedImage getImageDroite() {
        return ImageDroite;
    }

    @Override
    public BufferedImage getImageGauche() {
        return ImageGauche;
    }

    @Override
    public BufferedImage getImageForme() {
        // TODO Auto-generated method stub
        return ImageForme;
    }
    
    
    @Override
    public BufferedImage getImageFormeCreuse() {
        // TODO Auto-generated method stub
        return ImageForme_c;
    }
}
