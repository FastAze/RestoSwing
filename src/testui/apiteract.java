package testui;

import org.json.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class apiteract {
    private final HttpClient client = HttpClient.newHttpClient();
    private String commandes_json = "[]";

    public apiteract() {
        try {
            HttpRequest get_commandes = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost/restoweb/api/liste_commandes.php"))
                    .GET()
                    .build();
            HttpResponse<String> commande = client.send(get_commandes, HttpResponse.BodyHandlers.ofString());
            if (commande != null && commande.body() != null) {
                commandes_json = commande.body();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public Object[][] getCommandesAsTableData(String[] columns) {
        JSONArray jsonArray = new JSONArray(commandes_json);
        Object[][] data = new Object[jsonArray.length()][columns.length];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            for (int j = 0; j < columns.length; j++) {
                data[i][j] = obj.has(columns[j]) ? obj.get(columns[j]) : null;
            }
        }
        return data;
    }
}
