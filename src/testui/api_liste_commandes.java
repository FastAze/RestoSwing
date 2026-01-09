package testui;

import org.json.*;
import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class api_liste_commandes {
    public Object[][] getData() {
        String json2 = "";
        String url = "http://localhost/www/api/liste_commandes.php";
        Object[][] data = new Object[0][];

        ArrayList<Noce> noces = new ArrayList<>();

        // Créer un HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // Crée une requête HTTP GET
        try {
            // Construit l'URL de la requête
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();
            // Envoie la requête et attend la réponse
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            // Vérifie que la réponse est normale
            if (response.statusCode() == 200) {
                json2 = response.body();
            } else {
                System.err.println("Erreur : Code statut " + response.statusCode());
            }
        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            //ex.printStackTrace();
        }

        try {
            JSONArray noces_json = new JSONArray(json2);
            // Parcoure le tableau JSON
            for (int i = 0; i < noces_json.length(); i++) {
                JSONObject noce_json = noces_json.getJSONObject(i);
                Noce noce = new Noce(noce_json);
                noces.add(noce);
            }


            System.out.println("-- Liste des noces --");
            System.out.println(noces_json);

            String[] ColumnNames = {"ID", "Date", "Etat", "Nombre de plat", "total TTC"};

            data = new Object[noces_json.length()][5];

            for (int i = 0; i < noces_json.length(); i++) {
                JSONObject noce_json = noces_json.getJSONObject(i);

                for (int j = 0; j < ColumnNames.length; j++) {
                    data[i][j] = noce_json.opt(ColumnNames[j]);
                }
            };


        } catch (JSONException ex) {
            System.err.println("Erreur : " + ex.getMessage());
            //ex.printStackTrace();
        }
        return data;
    }
}