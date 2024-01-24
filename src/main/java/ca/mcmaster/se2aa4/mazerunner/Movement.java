package ca.mcmaster.se2aa4.mazerunner;

public class Movement {
    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }

    public enum Move {
        FORWARD, LEFT, RIGHT, BACKWARD
    }

    public int[] updatePos(Move move, Direction dir, int[] currentPos) {
        int[] newPos = currentPos;
        switch (dir) {
            case NORTH -> newPos[0] = (move == Move.FORWARD) ? currentPos[0] - 1 : currentPos[0] + 1;
            case EAST -> newPos[1] = (move == Move.FORWARD) ? currentPos[1] + 1 : currentPos[1] - 1;
            case SOUTH -> newPos[0] = (move == Move.FORWARD) ? currentPos[0] + 1 : currentPos[0] - 1;
            case WEST -> newPos[1] = (move == Move.FORWARD) ? currentPos[1] - 1 : currentPos[1] + 1;
        }
        return newPos;
    }

    public Direction updateDir(Move move, Direction dir) {
        Direction newDir = null;
        switch (dir) {
            case NORTH -> newDir = (move == Move.RIGHT) ? Direction.EAST : Direction.WEST;
            case EAST -> newDir = (move == Move.RIGHT) ? Direction.SOUTH : Direction.NORTH;
            case SOUTH -> newDir = (move == Move.RIGHT) ? Direction.WEST : Direction.EAST;
            case WEST -> newDir = (move == Move.RIGHT) ? Direction.NORTH : Direction.SOUTH;
        }
        return newDir;
    }
}
