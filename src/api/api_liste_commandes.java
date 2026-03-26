package api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.*;
import DAO.Commande;
import DAO.Ligne;

public class api_liste_commandes {
    public Object[][] recupererCommandes() {
        String json = "";
        String url = "http://localhost/www/api/liste_commandes.php";
        HttpClient client = HttpClient.newHttpClient();
        ArrayList<Ligne> lignes;
        ArrayList<Commande> commandes = new ArrayList<>();
        int i = 0;
        int j = 0;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                json = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
            }
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
        }

        JSONArray commandes_json = new JSONArray(json.toString());
        Object[][] donnees = new Object[commandes_json.length()][5];
        String[] ordreColonnes = {"idCommande", "dateHeureCom", "libEtat", "COUNT(*)", "totalTTC"};

        for (i = 0; i < commandes_json.length(); i++) {
            // Récupère la region
            JSONObject commande_json = commandes_json.getJSONObject(i);
            // Récupère les départements de la région
            lignes = new ArrayList<>(); // Collection des départements
            JSONArray lignes_json = commande_json.getJSONArray("ligne");
            for (j = 0; j < lignes_json.length(); j++) {
                JSONObject ligne_json = lignes_json.getJSONObject(j);
                Ligne ligne = new Ligne(ligne_json.getString("id"), ligne_json.getString("libelle"));
                lignes.add(ligne);
            }

            // Crée un objet métier à partir du JSON
            Commande commande = new Commande(lignes_json.getString("id"), lignes_json.getString("libelle"), lignes_json.getBoolean("est_outremer"), departements);
            commandes.add(commande);
        }

//        for (int i = 0; i < commandesArray.length(); i++) {
//            JSONObject commande = commandesArray.getJSONObject(i);
//            for (int j = 0; j < ordreColonnes.length; j++) {
//                donnees[i][j] = commande.opt(ordreColonnes[j]);
//            }
//        }

        return donnees;
    }
}