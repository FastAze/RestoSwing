package testui;
import org.json.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class api_liste_commandes {
    private String apiUrl = "http://localhost/restoweb/api/liste_commandes.php";

    public Object[][] recupererCommandes() {
        try {
            URL url = new URL(apiUrl);
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

        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            ex.printStackTrace();
            return new Object[0][0];
        }
    }
}