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

public class GameSave {

    public static void saveGame(Player player, Inventory inventory, int level, int playerProgress/*, boolean questStarted*/) {
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

            System.out.println("Game data has been saved.");
        } catch (IOException e) {
            e.printStackTrace(); //Better shows errors popping up in the console for easier viewing
        } catch (java.lang.NullPointerException n) {
            System.out.println("You can only save/load after the first quest.");
        }
    }

    //Figure out how to PROPERLY load the saved file
    public static void loadGame() {
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
            System.out.println("Loaded Player Data:");
            System.out.println("Name: " + player.getPlayerName());
            System.out.println("HP: " + player.getHP());

            //Prints Inventory data
            System.out.println("Loaded Inventory:");
            for (String item : inventoryItems) {
                System.out.println(item);
            }
            
            //Print player progress and that it has been loaded
            System.out.println("Player Progress: " + playerProgress);
            System.out.println("Game data has been loaded.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); //For more in depth analysis for debugging
        } catch (NullPointerException n) {
            System.out.println("You can only save/load after the first quest.");
        }
    }

}
