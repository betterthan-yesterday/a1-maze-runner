package ca.mcmaster.se2aa4.mazerunner;

public class MazePath {

    private final String sequence;

    public MazePath(String path) {
        this.sequence = path != null ? path : "";
    }

    public String getSequence() {
        return sequence;
    }

    public char getMove(int index) {
        return sequence.charAt(index);
    }

    public int length() {
        return sequence.length();
    }
}
