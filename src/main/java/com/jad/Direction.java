package com.jad;

public class Direction {
    private int up;
    private int down;
    private int left;
    private int right;

    public Direction() {
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 1;
    }

    public void setUp() {
        up = 1; down = 0; left = 0; right = 0;
    }

    public void setDown() {
        up = 0; down = 1; left = 0; right = 0;
    }

    public void setLeft() {
        up = 0; down = 0; left = 1; right = 0;
    }

    public void setRight() {
        up = 0; down = 0; left = 0; right = 1;
    }

    public int getUp() { return up; }
    public int getDown() { return down; }
    public int getLeft() { return left; }
    public int getRight() { return right; }
}
