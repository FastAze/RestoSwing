package api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class api_commande_terminer {
    public boolean accepterTerminer(int idCommande) {
        String json = "";
        String url = "http://localhost/www/api/commande_terminer.php?idCommande=" + idCommande;
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

        JSONObject jsonResponse = new JSONObject(json.toString());
        return jsonResponse.optBoolean("success", false);
    }
}
