# Hidden Classics Pavilion RPG

## A text-based RPG (Role-playing Game)

**Project Overview:**
*Hidden Classics Pavilion*, is a simple role-playing game with adventure elements. 
The basic mechanics of the game involves the navigation of a player through a map with the goal of collecting a variety of remnant texts.
Obstacles are in the way blocking the user's end goal as the user will have to deal with challengers along their journey. 
Once the user collects all the texts, the user learns the truth of the world.


**Application Usage:**
- The application will allow a user to explore a storyline with relative freedom in the selection of options.
- The application is at first text-based which will then (hopefully) be converted to a 3D overhead and open-world style movement for the player.
- It is meant to allow users to enjoy and experience an role play adventure.
- It will allow the user to interact with their character stats, inventory, and other characters.
- Users will have to cope with challengers along the way which involve a battle sequence.
- Users will be able to learn the truth of functional programming.

**Target User(s):**
- Idle individuals
- RPG enthusiasts 
- The handsome TA marking this

**Why this project:**
This project was chosen because of the interest in creating a functional RPG using object oriented programming. The goal is to surpass a simple text-based project with more interesting and involved mechanics for the end user.


## User Stories

**Phase 1**:*Menu Setup, Character Setup, Inventory*
- As the user, I want to be able to create a character with a specific name and choose a character class. [completed]
- As the user, I want to be able to obtain a starter item and have it automatically added into my inventory. [completed]
- As the user, I want to be able to be notified of the starter item I have chosen and to have it automatically added into my inventory. [completed]
- As the user, I want to be able to select an item in my inventory and be able to view its name, description, and worth. [completed]
- As the user, I want to be able to choose what to do with the item that I selected in the inventory (use, discard, view different item). [completed]
- As the user, I want to be able to see the item selected used, discarded, or to view a different item. [completed]
- As the user, I want to be able to view my stats which shows my character name, class, balance, stats, inventory items, total inventory items. [completed]

**Phase 2**:*Text Collection (Inventory for Texts), NPC, Battle Phase, Persistence*
- As the user, I want to be able to view the in game shop and my balance. [completed] 
- As the user, I want to be able to make purchases. [completed] 
- As the user, I want to be able to view the text's name and description [completed] 
- As the user, I want to be able to save my game data! (In a JSON!!!) [completed] 
- As the user, I want to be able to load my game data! (From a JSON!!!)  [completed] 

**Phase 3**:*GUI*
- Added user GUI. [completed] 
- Referenced all third party assets. [completed] 
- Added Section for "Instructions for Grader" [completed] 
- Added Specifications to all methods [completed] 

TODO
- Finish Game element portion / write tests if necessary


**References for Sources Utilized in Phase 3**
- MainMenu Background Image Asset: https://www.pinterest.ca/pin/10555380362736490/
- Character Stat Page Image Asset: https://imgur.com/t/fate_grand_order/j0I7o
- Inventory Page Image Asset: http://commons.nicovideo.jp/material/nc172745
- Character Creation Page Image Asset: https://chatsticker.com/sticker/punipuni-animated-1
- Main Menu Music: No. 51: Sword Art Online - Gracefully (wav file) 
- InGame Music: Radio Route 101 - Pokémon HeartGold/SoulSilver (wav file)
- InStore Music:  Goldenrod City - Pokémon HeartGold/SoulSilver (wav file)

## Instructions for Grader
**On StartUp**:

To start the game, run the Main.java file

The main menu will immediately display an audio and visual component.
Music can be heard loading directly onto the main menu.
On the main menu, there are three options.

1. New Game - Start a new game with completely new data
2. Load Game - Load a previous existing data file containing old game data
3. Quit - Quit the game.

Instructions: Initial Setup:
- Select New Game.
- Enter Name by typing in the "Name" field.
- Select the class "Wanderer" which will ensure you will have sufficient funds for testing.
- Click Submit.

Generating required events related to user story "Adding X to Y"
- A player can add to their inventory by purchasing an item in the Store, then viewing it in their inventory.
- The following steps will detail how to do so.
- Click on Inventory.
- There are four buttons each having an event meant to satisfy the two required events that are related to the required user story

You can generate the first required event by...
- Select an item with the mouse and press enter or the button "View Details" to see item details
- Select an item and click the button "Use Item" to add item stats to your character stats.

You can generate the second required event by...
- Select an item and click the button "Discard" to earn 25 gold and discard the item
- It is recommended to Discard the remaining items and start with an empty inventory. 
- When finished, Click Done.

Generating the required user story "add an X to a Y"
- Click Store
- Check that the Current Balance is >= 50.
- Click the Button "Yes"
- Select an item and press enter to view the item details.
- Select an item and then press the button "Submit" which will show a prompt that the item has been added to your inventory
- Select "Done" to go back to the in game menu
- Click Inventory
- Click the button "Refresh" to update the list and display the brand new item that you have added to your inventory.
- To further confirm, click "Done" to go back to the in game menu.
- Click on "Character Stats" 
- Click "Refresh" to see the updated character attributes and items they have.

You can locate my visual component by...
- The main menu contains the audio component which plays upon starting the application
- Each new screen has a background.

You can save the state of my application by...
- To save, navigate back to the in game menu.
- Click on "Save".
- Exit the application.

You can reload the state of my application by...
- Re-run the application from Main.java
- Click on Load Game from the main menu which displays upon opening the application
- Click Character Attributes to see your character's updated attributes. 
- Troubleshooting Note: If at any point a component is not updating, be sure to click the "Refresh" button


