/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcGUI;

/**
 *
 * @author admin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

public class GameScreen extends JPanel {

    private JTextField userInput;
    private JTextArea dialogueArea;
    private JPanel buttonPanel, textFieldPanel;
    private Queue<String> dialogues;
    private Timer timer;
    private Player player;
    private Inventory inventory;

    public int progress;
    public boolean questStarted;

    public GameScreen() {
        // Prompt the user for their name
        String playerName = JOptionPane.showInputDialog("Please enter your name: ");

        setPreferredSize(new Dimension(800, 500));
        setLayout(new BorderLayout());

        //User text input (WIP)
        userInput = new JTextField();
        userInput.setPreferredSize(new Dimension(700, 25));
        userInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // The Enter key was pressed
                    // You can start the combat or do other things here
                    // For example:
                    Player pn = new Player(player.getPlayerName(), 30);
                    KoboldEnemy kobold = new KoboldEnemy();
                    Combat c = new Combat(pn, kobold, dialogueArea, userInput); // Pass userInput to Combat
                    c.playerCombat();
                    if (!pn.isAlive()) {
                        dialogueArea.append("\nYOU DIED!\nGAME OVER");
                        return;
                    }
                }
            }
        });

        //Dialogue area
        dialogueArea = new JTextArea();
        dialogueArea.setEditable(false);
        dialogueArea.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(dialogueArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        inventory = new Inventory(dialogueArea, userInput);

        //Player object
        player = new Player();
        player.setPlayerName(playerName);

        dialogues = new LinkedList<>();
        dialogues.add(Dialogue.getGreeting(playerName));
        dialogues.add(Dialogue.getGuildWelcome());
        dialogues.add(Dialogue.getQuestInfo());

        //Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(createButton("Guild"));
        buttonPanel.add(createButton("Shops"));
        buttonPanel.add(createButton("The Plains"));
        buttonPanel.add(createButton("Inventory"));
        buttonPanel.add(createButton("Save Game"));
        buttonPanel.add(createButton("Load Game"));
        buttonPanel.add(createButton("Exit Game"));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton guildButton = createButton("Guild");
        guildButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Guild");
            }
        });
        buttonPanel.add(guildButton);

        JButton shopButton = createButton("Shops");
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Shops are currently under construction.");
            }
        });
        buttonPanel.add(shopButton);

        JButton plainsButton = createButton("The Plains");
        plainsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player pn = new Player(player.getPlayerName(), 30);
                KoboldEnemy kobold = new KoboldEnemy();
                Combat c = new Combat(player, kobold, dialogueArea, userInput); // Pass dialogueArea to Combat
                c.playerCombat();
                if (!pn.isAlive()) {
                    dialogueArea.append("\nYOU DIED!\nGAME OVER");
                    return;
                }
            }
        });
        buttonPanel.add(plainsButton);

        JButton invenButton = createButton("Inventory");
        invenButton.addActionListener(new ActionListener() {
            boolean inInventory = false;

            public void actionPerformed(ActionEvent e) {
                if (inInventory) {
                    // Exiting the inventory
                    inInventory = false;
                    invenButton.setText("Inventory");
                    // Redisplay the completed dialogues
                    dialogueArea.setText("");
                    for (String dialogue : dialogues) {
                        dialogueArea.append("\n" + dialogue);
                        timer.start();
                    }
                } else {
                    // Showing inventory
                    inInventory = true;
                    invenButton.setText("Exit Inventory");
                    inventory.showInventory();
                }
            }
        });
        buttonPanel.add(invenButton);

        JButton saveButton = createButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameSave.saveGame(player, inventory, player.getLevel(), progress, dialogueArea);
            }
        });
        buttonPanel.add(saveButton);

        JButton loadButton = createButton("Load Game");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameSave.loadGame(dialogueArea);
            }
        });
        buttonPanel.add(loadButton);

        JButton exitButton = createButton("Exit Game");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        timer = new Timer(3000, new ActionListener() { // 3s delay
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dialogues.isEmpty()) {
                    dialogueArea.append("\n" + dialogues.poll());
                } else {
                    timer.stop();
                    // Enable the buttons once the dialogues are finished
                    for (Component comp : buttonPanel.getComponents()) {
                        if (comp instanceof JButton) {
                            comp.setEnabled(true);
                        }
                    }
                }
            }
        });

        // Disable the buttons at the start
        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(false);
            }
        }

        // Start the timer
        timer.start();

        //Text field input layout
        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.PAGE_AXIS));
        textFieldPanel.add(userInput);
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        textFieldPanel.add(buttonPanel);

        add(scrollPane, BorderLayout.CENTER);
        add(textFieldPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 50)); // adjust size as needed
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Game Screen");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GameScreen gameScreen = new GameScreen();
                frame.setResizable(false);
                frame.add(gameScreen);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
