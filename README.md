# Hidden Classics Pavilion RPG

## A text-based RPG (Role-playing Game)

*Demo:* https://youtu.be/IRPFjHanaxQ

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
- Added Simplified Text Based Game when exiting in game menu [completed]
- Convert Text Based Game to Actual Game - Finish Game element portion [Completed]
- Added NPC Battle Event by walking up to futuristic experiment building and pressing enter or clicking button [completed]

**Phase 4**:*Design*

Phase 4: Task 2 - Map Interface: 

[Make appropriate use of the Map interface somewhere in your code]  
The HashMap is used in the UserCharacter class within the field: HashMap<String, Integer> characterAttributes. Utilized in the constructor and in method which adjust the character attributes.  [completed] 

Phase 4: Task 3 - Changelog / Refactoring 

[Improving Cohesion #1] 
Within the GameGUI class, it did not follow the 1 class, 1 responsibility principle. 
The GameGUI class previously handled the entire GameGUI scene, the event handlers, all the buttons, the logic behind the camera, the sprites, and the game loop.
It is apparent that the class had too many responsibilities. Thus, to make the class have better cohesion, we improve the design by creating a new class.

[Solution #1] A new GameTools class was created which contains the following:
All methods related to the camera.
All related sprite methods.
All methods related to the player's location relative to the map.
The actual game loop. 
This narrows down the GameGUI class responsibilities a it now only handles the GUI elements (ie. buttons/ event handlers / text that displays on screen , mainLayout of the GUI).

[Improving Cohesion #2] 
Within the MasterFrame class, poor cohesion can be observed. This poor cohesion is because the MasterFrame fails to follow the 1 class, 1 responsibility principle.
The MasterFrame previously was responsible for all of the components of the MasterFrame GUI such as buttons / event listeners / as well as saving + loading data.
The MasterFrame had too much responsibility as the tasks of loading in new Game Assets or loading previously existing Game Assets shouldn't be handled by the MasterFrame class.
Thus, to make this class have better cohesion, a new class is created.

[Solution #2] 
A new LoadGameAssetManager class was created which contains the following:
The Addition of Game Assets added to the game upon the creation of a new Game.
The Loading of All Game Data upon the selection of "Load Game" based on previously existing game data.
This allows the MasterFrame to focus solely on the UI elements. The actual logic behind loading and adding in new Game Data is dealt behind the scenes by the LoadGameAssetManager class.
The cohesion of the MasterFrame can thus, be improved.



**References for Sources Utilized in Phase 3**
- MainMenu Background Image Asset: https://www.pinterest.ca/pin/10555380362736490/
- Character Stat Page Image Asset: https://imgur.com/t/fate_grand_order/j0I7o
- Inventory Page Image Asset: http://commons.nicovideo.jp/material/nc172745
- Character Creation Page Image Asset: https://chatsticker.com/sticker/punipuni-animated-1
- Main Menu Music: No. 51: Sword Art Online - Gracefully (wav file) 
- InGame Music: Radio Route 101 - Pokémon HeartGold/SoulSilver (wav file)
- InStore Music:  Goldenrod City - Pokémon HeartGold/SoulSilver (wav file)
- In Game Menu Music: Nintendo Wii - Mii Channel Theme (wav file)
- Character Sprites: https://www.spriters-resource.com/ds_dsi/pokemonheartgoldsoulsilver/sheet/26778/
- TileSheet: https://github.com/blackravendb/Project-Pokemon/blob/master/Project-Pokemon/res/world/tileset32x32.png

