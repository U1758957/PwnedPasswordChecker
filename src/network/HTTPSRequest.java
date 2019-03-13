package network;

import errors.NetworkError;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPSRequest {

    private URL url;

    /**
     * Constructor for HTTPSRequest
     *
     * @param beginningOfHash the first 5 characters of the hash for the URL
     */
    public HTTPSRequest(String beginningOfHash) throws NetworkError {
        try {
            this.url = new URL("https://api.pwnedpasswords.com/range/" + beginningOfHash);
        } catch (MalformedURLException e) {
            throw new NetworkError("NetworkError: " + e.getMessage());
        }
    }

    /**
     * Get a list of hashes that contain the same first 5 characters as the password's hash
     *
     * @return a list of hashes
     * @throws NetworkError if the website doesn't respond, or an IOException occurs
     */
    public Map<String, Integer> hashDict() throws NetworkError {
        Map<String, Integer> hashDictionary = new HashMap<>();
        String currentHash;
        String[] hashSplit;
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                            "AppleWebKit/537.11 (KHTML, like Gecko) " +
                            "Chrome/23.0.1271.95 " +
                            "Safari/537.11");
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != 200) throw new NetworkError("Error fetching URL, status "
                    + connection.getResponseCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((currentHash = reader.readLine()) != null) {
                hashSplit = currentHash.split(":");
                hashDictionary.put(hashSplit[0], Integer.valueOf(hashSplit[1]));
            }
            reader.close();
        } catch (IOException e) {
            throw new NetworkError("NetworkError: " + e.getMessage());
        }
        return hashDictionary;
    }

}
