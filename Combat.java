/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

import pdcproject.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Uni
 */
public class Combat 
{
    private final Player player;
    private final KoboldEnemy kobold;
    private final Scanner scan = new Scanner(System.in);
    private final Random rand;

    public Combat(Player player, KoboldEnemy kobold) 
    {
        this.rand = new Random();
        this.player = player;
        this.kobold = kobold;
    }

    public void playerCombat()
    {
        System.out.format("A kobold has appeared!");
        pause(3);

        while (player.isAlive() && kobold.isAlive()) 
        {
            battleScreen();

            try 
            {
                int battle = scan.nextInt();

                switch (battle) 
                {
                    case 1:
                        playerAttack();
                        pause(2);
                        if (kobold.isAlive()) 
                        {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    case 2:
                        checkStats();
                        pause(2);
                        if (kobold.isAlive()) 
                        {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    case 3:
                        //call inventory class
                        break;
                    case 4:
                        if (playerEscape()) 
                        {
                            return;
                        }
                        if (kobold.isAlive()) 
                        {
                            koboldAttack();
                            pause(2);
                        }
                        break;
                    default:
                        System.out.println("INCORRECT INPUT");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("PLEASE ENTER A NUMBER(1-4)");
                pause(2);
                scan.next();
            }

            if (!kobold.isAlive()) 
            {
                System.out.println("YOU WON! YOU GOT 50 EXP!");
                player.gainEXP(kobold.getDroppedEXP());
                System.out.print("YOU GOT A " + kobold.getDroppedItems() + "!\n\n\n\n");
                pause(3);
            }
        }
    }

    public void battleScreen()
    {
        System.out.println("\nWHAT DO YOU WANT TO DO!");
        System.out.println("[1]FIGHT\n[2]CHECK\n[3]INV\n[4]RUN");
    }

    public void playerAttack() 
    {
        int dmg = 5;
        int critChance = rand.nextInt(100);

        if (critChance < 5) 
        {
            dmg *= 2;
            kobold.Damage(dmg);
            System.out.println("CRITICAL HIT! you dealt " + dmg + " damage!");
        } 
        else 
        {
            kobold.Damage(dmg);
            System.out.println("You dealt " + dmg + " damage!");
        }
    }

    public void koboldAttack()
    {
        int dmg = 3;
        int critChance = rand.nextInt(100);

        if (critChance < 5) 
        {
            dmg *= 2;
            player.Damage(dmg);
            System.out.println("CRITICAL HIT! The kobold dealt " + dmg + " damage!");
        } 
        else 
        {
            player.Damage(dmg);
            System.out.println("The kobold dealt " + dmg + " damage!");
        }
    }

    public void checkStats() 
    {
        System.out.println("[1]CHECK YOUR HP\n[2]CHECK ENEMY HP");
        int check = scan.nextInt();

        if (check == 1) 
        {
            System.out.println("YOU HAVE: " + player.getHP() + "/30 HP");
        }
        else if (check == 2)
        {
            System.out.println("THE KOBOLD HAS: " + kobold.getHP() + "/25 HP");
        }
    }

    public boolean playerEscape() 
    {
        if (player.getPlayerLevel() == 1) 
        {
            System.out.println("YOU MUSN'T ESCAPE!");
            return false;
        }
        return false;
    }

    public void pause(int seconds)
    {
        try 
        {
            Thread.sleep(seconds * 1000);
        } 
        catch (InterruptedException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
