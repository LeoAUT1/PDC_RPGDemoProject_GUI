/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

import pdcproject.*;

/**
 *
 * @author Uni
 */
public class Level implements experiencePoints
{
    private int level;
    private int currentEXP;

    public Level()
    {
        this.currentEXP = 0;
        this.level = 1;
    }

    @Override
    public int getCurrentEXP()
    {
        return currentEXP;
    }

    @Override
    public int getLevel() 
    {
        return level;
    }

    public void levelUp()
    {
        level++;
        System.out.println("You are now level " + level + "!");
    }

    @Override
    public void gainEXP(int EXP) 
    {
        currentEXP += EXP;

        while (currentEXP >= levelReq()) 
        {
            levelUp();
        }
    }

    public int levelReq() 
    {
        return level * 50;
    }
}
