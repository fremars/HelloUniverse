package com.espacex.decouverte.enginsspatiaux;

public class VaisseauCivil extends Vaisseau {


    public VaisseauCivil(TypeVaisseau type){
        super(type);
        if(type==TypeVaisseau.CARGO){
            tonnageMax=500;
        }
        else if(type==TypeVaisseau.VAISSEAUMONDE){
            tonnageMax=2000;
        }

    }


    @Override
    public void emporterCargaison(int cargaison) throws DepassementTonnageException {
        int tonnageRestant = tonnageMax - tonnageActuel;
        if(cargaison>tonnageRestant){
            int tonnageEnExces = cargaison-tonnageRestant;
            throw new DepassementTonnageException(tonnageEnExces);
        } else{
            tonnageActuel = tonnageActuel + cargaison;
        }
    }
}
