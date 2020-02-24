package model;

import model.GamePanel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static persistence.Reader.readData;

public class ReaderTest {
    private Reader reader;

    @BeforeEach
    void runBefore() {
        try {
            reader = new Reader(new File("./data/gamedatatest.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testConstructor() {
        assertTrue(reader instanceof Reader);
    }

    @Test
    void testReadData() throws FileNotFoundException {
        try {
            FileReader reader = new FileReader("./data/gamedatatest.json");
            JSONObject jsonObj = readData(reader);
            assertEquals("[]", jsonObj.get("npcstat").toString());
            assertEquals("[[3,1,0,1],[2,2,2,0],[1,1,2,2],[2,2,3,12]," +
                    "[2,2,3,12],[2,2,3,12],[2,2,3,12],[2,2,3,12]]", jsonObj.get("uncl_i_s").toString());
            assertEquals("{\"Speed\":0,\"Health\":0,\"Attack\":0,\"Defense\":0}",
                    jsonObj.get("char_attributes").toString());
            assertEquals("[\"Golden Apple\",\"Golden Pear\",\"Golden Strawberry\",\"Golden ?\"," +
                    "\"Golden ??\",\"Golden ???\",\"Golden ????\",\"Golden ???????\"]", jsonObj.get("uncl_i_name").
                    toString());
            assertEquals(0.0, jsonObj.get("char_balance"));
            assertEquals("0", jsonObj.get("inv_total_items").toString());
            assertEquals("[]", jsonObj.get("npcdirs").toString());

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
