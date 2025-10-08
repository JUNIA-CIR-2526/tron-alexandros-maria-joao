package com.jad;

import com.jad.textwindow.TextWindowSettings;

public class Grid {
    private int height;
    private int width;
    private TextWindowSettings settings;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.settings = new TextWindowSettings();
        settings.setScreenWidth(width);
        settings.setScreenHeight(height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public TextWindowSettings getSettings() {
        return settings;
    }

    public boolean isOutOfBounds(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    public void wrapPosition(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0) {
            x = width - 1;
        } else if (x >= width) {
            x = 0;
        }

        if (y < 0) {
            y = height - 1;
        } else if (y >= height) {
            y = 0;
        }

        position.setX(x);
        position.setY(y);
    }

}