package testing.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * @author rick.
 */
public class ClientWithAssortedProviders {

    public static void main (String ... args) throws IOException {
        String jsonResponse = usingJerseyClient();
        System.out.println(String.format("Response received \n%s", jsonResponse));
    }

    public static String usingRestTemplate() {
        RestTemplate rest = new RestTemplate();
        // auctionslist.json?archived=false
        String url = "http://www.sothebys.com/api/web/lots.json?saleNumber=PF1850&pageNum=0&itemsPerPage=5";
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.set("Accept-Language", "en-US;q=0.5");
        headers.setConnection("keep-alive");
        headers.set("User-Agent", "Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> jsonResponse = null;
        try {
            jsonResponse = rest.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return jsonResponse == null? null: jsonResponse.toString();
    }

    public static String usingHttpClient() throws IOException {
        URL url = new URL("http://www.sothebys.com/api/web/lots.json?saleNumber=PF1850&pageNum=0&itemsPerPage=5");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //connection.setRequestProperty("Accept-Language", "en-US;q=0.5");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0");
        connection.setRequestProperty("Connection", "keep-alive");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Cannot connect!" + connection.getResponseCode());
        }

        BufferedReader bread = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while(bread.ready()) {
            sb.append(bread.readLine()).append("\n");
        }

        connection.disconnect();
        return sb.toString();
    }

    public static String usingJerseyClient() {
        Client client = ClientBuilder.newClient();
        WebTarget lotsWebTarget = client.target("http://www.sothebys.com/api/web/lots.json?saleNumber=PF1850&pageNum=0&itemsPerPage=5");
        Invocation.Builder invocationBuilder = lotsWebTarget.request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0")
                .header("Connection", "keep-alive");
        String jsonResponse = invocationBuilder.get(String.class);
        return jsonResponse;
    }
}
