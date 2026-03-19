package api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class api_detail_commande {
    public Object[][] recupererDetailCommande(int idCommande) {
        String json = "";
        String url = "http://localhost/www/api/detail_commande.php?idCommande=" + idCommande;
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

        JSONArray detailsArray = new JSONArray(json.toString());
        Object[][] donnees = new Object[detailsArray.length()][6];
        String[] ordreColonnes = {"idProduit", "libProduit", "quantite", "idCommande", "dateHeureCom", "loginUtil"};

        for (int i = 0; i < detailsArray.length(); i++) {
            JSONObject detail = detailsArray.getJSONObject(i);
            for (int j = 0; j < ordreColonnes.length; j++) {
                donnees[i][j] = detail.opt(ordreColonnes[j]);
            }
        }

        return donnees;
    }
}
