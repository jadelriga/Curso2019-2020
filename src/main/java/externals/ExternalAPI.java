package externals;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalAPI {


    private static JSONObject sendQuery(String queryURL) {
        JSONObject body = null;
        try {
            URL obj = new URL(queryURL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            body = new JSONObject(response.toString());

        } catch (Exception ignored) {

        }

        return body;
    }

}
