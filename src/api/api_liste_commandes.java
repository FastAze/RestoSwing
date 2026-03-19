package api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class api_liste_commandes {
    public Object[][] recupererCommandes() {
        String json = "";
        String url = "http://localhost/www/api/liste_commandes.php";
        HttpClient client = HttpClient.newHttpClient();

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

        JSONArray commandesArray = new JSONArray(json.toString());
        Object[][] donnees = new Object[commandesArray.length()][5];
        String[] ordreColonnes = {"idCommande", "dateHeureCom", "libEtat", "COUNT(*)", "totalTTC"};

        for (int i = 0; i < commandesArray.length(); i++) {
            JSONObject commande = commandesArray.getJSONObject(i);
            for (int j = 0; j < ordreColonnes.length; j++) {
                donnees[i][j] = commande.opt(ordreColonnes[j]);
            }
        }

        return donnees;
    }
}