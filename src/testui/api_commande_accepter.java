package testui;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class api_commande_accepter {
    private String apiUrl = "http://localhost/www/api/commande_accepter.php";

    public boolean accepterCommande(int idCommande) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            String parametres = "idCommande=" + idCommande;
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
                writer.write(parametres);
            }

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
