/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

import pdcproject.*;
import java.util.InputMismatchException;
import java.util.Random;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Uni
 */
public class Combat {

    private final Player player;
    private final KoboldEnemy kobold;
    private final Random rand;
    private final JTextArea dialogueArea;
    private final JTextField userInput;

    public Combat(Player player, KoboldEnemy kobold, JTextArea dialogueArea, JTextField userInput) {
        this.rand = new Random();
        this.player = player;
        this.kobold = kobold;
        this.dialogueArea = dialogueArea;
        this.userInput = userInput;
    }

    public void playerCombat() {
        dialogueArea.append("A kobold has appeared!\n");
        pause(3);

        while (player.isAlive() && kobold.isAlive()) {
            battleScreen();

            try {
                String userInputText = userInput.getText();
                if (userInputText.isEmpty()) {
                    dialogueArea.append("\nPLEASE ENTER A NUMBER(1-4)");
                    pause(2);
                    continue;
                }

                int battle = Integer.parseInt(userInputText);

                switch (battle) {
                    case 1:
                        playerAttack();
                        pause(2);
                        if (kobold.isAlive()) {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    case 2:
                        checkStats();
                        pause(2);
                        if (kobold.isAlive()) {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    case 3:
                        //call inventory class
                        break;
                    case 4:
                        if (playerEscape()) {
                            return;
                        }
                        if (kobold.isAlive()) {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    default:
                        dialogueArea.append("INCORRECT INPUT");
                }
            } catch (NumberFormatException e) {
                dialogueArea.append("\nPLEASE ENTER A NUMBER(1-4)");
                pause(2);
            }

            if (!kobold.isAlive()) {
                dialogueArea.append("YOU WON! YOU GOT 50 EXP!");
                player.gainEXP(kobold.getDroppedEXP());
                dialogueArea.append("YOU GOT A " + kobold.getDroppedItems() + "!\n\n\n\n");
                pause(3);
            }
        }
    }

    public void battleScreen() {
        dialogueArea.append("\nWHAT DO YOU WANT TO DO!");
        dialogueArea.append("[1]FIGHT\n[2]CHECK\n[3]INV\n[4]RUN");
    }

    public void playerAttack() {
        int dmg = 5;
        int critChance = rand.nextInt(100);

        if (critChance < 5) {
            dmg *= 2;
            kobold.Damage(dmg);
            dialogueArea.append("CRITICAL HIT! you dealt " + dmg + " damage!");
        } else {
            kobold.Damage(dmg);
            dialogueArea.append("You dealt " + dmg + " damage!");
        }
    }

    public void koboldAttack() {
        int dmg = 3;
        int critChance = rand.nextInt(100);

        if (critChance < 5) {
            dmg *= 2;
            player.Damage(dmg);
            dialogueArea.append("CRITICAL HIT! The kobold dealt " + dmg + " damage!");
        } else {
            player.Damage(dmg);
            dialogueArea.append("The kobold dealt " + dmg + " damage!");
        }
    }

    public void checkStats() {
        dialogueArea.append("\n[1]CHECK YOUR HP\n[2]CHECK ENEMY HP");
        try {
            int check = Integer.parseInt(userInput.getText());

            if (check == 1) {
                dialogueArea.append("\nYOU HAVE: " + player.getHP() + "/30 HP");
            } else if (check == 2) {
                dialogueArea.append("\nTHE KOBOLD HAS: " + kobold.getHP() + "/25 HP");
            }
        } catch (NumberFormatException e) {
            dialogueArea.append("\nPLEASE ENTER A NUMBER(1-2)");
            pause(2);
        }
    }

    public boolean playerEscape() {
        if (player.getPlayerLevel() == 1) {
            dialogueArea.append("YOU MUSN'T ESCAPE!");
            return false;
        }
        return false;
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            dialogueArea.append(e.getLocalizedMessage());
        }
    }
}
