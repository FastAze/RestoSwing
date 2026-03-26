package DAO;

import java.util.ArrayList;

public class Commande {
    public int idCommande;
    public String dateHeureCom;
    public String libEtat;
    public int nbProduits;
    public String totalTTC;
    public String loginUtil;
    public ArrayList<Ligne> ligne= new ArrayList<>();

    public Commande(int idCommande, String dateHeureCom, String libEtat, int nbProduits, String totalTTC, String loginUtil, ArrayList<Ligne> ligne) {
        this.idCommande = idCommande;
        this.dateHeureCom = dateHeureCom;
        this.libEtat = libEtat;
        this.nbProduits = nbProduits;
        this.totalTTC = totalTTC;
        this.loginUtil = loginUtil;
        this.ligne = ligne;
    }
}
