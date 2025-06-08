# 4x4 Tic-Tac-Toe

Eine Java Swing-Implementierung des klassischen Tic-Tac-Toe-Spiels mit einem erweiterten 4x4-Spielfeld. Dieses Projekt demonstriert die Verwendung von Java Swing für die Erstellung einer interaktiven Spieloberfläche und die Implementierung einer Spiellogik.

## Funktionen

- 4x4-Spielfeld für erweiterte Spielmöglichkeiten
- Grafische Benutzeroberfläche mit Java Swing
- Zufällige Auswahl des Startspielers
- Automatische Erkennung von Gewinnkombinationen
- Visuelle Hervorhebung der Gewinnkombination
- Unentschieden-Erkennung
- Möglichkeit zum Zurücksetzen des Spiels

## Anforderungen

- Java Development Kit (JDK) 8 oder höher
- Eine Java-IDE wie Eclipse, IntelliJ IDEA oder NetBeans

## Installation und Ausführung

1. Klonen Sie das Repository:
git clone https://github.com/MoBoudni/java-tictactoe-4x4.git

2. Öffnen Sie das Projekt in Ihrer bevorzugten Java-IDE

3. Führen Sie die Klasse `com.spielart.TicTacToe.Main` aus, um das Spiel zu starten

## Spielregeln

- Spieler X und Spieler O setzen abwechselnd ihre Symbole auf das 4x4-Spielfeld
- Der erste Spieler, der vier seiner Symbole in einer horizontalen, vertikalen oder diagonalen Reihe platziert, gewinnt
- Wenn alle Felder belegt sind und kein Spieler gewonnen hat, endet das Spiel unentschieden

## Projektstruktur

- `TicTacToe.java`: Hauptklasse, die das Spiel und die GUI implementiert
- `TicTacToe.Main`: Innere Klasse zum Starten des Spiels

## Gewinnkombinationen

Das Spiel überprüft folgende Gewinnkombinationen:
- 4 horizontale Reihen
- 4 vertikale Reihen
- 2 diagonale Reihen

## Autor

- M. Boudni

## Lizenz

Dieses Projekt steht unter der MIT-Lizenz - siehe die [LICENSE](LICENSE) Datei für Details.
