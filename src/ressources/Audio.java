package ressources;

import t2s.SIVOXDevint;

public class Audio {
	private static final SIVOXDevint effets = new SIVOXDevint();
	
    private static final String SND = Ressources.RESS + "sons/";
    
    public static final String MUSIQUE = SND + "musique.wav";
    public static final String AIDE = SND + "aide.wav";
    
    private static final String UNT = SND + "unit/";
    
    public static final String SONCHEVALIER = UNT + "chevalier.wav";
    public static final String SONARCHER = UNT + "archer.wav";
    public static final String SONPIQUIER = UNT + "piquier.wav";
    
    public static void playSound(final String url) {
        effets.playWav(url);
    }
}
