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
public class KoboldEnemy implements healthPoints, enemyDefeated
{
    private int hp = 25;
    private final int droppedEXP = 50;
    private final String droppedItems = "healthPotion";

    @Override
    public int getHP()
    {
        return hp;
    }

    @Override
    public int getDroppedEXP() 
    {
        return droppedEXP;
    }

    @Override
    public String getDroppedItems() 
    {
        return droppedItems;
    }

    @Override
    public void Damage(int dmg) 
    {
        hp -= dmg;
        if (hp < 0) 
        {
            hp = 0;
        }
    }

    @Override
    public boolean isAlive()
    {
        return hp > 0;
    }
}
