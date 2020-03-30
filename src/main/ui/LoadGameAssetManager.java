package ui;

import javafx.stage.Stage;
import model.GamePanel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static persistence.Reader.readData;

/**
 * The NewGameAssetManager class represents a manager of all assets upon the creation of a new game or
 * the loading of game data in a previously existing game.
 */
public class LoadGameAssetManager {
    private String gameFile = "";

    //EFFECTS: creates / constructs the load game asset manager object
    public LoadGameAssetManager(String gameFile) {
        this.gameFile = gameFile;
    }


    //MODIFIES: GamePanel game
    //EFFECTS: adds game items to game Panel
    public void addAssetsToGame(GamePanel game) {
        game.addUnclaimedItems();
        game.addUnclaimedTexts();
        game.addNpcs();
        //Hardcoded this for testing purposes
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(0));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(1));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(2));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(3));
        game.textCollection.addTextItem(game.gameObjects.getUnClaimedTextItem(4));

        //Added Some Inventory Items to Inventory
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(0), 2);
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(2), 3);
        game.inventory.addInventoryItem(game.gameObjects.getUnClaimedGameItem(1), 1);
    }


    //MODIFIES: GamePanel game
    //EFFECTS: loads game data from GAME_FILE, if the file exists
    public GamePanel loadGameData() throws IOException, ParseException {

        FileReader reader = new FileReader(gameFile);
        JSONObject jsonObj = readData(reader);
        GamePanel game = new GamePanel((String) jsonObj.get("char_name"), (double) jsonObj.get("char_balance"),
                (HashMap<String, Long>) jsonObj.get("char_attributes"), (String) jsonObj.get("char_class"),
                (ArrayList<String>) jsonObj.get("inv_item_names"), (ArrayList<Double>) jsonObj.get("inv_item_price"),
                (ArrayList<ArrayList<Long>>) jsonObj.get("inv_item_stats"),
                (ArrayList<String>) jsonObj.get("inv_item_des"), (ArrayList<String>) jsonObj.get("uncl_i_name"),
                (ArrayList<Double>) jsonObj.get("uncl_i_pri"), (ArrayList<ArrayList<Long>>) jsonObj.get("uncl_i_s"),
                (ArrayList<String>) jsonObj.get("uncl_i_desc"),
                (ArrayList<ArrayList<String>>) jsonObj.get("npclines"),
                (ArrayList<String>) jsonObj.get("npctitles"), (ArrayList<String>) jsonObj.get("npcdirs"),
                (ArrayList<String>) jsonObj.get("npctypes"), (ArrayList<String>) jsonObj.get("npcnames"),
                (ArrayList<HashMap<String, Long>>) jsonObj.get("npcstat"),
                (ArrayList<String>) jsonObj.get("uncl_ti_name"), (ArrayList<String>) jsonObj.get("uncl_ti_content"),
                (ArrayList<String>) jsonObj.get("uncl_ti_bookId"),
                (ArrayList<String>) jsonObj.get("text_item_name"), (ArrayList<String>) jsonObj.get("text_item_cont"),
                (ArrayList<String>) jsonObj.get("text_item_bookId"));
        return game;
    }

}
