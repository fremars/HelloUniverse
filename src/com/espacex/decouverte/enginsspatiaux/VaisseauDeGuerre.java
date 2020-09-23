package com.espacex.decouverte.enginsspatiaux;

public class VaisseauDeGuerre extends Vaisseau {

    private boolean armesDesactivees;

    public VaisseauDeGuerre(TypeVaisseau type){
        super(type);
        if(type==TypeVaisseau.CHASSEUR){
            tonnageMax=0;
        }
        else if(type==TypeVaisseau.FREGATE){
                tonnageMax=50;
         }
        else if(type==TypeVaisseau.CROISEUR){
                tonnageMax = 100;
        }
    }

    public void activerBouclier(){
        desactiverArmes();
        System.out.println("Activation du bouclier d'un vaisseau de type "+type);
    }


    void attaquer(Vaisseau cible, String arme, int nbMinutes) {
        if (armesDesactivees = true) {
            System.out.println("Attaque impossible, les armes sont désactivées.");
        } else {
            System.out.println("Un vaisseau de type " + type + " attaque un vaisseau de type " + cible.type + " en utilisant l'arme " + arme + " pendant " + nbMinutes + " minutes.");
            cible.blindage = cible.blindage / 2;
            cible.resistanceDuBouclier = 0;
        }
    }

    public void desactiverArmes(){
            System.out.println("Désactivation des armes d'un vaisseau de type " + type);
            armesDesactivees = true;
        }

    public void activerArmes(){
        armesDesactivees = false;
    }

    @Override
    public void emporterCargaison(int cargaison) throws DepassementTonnageException {
        if(type.equals("CHASSEUR")) {
            int tonnageEnExces = cargaison;
            throw new DepassementTonnageException(tonnageEnExces);
        } else {
            int tonnagePassagers=2*nbPassager;
            int tonnageRestant=tonnageMax-tonnageActuel;
            int tonnageAConsidere=(tonnagePassagers<tonnageActuel ? tonnagePassagers : tonnageRestant);
            if(cargaison>tonnageAConsidere){
                int tonnageEnExces = cargaison-tonnageAConsidere;
                throw new DepassementTonnageException(tonnageEnExces);
            } else {
                tonnageActuel=tonnageActuel-cargaison;
            }
        }
    }

}
