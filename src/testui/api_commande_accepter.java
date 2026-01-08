package testui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class api_commande_accepter {
    private String apiUrl = "http://localhost/www/api/commande_accepter.php";

    public boolean accepterCommande(int idCommande) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            String parametres = "idCommande=" + idCommande;
            OutputStream os = conn.getOutputStream();
            os.write(parametres.getBytes());
            os.flush();
            os.close();

            int codeReponse = conn.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder reponse = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                reponse.append(ligne);
            }
            reader.close();
            conn.disconnect();

            return codeReponse == 200;

        } catch (Exception ex) {
            System.err.println("Erreur : " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
