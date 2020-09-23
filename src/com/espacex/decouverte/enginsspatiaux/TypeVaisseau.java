package com.espacex.decouverte.enginsspatiaux;

public enum TypeVaisseau {
    CHASSEUR("CHASSEUR"),
    FREGATE("FREGATE"),
    VAISSEAUMONDE("VAISSEAU-MONDE"),
    CARGO("CARGO"),
    CROISEUR("CROISEUR");

    String nom;

    TypeVaisseau(String nom){
        this.nom=nom;
    }
}
