package persistence;

// Some snippets of code from: TellerApp
//Inspired  by https://mkyong.com/java/json-simple-example-read-and-write-json/

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;


// A class representing a  writer that can write game  data to a file
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs a writer that will write data to file
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void write(String s) {
        printWriter.write(s);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    public void close() {
        printWriter.close();
    }

    //MODIFIES: this
    //EFFECTS: creates a JSON object and saves all into file writer
    public void save(GamePanel game) {
        JSONObject obj = new JSONObject();
        JSONArray listOfGameItemsPrice = new JSONArray();
        JSONArray listOfGameItemsStats = new JSONArray();
        JSONArray listOfGameItemsDescrip = new JSONArray();
        JSONArray listOfTextItemData = new JSONArray();
        addToJson(game, obj);
        for (InventoryItem i: game.inventory.getAllInventoryItems()) {
            listOfGameItemsPrice.add(i.getGameItem().getMarketPrice());
            listOfGameItemsStats.add(i.getGameItem().getStats());
            listOfGameItemsDescrip.add(i.getGameItem().getDescription());
        }
        obj.put("inventory_item_names", game.inventory.getAllInventoryItemGameItemNames());
        obj.put("inventory_item_price", listOfGameItemsPrice);
        obj.put("inventory_item_stats", listOfGameItemsStats);
        obj.put("inventory_item_description", listOfGameItemsDescrip);
        for (TextItem t: game.textCollection.getAllTextItems()) {
            listOfTextItemData.add(game.textCollection.getTextItemData(t));
        }
        obj.put("text_collection_item_data", listOfTextItemData);
        write(obj.toJSONString());
        close();
    }

    //MODIFIES: JSON object
    //EFFECTS: creates a JSONArray objects and adds to JSON
    public void addToJson(GamePanel game, JSONObject obj) {
        obj.put("character_name", game.character.getName());
        obj.put("character_attributes", game.character.getCharacterAttributes());
        obj.put("character_balance",game.character.getBalance());
        obj.put("character_class",game.character.getCharacterClass());
        obj.put("inventory_total_items", game.inventory.getInventoryTotalItems());
        JSONArray listOfUnclaimedTextItems = new JSONArray();
        JSONArray listOfUnclaimedGameItems = new JSONArray();
        JSONArray listOfNpcs = new JSONArray();
        for (TextItem i: game.gameObjects.getAllUnclaimedTextItems()) {
            listOfUnclaimedTextItems.add(game.gameObjects.getAllUnclaimedTextItemsData(i));
        }
        obj.put("gameObjects_unclaimedTextItems", listOfUnclaimedTextItems);
        for (GameItem i: game.gameObjects.getAllUnclaimedGameItems()) {
            listOfUnclaimedGameItems.add(game.gameObjects.getAllUnclaimedGameItemsData(i));
        }
        obj.put("gameObjects_unclaimedGameItems", listOfUnclaimedGameItems);
        for (NPC n: game.gameObjects.getAllNPCs()) {
            listOfNpcs.add(game.gameObjects.getAllNpcData(n));
        }
        obj.put("gameObjects_npc_data", listOfNpcs);
    }
}
