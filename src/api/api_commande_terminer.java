package api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class api_commande_terminer {
    public boolean accepterTerminer(int idCommande) {
        // Contiendra la reponse JSON renvoyee par l'API.
        String json = "";
        
        // Endpoint qui marque une commande comme terminee via son identifiant.
        String url = "http://localhost/www/api/commande_terminer.php?idCommande=" + idCommande;
        HttpClient client = HttpClient.newHttpClient();

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

        // Lecture de la cle "success" pour indiquer si l'operation a reussi.
        JSONObject jsonResponse = new JSONObject(json.toString());
        return jsonResponse.optBoolean("success", false);
    }
}
