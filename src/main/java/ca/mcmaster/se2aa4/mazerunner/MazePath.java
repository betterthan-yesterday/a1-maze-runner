package ca.mcmaster.se2aa4.mazerunner;

public class MazePath {

    private String raw_sequence;
    private String canonical;
    private String factorized;

    public MazePath(String path) {
        this.raw_sequence = path != null ? path : "";
    }

    public String getRaw() {
        return raw_sequence;
    }

    private String removeSpaces() {
        return raw_sequence.replaceAll("\\s+", "");
    }

    private void factorize() {

    }

    private void canonize() {

    }

    public String getCanonical() {
        return canonical;
    }

    public int getCanonicalLength() {
        return canonical.length();
    }

    public String getFactorized() {
        return factorized;
    }

    public char getMove(int index) {
        return canonical.charAt(index);
    }
}
