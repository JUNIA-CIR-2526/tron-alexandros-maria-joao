package com.jad;

import com.jad.textwindow.TextWindow;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(80, 40);


        grid.getSettings().addKeyboardListener(KeyEvent.VK_ESCAPE, "exit");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_UP, "player1_up");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_DOWN, "player1_down");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_LEFT, "player1_left");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_RIGHT, "player1_right");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_Z, "player2_up");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_S, "player2_down");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_Q, "player2_left");
        grid.getSettings().addKeyboardListener(KeyEvent.VK_D, "player2_right");

        TextWindow textWindow = new TextWindow(grid.getSettings());
        textWindow.setVisible(true);

        LightCycle player1 = new LightCycle(20, 20);
        LightCycle player2 = new LightCycle(60, 20);


        while (textWindow.isOff("exit") && player1.isAlive() && player2.isAlive()) {


            if (textWindow.isOn("player1_up")) {
                player1.getDirection().setUp();
            }
            if (textWindow.isOn("player1_down")) {
                player1.getDirection().setDown();
            }
            if (textWindow.isOn("player1_left")) {
                player1.getDirection().setLeft();
            }
            if (textWindow.isOn("player1_right")) {
                player1.getDirection().setRight();
            }

            if (textWindow.isOn("player2_up")) {
                player2.getDirection().setUp();
            }
            if (textWindow.isOn("player2_down")) {
                player2.getDirection().setDown();
            }
            if (textWindow.isOn("player2_left")) {
                player2.getDirection().setLeft();
            }
            if (textWindow.isOn("player2_right")) {
                player2.getDirection().setRight();
            }

            player1.move();
            player2.move();

            if (grid.isOutOfBounds(player1.getPosition())) {
                grid.wrapPosition(player1.getPosition());
            }
            if (grid.isOutOfBounds(player2.getPosition())) {
                grid.wrapPosition(player2.getPosition());
            }


            if (player1.isAlive()) {
                for (Position pos : player1.getTrail().subList(0, player1.getTrail().size() - 1)) {
                    if (pos.getX() == player1.getPosition().getX() &&
                            pos.getY() == player1.getPosition().getY()) {
                        player1.kill();
                    }
                }
                for (Position pos : player2.getTrail()) {
                    if (pos.getX() == player1.getPosition().getX() &&
                            pos.getY() == player1.getPosition().getY()) {
                        player1.kill();
                    }
                }
            }

            if (player2.isAlive()) {
                for (Position pos : player2.getTrail().subList(0, player2.getTrail().size() - 1)) {
                    if (pos.getX() == player2.getPosition().getX() &&
                            pos.getY() == player2.getPosition().getY()) {
                        player2.kill();
                    }
                }
                for (Position pos : player1.getTrail()) {
                    if (pos.getX() == player2.getPosition().getX() &&
                            pos.getY() == player2.getPosition().getY()) {
                        player2.kill();
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


            StringBuilder display = new StringBuilder();
            display.append("=== TRON LIGHT CYCLES ===\n\n");
            display.append("Player 1 (1): Flèches directionnelles\n");
            display.append("Player 2 (2): WASD\n\n");
            display.append("Player 1 Position: (").append(player1.getPosition().getX())
                    .append(", ").append(player1.getPosition().getY()).append(")\n");
            display.append("Player 2 Position: (").append(player2.getPosition().getX())
                    .append(", ").append(player2.getPosition().getY()).append(")\n\n");


            char[][] gameGrid = new char[grid.getHeight()][grid.getWidth()];
            for (int i = 0; i < grid.getHeight(); i++) {
                for (int j = 0; j < grid.getWidth(); j++) {
                    gameGrid[i][j] = '.';
                }
            }


            for (Position pos : player1.getTrail()) {
                if (pos.getX() >= 0 && pos.getX() < grid.getWidth() &&
                        pos.getY() >= 0 && pos.getY() < grid.getHeight()) {
                    gameGrid[pos.getY()][pos.getX()] = '■';
                }
            }
            for (Position pos : player2.getTrail()) {
                if (pos.getX() >= 0 && pos.getX() < grid.getWidth() &&
                        pos.getY() >= 0 && pos.getY() < grid.getHeight()) {
                    gameGrid[pos.getY()][pos.getX()] = '□';
                }
            }

            for (int i = 0; i < grid.getHeight(); i++) {
                for (int j = 0; j < grid.getWidth(); j++) {
                    display.append(gameGrid[i][j]);
                }
                display.append('\n');
            }

            textWindow.display(display.toString());


        }



        String winner = "";
        if (!player1.isAlive() && !player2.isAlive()) {
            winner = "MATCH NUL !";
        } else if (player1.isAlive()) {
            winner = "PLAYER 1 GAGNE !";
        } else {
            winner = "PLAYER 2 GAGNE !";
        }

        textWindow.display("\n\n=== FIN DE PARTIE ===\n" + winner);


        textWindow.close();
    }
}