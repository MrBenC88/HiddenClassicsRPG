package ui;

import javafx.application.Application;

import java.io.IOException;

//Main class for a single entry point into the program
public class Main {
    public static void main(String[] args) throws IOException {
        //new HiddenClassicsPavilionRPG(); // This is for the text console version
        Application.launch(MasterFrame.class, args); // This is for actual GUI using JavaFX
    }
}
