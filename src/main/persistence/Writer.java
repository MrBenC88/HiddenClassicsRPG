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
        addCharacterDataToJson(game, obj);
        addTextCollectionToJson(game, obj);
        addGameObjectsToJson(game, obj);
        addUnClaimedItemsToJson(game,obj);
        addUnclaimedTextCollectionToJson(game, obj);
        for (InventoryItem i: game.inventory.getAllInventoryItems()) {
            listOfGameItemsPrice.add(i.getGameItem().getMarketPrice());
            listOfGameItemsStats.add(i.getGameItem().getStats());
            listOfGameItemsDescrip.add(i.getGameItem().getDescription());
        }
        obj.put("inv_item_names", game.inventory.getAllInventoryItemGameItemNames());
        obj.put("inv_item_price", listOfGameItemsPrice);
        obj.put("inv_item_stats", listOfGameItemsStats);
        obj.put("inv_item_des", listOfGameItemsDescrip);

        write(obj.toJSONString());
        close();
    }

    //MODIFIES: JSON Object
    //EFFECTS: creates a JSONArray object and adds the game objects unclaimed game item data to JSON
    public void addUnClaimedItemsToJson(GamePanel game, JSONObject obj) {
        JSONArray listOfUnclaimedGameItemsNames = new JSONArray();
        JSONArray listOfUnclaimedGameItemsPrice = new JSONArray();
        JSONArray listOfUnclaimedGameItemsStats = new JSONArray();
        JSONArray listOfUnclaimedGameItemsDescrip = new JSONArray();
        for (GameItem i: game.gameObjects.getAllUnclaimedGameItems()) {
            listOfUnclaimedGameItemsNames.add(i.getGameItemName());
            listOfUnclaimedGameItemsPrice.add(i.getMarketPrice());
            listOfUnclaimedGameItemsStats.add(i.getStats());
            listOfUnclaimedGameItemsDescrip.add(i.getDescription());
        }
        obj.put("uncl_i_name",listOfUnclaimedGameItemsNames);
        obj.put("uncl_i_pri",listOfUnclaimedGameItemsPrice);
        obj.put("uncl_i_s",listOfUnclaimedGameItemsStats);
        obj.put("uncl_i_desc",listOfUnclaimedGameItemsDescrip);
    }

    //MODIFIES: JSON Object
    //EFFECTS: creates a JSONArray object and adds the game objects (NPCs) data to JSON
    public void addGameObjectsToJson(GamePanel game, JSONObject obj) {
        JSONArray listOfNpcsLines = new JSONArray();
        JSONArray listOfNpcsTitles = new JSONArray();
        JSONArray listOfNpcsDirections = new JSONArray();
        JSONArray listOfNpcsTypes = new JSONArray();
        JSONArray listOfNpcsNames = new JSONArray();
        JSONArray listOfNpcsStats = new JSONArray();
        for (NPC n : game.gameObjects.getAllNPCs()) {
            listOfNpcsLines.add(game.gameObjects.getListOfNpcLines(n));
            listOfNpcsTitles.add(game.gameObjects.getNpcTitle(n));
            listOfNpcsDirections.add(game.gameObjects.getNpcDirection(n));
            listOfNpcsTypes.add(game.gameObjects.getNpcType(n));
            listOfNpcsNames.add(game.gameObjects.getNpcName(n));
            listOfNpcsStats.add(game.gameObjects.getNpcStat(n));
        }
        obj.put("npclines", listOfNpcsLines);
        obj.put("npctitles", listOfNpcsTitles);
        obj.put("npcdirs", listOfNpcsDirections);
        obj.put("npctypes", listOfNpcsTypes);
        obj.put("npcnames", listOfNpcsNames);
        obj.put("npcstat", listOfNpcsStats);
    }

    //MODIFIES: JSON Object
    //EFFECTS: creates a JSONArray object and adds the unclaimed text collection data to JSON
    public void addUnclaimedTextCollectionToJson(GamePanel game, JSONObject obj) {
        JSONArray listOfUnclaimedTextItemName = new JSONArray();
        JSONArray listOfUnclaimedTextItemContent = new JSONArray();
        JSONArray listOfUnclaimedTextItemBookId = new JSONArray();
        for (TextItem t: game.gameObjects.getAllUnclaimedTextItems()) {
            listOfUnclaimedTextItemName.add(game.gameObjects.getUnclaimedTextItemName(t));
            listOfUnclaimedTextItemContent.add(game.gameObjects.getUnclaimedTextItemContent(t));
            listOfUnclaimedTextItemBookId.add(game.gameObjects.getUnclaimedTextItemBookId(t));
        }
        obj.put("uncl_ti_name", listOfUnclaimedTextItemName);
        obj.put("uncl_ti_content", listOfUnclaimedTextItemContent);
        obj.put("uncl_ti_bookId", listOfUnclaimedTextItemBookId);
    }


    //MODIFIES: JSON Object
    //EFFECTS: creates a JSONArray object and adds the text collection data to JSON
    public void addTextCollectionToJson(GamePanel game, JSONObject obj) {
        JSONArray listOfTextItemName = new JSONArray();
        JSONArray listOfTextItemContent = new JSONArray();
        JSONArray listOfTextItemBookId = new JSONArray();
        for (TextItem t: game.textCollection.getAllTextItems()) {
            listOfTextItemName.add(game.textCollection.getTextName(t));
            listOfTextItemContent.add(game.textCollection.getTextContent(t));
            listOfTextItemBookId.add(game.textCollection.getBookID(t));
        }
        obj.put("text_item_name", listOfTextItemName);
        obj.put("text_item_cont", listOfTextItemContent);
        obj.put("text_item_bookId", listOfTextItemBookId);
    }

    //MODIFIES: JSON object
    //EFFECTS: creates a JSONArray objects and adds character data to JSON
    public void addCharacterDataToJson(GamePanel game, JSONObject obj) {
        obj.put("char_name", game.character.getName());
        obj.put("char_attributes", game.character.getCharacterAttributes());
        obj.put("char_balance",game.character.getBalance());
        obj.put("char_class",game.character.getCharacterClass());
        obj.put("inv_total_items", game.inventory.getInventoryTotalItems());
    }
}
