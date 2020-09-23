package com.espacex.decouverte.enginsspatiaux;

public abstract class Vaisseau {
    public int nbPassager;
    public final TypeVaisseau type;
    public int blindage;
    public int resistanceDuBouclier;
    public int tonnageMax;
    protected int tonnageActuel;

    void activerBouclier(){
        System.out.println("Activation du bouclier d'un vaisseau de type "+type);
    }

    void desactiverBouclier(){
        System.out.println("Desactivation du bouclier d'un vaisseau de type "+type);
    }

    public Vaisseau(TypeVaisseau type) {
        this.type = type;
    }

    public abstract void emporterCargaison(int cargaison) throws DepassementTonnageException;
}
