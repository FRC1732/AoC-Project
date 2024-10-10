package aoc.project.year2023.day10;

import java.util.ArrayList;

import javax.annotation.meta.When;

public class AocPipe {

    // constants

    enum ConnectionType {
        INVALID, // out of bounds
        IS_TILE, // this is the pipe that we are
        CONNECTED, // valid connection
        NOT_CONNECTED, // can't connect
    }

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST,
        UNREACHABLE,
    }

    //

    private AocPipe[][] containGrid;
    private int[] coordinates;

    private ConnectionType[][] connectionGrid;
    private char shape;

    public AocPipe(int x, int y, char shape, AocPipe[][] grid) {
        coordinates = new int[] {x, y};
        this.shape = shape;
        connectionGrid = new ConnectionType[3][3];
        containGrid = grid;
    }

    public char getShape() {
        return shape;
    }

    public ConnectionType[][] getConnectionGrid() {
        return connectionGrid;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public boolean validIntersectionFromDirection(Direction from) {
        switch (shape) {
            case '|':
                return from == Direction.NORTH || from == Direction.SOUTH;

            case '-':
                return from == Direction.EAST || from == Direction.WEST;

            case 'L':
                return from == Direction.SOUTH || from == Direction.WEST;

            case 'J':
                return from == Direction.SOUTH || from == Direction.EAST;

            case '7':
                return from == Direction.NORTH || from == Direction.EAST;

            case 'F':
                return from == Direction.NORTH || from == Direction.WEST;

            case '.':
                return false;

            case 'S': // this will probably break things, yeabh
                return true;
        
            default:
                return false;
        }
    }

    public boolean isConnectedToTile(AocPipe otherPipe) {
        int[] otherCoordinates = otherPipe.getCoordinates();

        int thisX = coordinates[0];
        int thisY = coordinates[1];

        int otherX = otherCoordinates[0];
        int otherY = otherCoordinates[1];
        
        int adjustedX = otherX - thisX;
        int adjustedY = otherY - thisY;

        if (adjustedX > 1 || adjustedY > 1 || adjustedX < -1 || adjustedY < -1) {
            return false;
        }

        // diagonals will break things
        if (Math.abs(adjustedX) == Math.abs(adjustedY)) {
            return false;
        }

        Direction direction = Direction.UNREACHABLE;
        if (adjustedX == -1) {
            direction = Direction.WEST;
        } else if (adjustedX == 1) {
            direction = Direction.EAST;
        }

        if (adjustedY == -1) {
            direction = Direction.SOUTH;
        } else if (adjustedY == 1) {
            direction = Direction.NORTH;
        }

        return otherPipe.validIntersectionFromDirection(direction);
    }

    public Direction isConnectedToTileDirection(AocPipe otherPipe) {
        int[] otherCoordinates = otherPipe.getCoordinates();

        int thisX = coordinates[0];
        int thisY = coordinates[1];

        int otherX = otherCoordinates[0];
        int otherY = otherCoordinates[1];
        
        int adjustedX = otherX - thisX;
        int adjustedY = otherY - thisY;

        if (adjustedX > 1 || adjustedY > 1 || adjustedX < -1 || adjustedY < -1) {
            return Direction.UNREACHABLE;
        }

        // diagonals will break things
        if (Math.abs(adjustedX) == Math.abs(adjustedY)) {
            return Direction.UNREACHABLE;
        }

        Direction direction = Direction.UNREACHABLE;
        if (adjustedX == -1) {
            direction = Direction.WEST;
        } else if (adjustedX == 1) {
            direction = Direction.EAST;
        }

        if (adjustedY == -1) {
            direction = Direction.SOUTH;
        } else if (adjustedY == 1) {
            direction = Direction.NORTH;
        }

        if (!otherPipe.validIntersectionFromDirection(direction)) {
            return Direction.UNREACHABLE;
        }
        return direction;
    }

    public AocPipe getPipeFromLocalCords(int x, int y) {
        int thisX = coordinates[0];
        int thisY = coordinates[1];
        
        int adjustedX = thisX + x;
        int adjustedY = thisY + y;

        return containGrid[adjustedX][adjustedY];
    }
     
    public ArrayList<AocPipe> getConnectedPipes() {
        makeConnections();
        ArrayList<AocPipe> connections = new ArrayList<>();

        for (int outer = 0; outer <= 2; outer++) {
            for (int inner = 0; inner <= 2; inner++) {
                if (connectionGrid[outer][inner] == ConnectionType.CONNECTED) {
                    connections.add(getPipeFromLocalCords(outer, inner));
                }
            }
        }
        return connections;
    } 

    public void makeConnections() {
         for (int outer = 0; outer <= 2; outer++) {
            for (int inner = 0; inner <= 2; inner++) {
                int adjustedX = coordinates[0] + outer - 1;
                int adjustedY = coordinates[1] + inner - 1;
                if (adjustedX < 0 || adjustedY < 0) {
                    connectionGrid[outer][inner] = ConnectionType.INVALID;
                } else if (inner == 0 && outer == 0) {
                    connectionGrid[outer][inner] = ConnectionType.IS_TILE;
                } else {
                    AocPipe otherPipe = containGrid[adjustedX][adjustedY];

                    if (isConnectedToTile(otherPipe)) {
                        connectionGrid[outer][inner] = ConnectionType.CONNECTED;
                    } else {
                        connectionGrid[outer][inner] = ConnectionType.NOT_CONNECTED;
                    }
                }
            }
         }
    }

}
