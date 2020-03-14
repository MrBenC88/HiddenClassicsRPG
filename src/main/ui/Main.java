package ui;

import javafx.application.Application;

import java.io.IOException;

//Main class for a single entry point into the program
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Main Method Running");
        //new HiddenClassicsPavilionRPG();
        Application.launch(MasterFrame.class, args);
    }
}
