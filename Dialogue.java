/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

/**
 *
 * @author admin
 */
public class Dialogue {
    public static String getGreeting(String playerName) {
        return "Greetings " + playerName + "! \nwelcome to Faba town where rookie adventurers such as yourself come to start their journey!\n";
    }

    public static String getGuildWelcome() {
        return "Welcome to the Faba Adventurers Guild! \nIs this your first time here? \nIf so I have the perfect quest for a beginner like you!\n";
    }

    public static String getQuestInfo() {
        return "Recently there has been a Kobold causing issues for the incoming merchants. \nThey aren't that strong so i'm sure you take one on! \nJust head on over to 'The plains' to get started on your quest!\n";
    }

    public static String getQuestStarted() {
        return "You have started quest 1\n";
    }

    public static String getQuestInProgress() {
        return "Hmmm? you're back already? \nIf you want to start a new quest come back when you've finished your previous quest!\n";
    }

    public static String getQuestComplete() {
        return "Hey! look at you! it seems that you've gotten stronger! \nMust mean that you've finished you quest! With that I can make you a member of the guild! \njust sign your name right here!\n";
    }

    public static String getGuildMemberConfirmation() {
        return "You became a member of the Faba Adventurers Guild!\n";
    }

    public static String getGuildFarewell(String playerName) {
        return "Well then " + playerName + ". I wish you the best of luck on your journey! See ya around kid!\n";
    }

    public static String getEndOfDemo() {
        return "This is the end of our RPG Demo! I hope that you enjoyed our game!\n";
    }

    public static String getTheEnd() {
        return "THE END!";
    }
}

