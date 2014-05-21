package simplewars.affichage;

import java.util.Arrays;

public enum Modes {

    // images / couleurs
    PEUVISIBLE("visibilité minimale"),
    
    // formes / couleurs
    MOYENNEMENTVISIBLE("visibilité moyenne"),
    
    // formes / noir et blanc
    TRESVISIBLE("visibilité maximale");
    
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
