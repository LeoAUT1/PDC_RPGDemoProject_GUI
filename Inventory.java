/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

import pdcproject.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author vcs4803
 */
/**
 * TODO:_______________________________________________________________________
 * Figure out how to incorporate the other classes (Player, Shop) to relate to
 * inventory.__________________________________________________________________
 * Have the GameSave class save all inventory info for the specific save.______
 * Fine tune the addItem function______________________________________________
 */
public class Inventory implements Serializable {

    private String[] inventory;
    private int gold;
    private JTextArea inventoryDisplay;
    private JTextField userInput;

    public Inventory(JTextArea inventoryDisplay, JTextField userInput) {
        this.inventoryDisplay = inventoryDisplay;
        this.userInput = userInput;
        this.inventory = new String[]{"[Wooden Sword]", "[Rusty Shield]", "[Health Potion]", "[Gold]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"};
        this.gold = 20;
    }

    public void showInventory() {
        StringBuilder inventoryText = new StringBuilder();
        for (String item : inventory) {
            inventoryText.append(item);
        }
        inventoryDisplay.setText(inventoryText.toString());
    }

    public void equipItem() {
        try {
            int equipped = Integer.parseInt(userInput.getText()) - 1;
            inventoryDisplay.append("\nCurrently Equipped: " + inventory[equipped]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            inventoryDisplay.append("\nInvalid input, select an item within your inventory.");
        }
    }

    static void addItem(String[] inventory, String itemSpace, String item, int amount) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals("[" + item + "]")) {
                if (amount <= 1) {
                    inventory[i] = "[" + item + "]";
                    return;
                }
                inventory[i] = "[" + item + "(" + amount + ")" + "]";
                return;
            }
            if (inventory[i].equals("[  ]")) {
                inventory[i] = "[" + item + "]";
                return;
            }
        }
    }

    static void addGold(String[] inventory, String goldSpace, int gold) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals("[Gold]")) {
                inventory[i] = "[Gold" + "(" + gold + ")" + "]";
                return;
            }
        }
    }

    static void useItem(String[] inventory, String item, int currentAmount, int usage) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals("[" + item + "(" + currentAmount + ")" + "]")) {
                currentAmount -= usage;
                if (currentAmount < 2) {
                    if (currentAmount < 1) {
                        inventory[i] = "[  ]";
                        return;
                    }
                    inventory[i] = "[" + item + "]";
                    return;
                }
                inventory[i] = "[" + item + "(" + currentAmount + ")" + "]";
                return;
            }
        }
    }
}

