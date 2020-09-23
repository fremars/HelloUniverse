package com.espacex.decouverte;

import com.espacex.decouverte.enginsspatiaux.*;
import com.espacex.decouverte.objetsastro.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static com.espacex.decouverte.enginsspatiaux.TypeVaisseau.*;


public class HelloUniverse {

    public static void main(String... args) {

        Galaxie systemSolaire = new Galaxie();
        systemSolaire.nom = "Système solaire";


        PlaneteTellurique mercure = new PlaneteTellurique("Mercure",1);
        mercure.diametre = 4880;
        mercure.distanceEtoile = 57.9f;
        systemSolaire.planetes.add(mercure);
        PlaneteTellurique venus = new PlaneteTellurique("Venus",2);
        venus.diametre = 12100;
        venus.distanceEtoile = 108.2f;
        systemSolaire.planetes.add(venus);
        PlaneteTellurique terre = new PlaneteTellurique("Terre",10);
        terre.diametre = 12756;
        terre.distanceEtoile = 149.6f;
        systemSolaire.planetes.add(terre);
        PlaneteTellurique mars = new PlaneteTellurique("Mars",3);
        mars.diametre = 6792;
        mars.distanceEtoile = 227.9f;
        systemSolaire.planetes.add(mars);
        PlaneteGazeuse jupiter = new PlaneteGazeuse("Jupiter");
        jupiter.diametre = 142984;
        jupiter.distanceEtoile = 778.5f;
        systemSolaire.planetes.add(jupiter);
        PlaneteGazeuse saturne = new PlaneteGazeuse("Saturne");
        saturne.diametre = 120536;
        saturne.distanceEtoile = 1434f;
        systemSolaire.planetes.add(saturne);
        PlaneteGazeuse uranus = new PlaneteGazeuse("Uranus");
        uranus.diametre = 51118;
        uranus.distanceEtoile = 2871f;
        systemSolaire.planetes.add(uranus);
        PlaneteGazeuse neptune = new PlaneteGazeuse("Neptune");
        neptune.diametre = 49532;
        neptune.distanceEtoile = 4495f;
        systemSolaire.planetes.add(neptune);

        for (Planete nextPlanete: systemSolaire.planetes) {
            System.out.println("La planète suivante est : "+nextPlanete.nom);
        }
        
        Atmosphere atmosphereMars = new Atmosphere();
        atmosphereMars.constituants.put("CO2", 95f);
        atmosphereMars.constituants.put("N2", 3f);
        atmosphereMars.constituants.put("AR", 1.5f);
        atmosphereMars.constituants.put("NO", 0.013f);

        mars.atmosphere=atmosphereMars;
        System.out.println("L'atmosphère de Mars est constituée de : ");
        for (Map.Entry<String, Float> constituant : mars.atmosphere.constituants.entrySet()) {

            System.out.println(constituant.getValue()+"% de "+constituant.getKey());
            
        }

        Vaisseau chasseur = new VaisseauDeGuerre(CHASSEUR);
        chasseur.blindage = 156;
        chasseur.resistanceDuBouclier=2;

        Vaisseau vaisseauMonde = new VaisseauCivil(VAISSEAUMONDE);
        vaisseauMonde.blindage = 4784;
        vaisseauMonde.resistanceDuBouclier=30;

        Vaisseau fregate=new VaisseauDeGuerre(FREGATE);
        Vaisseau croiseur=new VaisseauDeGuerre(CROISEUR);
        Vaisseau cargo=new VaisseauDeGuerre(CARGO);


        mars.accueillirVaisseaux(cargo, vaisseauMonde, chasseur);

        Scanner sc = new Scanner(System.in);

        boolean recommencer = true;

        while (recommencer) {
            System.out.println("Quel vaisseau ?");
            String vaisseauAManipuler = sc.nextLine();
            System.out.println("Quelle planète ?");
            String planeteAtterissage = sc.nextLine();

            int tonnageEmbarquer;
            while(true){
                System.out.println("Quel tonnage ?");
                try{
                    tonnageEmbarquer = sc.nextInt();
                    break;
                }catch (InputMismatchException ime){
                    System.out.println("Le tonnage n'est pas numérique.");
                } finally {
                    sc.nextLine();
                }
            }

            TypeVaisseau typeVaisseau = valueOf(vaisseauAManipuler);
            Vaisseau vaisseau = null;

            switch (typeVaisseau) {
                case CHASSEUR:
                    vaisseau = chasseur;
                    break;
                case FREGATE:
                    vaisseau = fregate;
                    break;
                case CROISEUR:
                    vaisseau = croiseur;
                    break;
                case VAISSEAUMONDE:
                    vaisseau = vaisseauMonde;
                    break;
                case CARGO:
                    vaisseau = cargo;
                    break;
            }

            Planete planeteselectionnee = null;
            for (Planete p : systemSolaire.planetes) {
                if(p.nom.equalsIgnoreCase(planeteAtterissage)){
                    planeteselectionnee=p;
                    break;
                }
            }



            if (planeteselectionnee instanceof PlaneteGazeuse){
                System.out.println("La planète sélectionnée n'est pas une planète Tellurique, recommencez.");
                continue;
            }

            PlaneteTellurique planete = (PlaneteTellurique)planeteselectionnee;

            if (!planete.restePlaceDisponible(vaisseau)) {
                System.out.println("Le vaisseau ne peut pas atterir par manque de place.");
            } else {
                planete.accueillirVaisseaux(vaisseau);

                try {
                    vaisseau.emporterCargaison(tonnageEmbarquer);
                } catch (DepassementTonnageException dte) {
                    System.out.println("Le vaisseau a rejeté "+ dte.tonnageEnExces +" tonnes.");
                    System.out.println("Voulez-vous emportez un tonnage partiel à hauteur de "+(tonnageEmbarquer-dte.tonnageEnExces)+ " tonnes (oui/non) ?");
                    String accepte = sc.nextLine();
                    if (accepte.equalsIgnoreCase("oui")){
                        try {
                            vaisseau.emporterCargaison(tonnageEmbarquer-dte.tonnageEnExces);
                        } catch (DepassementTonnageException e) {
                            System.out.println("Erreur innatendue.");
                        }
                    } else{
                        System.out.println("Opération annulée.");
                    }
                }

            }

            System.out.println("Voulez-vous recommencer ?");
            recommencer = sc.nextLine().equalsIgnoreCase("oui");
        }
    }

}
