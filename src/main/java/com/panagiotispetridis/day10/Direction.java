package com.panagiotispetridis.day10;

public enum Direction {
    NORTH, SOUTH, EAST, WEST, NONE;

    public String toString() {
        switch (this) {
            case NORTH:
                return "NORTH";
            case SOUTH:
                return "SOUTH";
            case EAST:
                return "EAST";
            case WEST:
                return "WEST";
            case NONE:
                return "NONE";
        }

        return "INVALID";
    }
}
