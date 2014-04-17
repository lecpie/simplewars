package jeu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import ressources.Ressources;

public class OptionData {
	
	private static final String KEYJ1 = "joueur1";
	private static final String KEYJ2 = "joueur2";
	private static final String KEYMZK = "musique";
	
	private String nomJoueur1;
	private String nomJoueur2;
	private boolean qMusique;
	
	public OptionData() {
		setNomJoueur1(Option.NOMDEFAULTJOUEUR1);
		setNomJoueur2(Option.NOMDEFAULTJOUEUR2);
		setqMusique(Option.MUSIQUEDEFAULT);
	}
	
	public void readOptions() {
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
				case KEYMZK:
					qMusique = Boolean.parseBoolean(opt[1]);
					break;
				}
			}
			
			br.close();

		} catch (Exception e) {
			System.err.println("Erreur de chargement des options");
		}
	}
	
	public void writeOptions() {
		try {
	    	
	    	File ficOptions = new File(Ressources.OPTIONS);
			
			if (!ficOptions.exists()) {
				ficOptions.createNewFile();
			}
		
			BufferedWriter br = new BufferedWriter(new FileWriter(ficOptions));
			
			br.write(KEYJ1 + " " + nomJoueur1);
			br.newLine();
			
			br.write(KEYJ2 + " " + nomJoueur2);
			br.newLine();
			
			br.write(KEYMZK + " " + qMusique);
			br.newLine();
			
			br.close();
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public boolean isqMusique() {
		return qMusique;
	}

	public void setqMusique(boolean qMusique) {
		this.qMusique = qMusique;
	}

	public String getNomJoueur2() {
		return nomJoueur2;
	}

	public void setNomJoueur2(String nomJoueur2) {
		this.nomJoueur2 = nomJoueur2;
	}

	public String getNomJoueur1() {
		return nomJoueur1;
	}

	public void setNomJoueur1(String nomJoueur1) {
		this.nomJoueur1 = nomJoueur1;
	}
	
}
