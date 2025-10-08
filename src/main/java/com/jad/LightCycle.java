package com.jad;

import java.util.ArrayList;
import java.util.List;

public class LightCycle {

    private Position position;
    private Direction direction;
    private List<Position> trail;
    private boolean isAlive;


    public LightCycle(int x, int y) {
        this.position = new Position(x, y);
        this.direction = new Direction();
        this.trail = new ArrayList<>();
        this.trail.add(new Position(x, y));
        this.isAlive = true;
    }

    public void move() {
        if (!isAlive) {
            return;
        }

        int x = position.getX();
        int y = position.getY();

        if (direction.getUp() == 1) {
            y--;
        }

        else if (direction.getDown() == 1) {
            y++;
        }

        else if (direction.getLeft() == 1) {
            x--;
        }

        else if (direction.getRight() == 1) {
            x++;
        }

        position.setX(x);
        position.setY(y);
        trail.add(new Position(x, y));
    }

    public Position getPosition() {
        return position;
    }
    public Direction getDirection() {
        return direction;
    }

    public List<Position> getTrail() {
        return trail;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
}
