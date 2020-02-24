package persistence;

//Inspired by https://mkyong.com/java/json-simple-example-read-and-write-json/

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read game data from a file
public class Reader {

    public Reader() {
    }

    //Returns the json object read from file
    public static JSONObject readData(FileReader reader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        return jsonObject;
    }

}
