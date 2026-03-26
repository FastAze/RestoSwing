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
            JSONObject commande_json = commandes_json.getJSONObject(i);
            lignes = new ArrayList<>();
            JSONArray lignes_json = commande_json.getJSONArray("ligne");
            for (j = 0; j < lignes_json.length(); j++) {
                JSONObject ligne_json = lignes_json.getJSONObject(j);
                Ligne ligne = new Ligne(ligne_json.getInt("idProduit"), ligne_json.getString("libProduit"), ligne_json.getInt("quantite"));
                lignes.add(ligne);
            }

            Commande commande = new Commande(commande_json.getInt("idCommande"), commande_json.getString("dateHeureCom"), commande_json.getString("libEtat"), commande_json.getInt("COUNT(*)"), commande_json.getString("totalTTC"), commande_json.getString("loginUtil"), lignes);
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