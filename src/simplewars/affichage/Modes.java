package simplewars.affichage;

import java.util.Arrays;

public enum Modes {

    // images / couleurs
    PEUVISIBLE("visibilit� minimale"),
    
    // formes / couleurs
    MOYENNEMENTVISIBLE("visibilit� moyenne"),
    
    // formes / noir et blanc
    TRESVISIBLE("visibilit� maximale");
    
    private String name;
    private Modes (String name){
        this.setName(name);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Modes getNext (){
        return values()[(Arrays.binarySearch(values(), this) + 1) % values().length];
    }
}
