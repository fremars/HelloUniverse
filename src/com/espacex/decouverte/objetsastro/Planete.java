package com.espacex.decouverte.objetsastro;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Jean-Philippe
 */
public abstract class Planete implements Comparable{
    public String nom;
    public long diametre;
    static int nbPlanetesDecouvertes;
    private String typeHumain;
    public Atmosphere atmosphere = new Atmosphere();
    static String forme = "Sphèrique";
    public float distanceEtoile;

    Planete(String nom){
        this.nom=nom;
        nbPlanetesDecouvertes++;
        System.out.println("La nouvelle planete s'appelle : "+nom);
    }

    static String expansion(double distance){
        if (distance < 14) {
           return("Oh la la mais c'est super rapide !");
        } else {
            return("Je reve ou c'est plus rapide que la lumière ?");
        }
    }


    public final int revolution(int degres) {
        System.out.println("Je suis la planète " + nom + " et je tourne autour de mon étoile de " + degres + " degrés.");
        return degres / 360;
    }

    public final int rotation(int degres) {
        System.out.println("Je suis la planète " + nom + " et je tourne sur moi-même de " + degres + " degrés.");
        return degres / 360;
    }

    @Override
    public int compareTo(Object o) {
        Planete autrePlanete = (Planete) o;
        return ((Float)distanceEtoile).compareTo(autrePlanete.distanceEtoile);
            }
}


