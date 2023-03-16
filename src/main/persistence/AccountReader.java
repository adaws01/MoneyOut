package persistence;

import model.Account;
import model.moneyoutprimitives.Location;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AccountReader {
    private static String source;

    // EFFECTS: constructs reader to read from source file
    public AccountReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static Account readAccount() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseAccount(jsonObject);
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private static void parseAccount(JSONObject jsonObject) {
        Location address = openLocation(jsonObject);
        double balance = jsonObject.getDouble("balance");
        String name = jsonObject.getString("name");
        Account.accessAccount().setName(name);
        Account.accessAccount().setBalance(balance);
        Account.accessAccount().setAddress(address);
    }

    private static Location openLocation(JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject.getJSONObject("address");
        return getLocation(jsonObject2);
    }

    private static Location getLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int distanceFromHome = jsonObject.getInt("distanceFromHome");
        return new Location(name, district, distanceFromHome);
    }
}
