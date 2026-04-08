package DAO;

import org.json.JSONObject;

public class Ligne {
    public int idProduit;
    public String libProduit;
    public int quantite;

    public Ligne(int idProduit, String libProduit, int quantite) {
        this.idProduit = idProduit;
        this.libProduit = libProduit;
        this.quantite = quantite;
    }

    public Ligne(JSONObject commande_json) {
        this.idProduit = commande_json.getInt("idProduit");
        this.libProduit = commande_json.getString("libProduit");
        this.quantite = commande_json.getInt("quantite");
    }
}
