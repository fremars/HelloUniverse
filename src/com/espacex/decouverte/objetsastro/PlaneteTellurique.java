package com.espacex.decouverte.objetsastro;

import com.espacex.decouverte.enginsspatiaux.Vaisseau;
import com.espacex.decouverte.enginsspatiaux.VaisseauDeGuerre;

public class PlaneteTellurique extends Planete implements Habitable{

    String matiere = "Tellurique";
    int totalVisiteur;
    Vaisseau[] [] baieAccostage;

    public PlaneteTellurique(String nom, int tailleBaie){
        super(nom);
        this.baieAccostage=new Vaisseau[2] [tailleBaie];
    }

    public boolean restePlaceDisponible(Vaisseau vaisseau){
        int indexzone = 0;

        switch (vaisseau.type){
            case CARGO:
            case VAISSEAUMONDE:
                indexzone=1;
        }

        for(int index=0; index<baieAccostage[indexzone].length; index++){
            if(baieAccostage[indexzone] [index]==null){
                return true;
            }
        }
        return false;
    }


    @Override
    public void accueillirVaisseaux(Vaisseau... vaisseaux) {
        for(int i=0; i<vaisseaux.length; i++) {
            if (vaisseaux[i] instanceof VaisseauDeGuerre) {
                ((VaisseauDeGuerre) vaisseaux[i]).desactiverArmes();
            }

            totalVisiteur += vaisseaux[i].nbPassager;

            int indexzone = 0;

            switch (vaisseaux[i].type){
                case CARGO:
                case VAISSEAUMONDE:
                    indexzone=1;
            }

            for (int index = 0; index < baieAccostage.length; index++) {
                if (baieAccostage[indexzone] [index] == null) {
                    baieAccostage [indexzone] [index] = vaisseaux[i];
                    break;
                }
            }
        }
    }
}
