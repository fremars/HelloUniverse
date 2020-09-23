package com.espacex.decouverte.enginsspatiaux;

public class DepassementTonnageException extends Exception {

    public int tonnageEnExces;

    public DepassementTonnageException (int tonnageEnExces){
        super("La cargaison ne peut être chargée car elle représente un excès de "+tonnageEnExces);
        this.tonnageEnExces = tonnageEnExces;
    }
}
