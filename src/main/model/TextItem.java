package model;

import java.util.ArrayList;

// A class that represents a text item which is within the text collection (user owns this)
// Each TextItem has a name, the content, and a book id
public class TextItem {
    private String name;
    private String content;
    private String bookId;


    //EFFECTS: constructs the TextItem with name, description, and ability
    public TextItem(String name, String content, String bookId) {
        this.name = name;
        this.content = content;
        this.bookId = bookId;

    }

    //EFFECTS: gets the text item description
    public String getTextItemContent() {
        return content;
    }

    //EFFECTS: gets the text item name
    public String getTextItemName() {
        return name;
    }

    //EFFECTS: gets the text item book id
    public String getBookID() {
        return bookId;
    }

}
