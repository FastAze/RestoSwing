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
        // Contiendra la reponse JSON renvoyee par l'API.
        String json = "";

        // Endpoint qui retourne la liste complete des commandes.
        String url = "http://localhost/www/api/liste_commandes.php";
        HttpClient client = HttpClient.newHttpClient();
        
        // Liste temporaire des lignes d'une commande.
        ArrayList<Ligne> lignes;

        // Liste metier des commandes reconstruites depuis le JSON.
        ArrayList<Commande> commandes = new ArrayList<>();
        int i = 0;
        int j = 0;

        try {
            // Requete HTTP GET simple vers l'API locale.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // On garde le corps uniquement si la reponse est OK.
                json = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
            }
        } catch (Exception ex) {
            // Affiche l'erreur reseau/parsing sans interrompre brutalement l'application.
            System.err.println("Erreur : " + ex.getMessage());
        }

        // Tableau JSON principal contenant toutes les commandes.
        JSONArray commandes_json = new JSONArray(json.toString());

        // Conversion JSON -> objets metier (Commande + Ligne).
        for (i = 0; i < commandes_json.length(); i++) {
            // Lecture d'une commande du tableau JSON principal.
            JSONObject commande_json = commandes_json.getJSONObject(i);
            lignes = new ArrayList<>();

            // Recuperation de toutes les lignes (produits) de la commande.
            JSONArray lignes_json = commande_json.getJSONArray("ligne");
            
            for (j = 0; j < lignes_json.length(); j++) {
                // Conversion de chaque ligne JSON en objet Ligne.
                JSONObject ligne_json = lignes_json.getJSONObject(j);
                Ligne ligne = new Ligne(ligne_json.getInt("idProduit"),
                    ligne_json.getString("libProduit"), 
                    ligne_json.getInt("quantite"));

                lignes.add(ligne);
            }

            // Construction de l'objet Commande avec ses lignes associees.
            Commande commande = new Commande(
                commande_json.getInt("idCommande"), 
                commande_json.getString("dateHeureCom"), 
                commande_json.getString("libEtat"), 
                commande_json.getInt("nbProduits"), 
                commande_json.getString("totalTTC"), 
                commande_json.getString("loginUtil"), lignes);
                
            commandes.add(commande);
        }

        // Tableau 2D attendu par l'UI Swing.
        Object[][] donnees = new Object[commandes_json.length()][7];

        // Remplissage du tableau avec les champs de chaque commande.
        for (i = 0; i < commandes.size(); i++) {
            donnees[i][0] = commandes.get(i).idCommande;
            donnees[i][1] = commandes.get(i).dateHeureCom;
            donnees[i][2] = commandes.get(i).libEtat;
            donnees[i][3] = commandes.get(i).nbProduits;
            donnees[i][4] = commandes.get(i).totalTTC;
            donnees[i][5] = commandes.get(i).ligne;
            donnees[i][6] = commandes.get(i).loginUtil;
        }

        return donnees;
    }
}