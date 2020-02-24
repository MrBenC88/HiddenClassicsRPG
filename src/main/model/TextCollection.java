package model;

import java.util.ArrayList;

//A Class representing the user's collection of texts (TextItem) which is a list of texts.
// This class also keeps track of the total texts the user has, and the max number of texts in the collection
public class TextCollection {
    private ArrayList<TextItem> textItems;
    private int totalTextItems;
    private boolean fullTextInventory;
    private int maxTextSlots = 5;

    //EFFECTS: Constructs the empty Text Collection with 0 text items
    public TextCollection() {
        textItems = new ArrayList<>(maxTextSlots);
        totalTextItems = 0;
    }

    //EFFECTS: Constructs a Text Collection with text items based on parameters
    // initializes the textItems array list and adds the retrieved game data of text items to the array list
    public TextCollection(ArrayList<String> textName, ArrayList<String> textContent, ArrayList<String> textBookId) {
        textItems = new ArrayList<>(maxTextSlots);
        int counter = 0;
        for (int i = 0; i < textName.size(); i++) {
            counter++;
            textItems.add(new TextItem(textName.get(i), textContent.get(i), textBookId.get(i)));
        }
        totalTextItems = counter;
    }

    //REQUIRES: quantity > 0
    //MODIFIES: TextItem, this
    //EFFECTS: adds a text item to the text collection if the text inventory is not full
    public void addTextItem(TextItem item) {
        if (textItems.size() < maxTextSlots) {
            fullTextInventory = false;
        } else {
            fullTextInventory = true;
        }
        if (!fullTextInventory & textItems.size() < maxTextSlots) {
            textItems.add(item);
            totalTextItems++;
        }
    }

    /*
    //REQUIRES: quantity > 0
    //MODIFIES: TextItem, this
    //EFFECTS: removes the TextItem from the TextCollection
    public void removeTextItem(TextItem item) {
        textItems.remove(item);
        totalTextItems--;
    } */

    //EFFECTS: return the total text collection items
    public int getTextCollectionTotalTexts() {
        return totalTextItems;
    }

    //EFFECTS: returns the array containing all the text items in the text collection
    public ArrayList<TextItem> getAllTextItems() {
        return textItems;
    }

    //EFFECTS: returns a list of all the names of the text items in the text collection
    public ArrayList<String> getAllTextItemNames() {
        ArrayList<String> listOfString = new ArrayList<>();
        for (TextItem i : textItems) {
            listOfString.add(i.getTextItemName());
        }
        return listOfString;
    }

    //EFFECTS: returns the text item name of specified text item
    public String getTextName(TextItem i) {
        return i.getTextItemName();
    }

    //EFFECTS: returns the text content of specified text item
    public String getTextContent(TextItem i) {
        return i.getTextItemContent();
    }

    //EFFECTS: returns the text book id of specified text item
    public String getBookID(TextItem i) {
        return i.getBookID();
    }

    /*
    //EFFECTS: returns an arraylist of the text item's data
    public ArrayList<String> getTextItemData(TextItem item) {
        ArrayList<String> listOfString = new ArrayList<>();
        listOfString.add(item.getTextItemName());
        listOfString.add(item.getTextItemContent());
        listOfString.add(item.getBookID());
        return listOfString;
    }*/
}
