package api;
import org.json.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class api_detail_commande {
    private String apiUrl = "http://localhost/www/api/detail_commande.php";

    public Object[][] recupererDetailCommande(int idCommande) {
        try {
            URL url = new URL(apiUrl + "?idCommande=" + idCommande);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                json.append(ligne);
            }
            reader.close();
            conn.disconnect();

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

        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            ex.printStackTrace();
            return new Object[0][0];
        }
    }
}
