package ressources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Images {

    private static final String IMG = Ressources.RESS + "images/";
    
    private static final String BAT = IMG + "batiment/";
    private static final String UNIT = IMG + "unit/";
    private static final String TERR = IMG + "terrains/";
    
    public static final String CHATEAU = BAT + "chateau.png";
    
    public static final String AIDE = IMG + "explications/explic.jpg";
    
    private static final String CURSOR = IMG + "cursor/";
    
    public static final String CURSORBIG = CURSOR + "cursor.png";
    
    private static final String CHECKBOX = IMG + "checkbox/";
    public static final String CHECKBOXCHECKED = CHECKBOX + "checked.png";
    public static final String CHECKBOXUNCHECKED = CHECKBOX + "unchecked.png";

    // Unites
    public static final String ARCHERG = UNIT + "unit_archG.png";
    public static final String ARCHERD = UNIT + "unit_archD.png";
    public static final String PIQUIERG = UNIT + "unit_piqG.png";
    public static final String PIQUIERD = UNIT + "unit_piqD.png";
    public static final String CHEVALIERG = UNIT + "unit_chevG.png";
    public static final String CHEVALIERD = UNIT + "unit_chevD.png";

    // Unites avec formes

    public static final String ARCHER = UNIT + "triangle.png";
    public static final String PIQUIER = UNIT + "carre.png";
    public static final String CHEVALIER = UNIT + "rond.png";

            // formes creuses
    public static final String ARCHER_CREUX = UNIT + "triangle_creux.png";
    public static final String PIQUIER_CREUX = UNIT + "carre_creux.png";
    public static final String CHEVALIER_CREUX = UNIT + "rond_creux.png";
    
    // Terrain

    // Terrain praticable
    public static String lienPraticable = TERR + "praticable/";

    public static BufferedImage[] tabTerrainPraticable = new BufferedImage[6];

    // Terrain impraticable
    public static String lienImpraticable = TERR +  "impraticable/";

    public static BufferedImage[] tabImpraticable = new BufferedImage[1];

    // Selecteurs
    public static final String selecteur = IMG + "selecteurs/";
    public static final String selecJAUNE = selecteur + "/selectajaune.png";
    public static final String selecVERT = selecteur + "/selectaalliedi.png";
    public static final String selecROUGE = selecteur + "/selectaennemi.png";
    public static final String selecBLEU = selecteur + "/selecta.png";
    public static final String selecNOIR = selecteur + "/dejadeplace.png";
    public static final String selecHACHURES = selecteur + "/hachures.png";

    public static BufferedImage selecteurJauneB,selecteurVertB,selecteurRougeB,selecteurBleuB,selecteurNoirB, arch_G, arch_D, chev_D,
            chev_G, piq_D, piq_G, arch, piq, chev,arch_c,piq_c,chev_c,selecteurHachures;

    // Set les images
    static {

        try {
            
            selecteurJauneB = ImageIO.read(new File(selecJAUNE));
            selecteurVertB = ImageIO.read(new File(
                    selecVERT));
            selecteurRougeB = ImageIO.read(new File(
                    selecROUGE));
            selecteurBleuB = ImageIO.read(new File(
                    selecBLEU));
            selecteurNoirB = ImageIO.read(new File(
                    selecNOIR));
            selecteurHachures = ImageIO.read(new File(
                    selecHACHURES));

            arch_G = ImageIO.read(new File(ARCHERG));
            arch_D = ImageIO.read(new File(ARCHERD));
            chev_G = ImageIO.read(new File(CHEVALIERG));
            chev_D = ImageIO.read(new File(CHEVALIERD));
            piq_G = ImageIO.read(new File(PIQUIERG));
            piq_D = ImageIO.read(new File(PIQUIERD));
            arch = ImageIO.read(new File(ARCHER));
            piq = ImageIO.read(new File(PIQUIER));
            chev = ImageIO.read(new File(CHEVALIER));
            arch_c = ImageIO.read(new File(ARCHER_CREUX));
            piq_c = ImageIO.read(new File(PIQUIER_CREUX));
            chev_c = ImageIO.read(new File(CHEVALIER_CREUX));

        } catch (Exception e) {
            System.err.println("image non trouvee");
        }

        // Terrains impraticables
        for (int i = 0; i < tabTerrainPraticable.length; i++) {
            try {
                tabTerrainPraticable[i] = ImageIO.read(new File(lienPraticable
                        + "snow_" + i + ".png"));
            } catch (Exception e) {
                System.out.println("Fichier manquant " + lienPraticable
                        + "snow_" + i + ".png");
            }
        }

        // Terrains praticables
        for (int i = 0; i < tabImpraticable.length; i++) {
            try {
                tabImpraticable[i] = ImageIO.read(new File(lienImpraticable
                        + "imprat_" + i + ".png"));
            } catch (Exception e) {
                System.out.println("Fichier manquant " + lienImpraticable
                        + "imprat_" + i + ".png");
            }
        }

    }

    public static BufferedImage pickATerrainPraticable() {
        Random rand = new Random();
        int n = rand.nextInt(5);
        return tabTerrainPraticable[n];
    }

    public static BufferedImage pickATerrainImpraticable() {
        Random rand = new Random();
        int n = rand.nextInt(tabImpraticable.length);
        return tabImpraticable[n];
    }

}
