package ade.myanimelist.anime.database.repository;

import ade.myanimelist.anime.database.entity.User;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigRepositoryImpl implements ConfigRepository {
    @Override
    public void writeConfig(User user) {
        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("current_id_login", user.getId());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter config = new FileWriter("config.json")) {
            gson.toJson(jsonData, config);
            System.out.println("suskes menuliskan data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCurrentUserId() {
        int userId = -1;
        Gson gson = new Gson();
        try (FileReader config = new FileReader("config.json")) {
            JsonElement jsonElement = JsonParser.parseReader(config);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            userId = jsonObject.get("current_id_login").getAsInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
