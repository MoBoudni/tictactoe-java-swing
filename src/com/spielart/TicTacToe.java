package com.spielart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Der Code verwendet grundlegende Konzepte der objektorientierten Programmierung und der GUI-Entwicklung mit Java Swing
 * 
 * Diese Klasse implementiert ein einfaches Tic-Tac-Toe-Spiel mit einer 4x4-Spielfeldmatrix.
 * 
 * Das Spiel wird über eine grafische Benutzeroberfläche (GUI) gesteuert, die mit Java Swing erstellt wurde.
 * Die Spieler (X und O) wechseln sich ab, um ihre Symbole in die Felder zu setzen. Das Spiel überprüft nach
 * jedem Zug, ob ein Spieler gewonnen hat, indem es alle möglichen Gewinnkombinationen überprüft. Wenn ein Spieler
 * gewinnt, werden die entsprechenden Felder hervorgehoben und das Spiel wird beendet.
 * 
 * Die GUI besteht aus einem JFrame, das das Hauptfenster darstellt, und zwei JPanels: eines für den Titel und eines
 * für die Schaltflächen des Spielfelds. Die Schaltflächen sind in einem 4x4-Grid angeordnet und reagieren auf Klicks,
 * um die Züge der Spieler zu verarbeiten.
 * 
 * Die Klasse implementiert das ActionListener-Interface, um auf Benutzeraktionen (Klicks auf die Schaltflächen)
 * zu reagieren.
 * 
 * @author M. Boudni
 * @version 1.1
 */
public class TicTacToe implements ActionListener {

    // ****************** Konstanten ********************
    private static final int GRID_SIZE = 4;
    private static final int TOTAL_BUTTONS = GRID_SIZE * GRID_SIZE;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private static final String EMPTY_TEXT = "";
    
    // Alle möglichen Gewinnkombinationen für 4x4 Grid
    private static final int[][] WINNING_COMBINATIONS = {
        // Horizontale Linien
        {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15},
        // Vertikale Linien
        {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15},
        // Diagonale Linien
        {0, 5, 10, 15}, {3, 6, 9, 12}
    };

    // ****************** Instanzvariablen ********************
    private final Random random;
    private final JFrame frame;
    private final JPanel titleJPanel;
    private final JPanel btnJPanel;
    private final JLabel textJLabel;
    private final JButton[] buttons;
    private boolean player1Turn;
    private boolean gameEnded;

    // ****************** Konstruktor ********************
    public TicTacToe() {
        this.random = new Random();
        this.frame = new JFrame();
        this.titleJPanel = new JPanel();
        this.btnJPanel = new JPanel();
        this.textJLabel = new JLabel();
        this.buttons = new JButton[TOTAL_BUTTONS];
        this.gameEnded = false;
        
        initializeGUI();
        firstTurn();
    }

    /**
     * Initialisiert die grafische Benutzeroberfläche
     */
    private void initializeGUI() {
        // Frame-Konfiguration
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setTitle("4x4 Tic-Tac-Toe - IT Alfatraining");
        frame.setLocationRelativeTo(null); // Fenster zentrieren

        // Label-Konfiguration
        textJLabel.setBackground(new Color(25, 25, 0));
        textJLabel.setForeground(new Color(25, 255, 0));
        textJLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        textJLabel.setHorizontalAlignment(JLabel.CENTER);
        textJLabel.setText("Tic-Tac-Toe 4x4");
        textJLabel.setOpaque(true);

        // Button-Panel Konfiguration
        btnJPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        initializeButtons();

        // Title-Panel Konfiguration
        titleJPanel.setLayout(new BorderLayout());
        titleJPanel.setBounds(0, 0, 800, 100);
        titleJPanel.add(textJLabel);

        // Komponenten zum Frame hinzufügen
        frame.add(titleJPanel, BorderLayout.NORTH);
        frame.add(btnJPanel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

    /**
     * Initialisiert alle Buttons des Spielfelds
     */
    private void initializeButtons() {
        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.WHITE);
            btnJPanel.add(buttons[i]);
        }
    }

    /**
     * Bestimmt zufällig, welcher Spieler das Spiel beginnt
     */
    private void firstTurn() {
        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textJLabel.setText("Spieler X ist dran");
        } else {
            player1Turn = false;
            textJLabel.setText("Spieler O ist dran");
        }
    }

    // ************** Implementierung der Hauptspiellogik *******************
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) {
            return; // Spiel ist bereits beendet
        }

        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            if (e.getSource() == buttons[i] && EMPTY_TEXT.equals(buttons[i].getText())) {
                makeMove(i);
                break;
            }
        }
    }

    /**
     * Führt einen Spielzug aus
     * @param buttonIndex Index des geklickten Buttons
     */
    private void makeMove(int buttonIndex) {
        if (player1Turn) {
            buttons[buttonIndex].setText(PLAYER_X);
            buttons[buttonIndex].setForeground(Color.BLUE);
            player1Turn = false;
            textJLabel.setText("Spieler O ist dran");
        } else {
            buttons[buttonIndex].setText(PLAYER_O);
            buttons[buttonIndex].setForeground(Color.RED);
            player1Turn = true;
            textJLabel.setText("Spieler X ist dran");
        }
        
        checkWinner();
        
        if (!gameEnded) {
            checkForDraw();
        }
    }

    /**
     * Überprüft alle möglichen Gewinnkombinationen
     */
    private void checkWinner() {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (checkCombination(combination, PLAYER_X)) {
                playerWins(PLAYER_X, combination);
                return;
            }
            if (checkCombination(combination, PLAYER_O)) {
                playerWins(PLAYER_O, combination);
                return;
            }
        }
    }

    /**
     * Überprüft eine spezifische Gewinnkombination
     * @param combination Array mit Button-Indizes
     * @param player Spieler (X oder O)
     * @return true wenn der Spieler diese Kombination hat
     */
    private boolean checkCombination(int[] combination, String player) {
        for (int index : combination) {
            if (!player.equals(buttons[index].getText())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Behandelt den Gewinnfall
     * @param player Gewinner (X oder O)
     * @param winningCombination Gewinnende Kombination
     */
    private void playerWins(String player, int[] winningCombination) {
        // Gewinnende Buttons hervorheben
        for (int index : winningCombination) {
            buttons[index].setBackground(Color.YELLOW);
        }

        // Alle Buttons deaktivieren
        for (JButton button : buttons) {
            button.setEnabled(false);
        }

        textJLabel.setText("Spieler " + player + " gewinnt!");
        gameEnded = true;
    }

    /**
     * Überprüft auf Unentschieden
     */
    private void checkForDraw() {
        for (JButton button : buttons) {
            if (EMPTY_TEXT.equals(button.getText())) {
                return; // Es gibt noch leere Felder
            }
        }
        
        // Alle Felder sind belegt, aber niemand hat gewonnen
        textJLabel.setText("Unentschieden!");
        gameEnded = true;
        
        // Alle Buttons deaktivieren
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    /**
     * Startet ein neues Spiel
     */
    public void resetGame() {
        gameEnded = false;
        
        // Alle Buttons zurücksetzen
        for (JButton button : buttons) {
            button.setText(EMPTY_TEXT);
            button.setEnabled(true);
            button.setBackground(Color.WHITE);
        }
        
        firstTurn();
    }

    /**
     * Main-Klasse zum Starten des Spiels
     */
    public static class Main {
        public static void main(String[] args) {
            // GUI auf Event Dispatch Thread ausführen
            javax.swing.SwingUtilities.invokeLater(() -> {
                new TicTacToe();
            });
        }
    }
}