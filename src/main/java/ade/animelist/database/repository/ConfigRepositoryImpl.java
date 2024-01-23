package ade.animelist.database.repository;

import ade.animelist.database.entity.User;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * class untuk melakukan write config dalam aplikasi
 */
public class ConfigRepositoryImpl implements ConfigRepository{
    /**
     * Method untuk menulis config
     * @param user => user that currently logs in
     */
    @Override
    public void writeConfig(User user) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("id", user.getId());
        jsonData.put("username", user.getUsername());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter config = new FileWriter("config.json")) {
            gson.toJson(jsonData, config);
            System.out.println("200 OK");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method untuk mendapatkan user id sekarang yang login
     * @return int (id user)
     */
    @Override
    public int getCurrentUserId() {
        int userId = -1;
        Gson gson = new Gson();
        try (FileReader config = new FileReader("config.json")) {
            JsonElement jsonElement = JsonParser.parseReader(config);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            userId = jsonObject.get("id").getAsInt();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return userId;
    }

    /**
     * Method untuk mendapatkan username sekarang yang login
     * @return username user
     */
    @Override
    public String getCurrentUsername() {
        String username = null;
        Gson gson = new Gson();
        try (FileReader config = new FileReader("config.json")) {
            JsonElement jsonElement = JsonParser.parseReader(config);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            username = jsonObject.get("username").getAsString();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return username;
    }
}
