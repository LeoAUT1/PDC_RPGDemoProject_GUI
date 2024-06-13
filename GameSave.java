/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

/**
 *
 * @author Uni
 */
import pdcproject.*;
import java.io.*;
import javax.swing.JTextArea;

public class GameSave {

    public static void saveGame(Player player, Inventory inventory, int level, int playerProgress, JTextArea dialogueArea) {
    try {
        FileWriter fileWriter = new FileWriter("gameSave.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);

        //Player data
        writer.write(player.getPlayerName() + "\n");
        writer.write(player.getHP() + "\n");

        //Inventory
        //Figure out how to ACTUALLY get current inventory items
        String[] inventoryItems = {"[Wooden Sword]", "[Rusty Shield]",
            "[Health Potion]", "[Gold]", "[  ]", "[  ]", "[  ]",
            "[  ]", "[  ]"};
        for (String item : inventoryItems) {
            writer.write(item + "\n");
        }

        //Progress in the game
        writer.write(playerProgress + "\n");

        writer.close();

        dialogueArea.append("\nGame data has been saved.");
    } catch (IOException e) {
        dialogueArea.append("\nError while saving game: " + e.getMessage());
    } catch (java.lang.NullPointerException n) {
        dialogueArea.append("\nYou can only save/load after the first quest.");
    }
}

public static void loadGame(JTextArea dialogueArea) {
    try {
        FileReader fileReader = new FileReader("gameSave.txt");
        BufferedReader reader = new BufferedReader(fileReader);

        //Read Player data
        String playerName = reader.readLine();
        int hp = Integer.parseInt(reader.readLine());
        Player player = new Player(playerName, hp);

        //Read Inventory data
        String[] inventoryItems = new String[9];
        for (int i = 0; i < inventoryItems.length; i++) {
            inventoryItems[i] = reader.readLine();
        }

        //Progress in the game
        int playerProgress = Integer.parseInt(reader.readLine());

        reader.close();
        
        //Prints Player data
        dialogueArea.append("\nLoaded Player Data:");
        dialogueArea.append("\nName: " + player.getPlayerName());
        dialogueArea.append("\nHP: " + player.getHP());

        //Prints Inventory data
        dialogueArea.append("\nLoaded Inventory:");
        for (String item : inventoryItems) {
            dialogueArea.append("\n" + item);
        }
        
        //Print player progress and that it has been loaded
        dialogueArea.append("\nPlayer Progress: " + playerProgress);
        dialogueArea.append("\nGame data has been loaded.");
    } catch (IOException | NumberFormatException e) {
        dialogueArea.append("\nError while loading game: " + e.getMessage());
    } catch (NullPointerException n) {
        dialogueArea.append("\nYou can only save/load after the first quest.");
    }
}

}
