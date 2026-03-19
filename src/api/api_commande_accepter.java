package testui;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api_commande_accepter {
    private String apiUrl = "http://localhost/www/api/commande_accepter.php";
    public boolean accepterCommande(int idCommande) {
        try {
            URL url = new URL(apiUrl.replace("detail_commande.php", "commande_accepter.php")
                    + "?idCommande=" + idCommande);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();

            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                response.append(ligne);
            }
            reader.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.optBoolean("success", false);

        } catch (Exception ex) {
            System.err.println("Erreur lors de l'acceptation de la commande : " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

}