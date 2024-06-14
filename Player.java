/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;


/**
 *
 * @author Uni
 */
public class Player extends Level implements healthPoints {

    private String playerName;
    private int hp = 30;
    private final Level playerLevel;

    //player stats
    public Player(String playerName, int hp) {
        this.playerName = playerName;
        this.hp = hp;
        this.playerLevel = new Level();
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getHP() {
        return hp;
    }

    public int getPlayerLevel() {
        if (playerLevel != null) {
            return playerLevel.getLevel();
        } else {
            return 0;
        }
    }

    @Override
    public void Damage(int dmg) {
        hp -= dmg;
        if (hp <= 0) {
            hp = 0;
            System.out.println("Game Over!");
        }
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void gainEXP(int EXP) {
        playerLevel.gainEXP(EXP);
    }
}
