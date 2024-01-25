package ca.mcmaster.se2aa4.mazerunner;

public class MazePath {

    private String raw_sequence;
    private String canonical;
    private String factorized;

    public MazePath(String path) {
        this.raw_sequence = path != null ? path : "";
        factorize();
        canonize();
    }

    private String removeSpaces() {
        return raw_sequence.replaceAll("\\s+", "");
    }

    private void factorize() {
        String raw_seq = removeSpaces();
        StringBuilder factorized_seq = new StringBuilder();

        int move_count = 1;
        for (int i = 1; i < raw_seq.length(); i++) {
            char move = raw_seq.charAt(i);
            char prev_move = raw_seq.charAt(i - 1);
            if (move == prev_move) {
                move_count++;
            } else {
                if (move_count > 1)
                    factorized_seq.append(Integer.toString(move_count));
                factorized_seq.append(prev_move);

                move_count = 1;
            }

            if (i == raw_seq.length() - 1) {
                if (move_count > 1)
                    factorized_seq.append(Integer.toString(move_count));
                factorized_seq.append(move);
            }
        }
        factorized = factorized_seq.toString();
    }

    private void canonize() {
        String raw_seq = removeSpaces();
        StringBuilder canonical_seq = new StringBuilder();
        
        for (int i = 0; i < raw_seq.length(); i++) {
            char move = raw_seq.charAt(i);

            if (Character.isDigit(move)) {
                int move_count = Character.getNumericValue(move);
                char actual_move = raw_seq.charAt(i+1);
                for (int rep = 0; rep < move_count - 1; rep++) {
                    canonical_seq.append(actual_move);
                }
            } else {
                canonical_seq.append(move);
            }
        }
        canonical = canonical_seq.toString();
    }

    public String getRaw() {
        return raw_sequence;
    }

    public String getCanonical() {
        return canonical;
    }

    public String getFactorized() {
        return factorized;
    }

    public int getCanonicalLength() {
        return canonical.length();
    }

    public char getMove(int index) {
        return canonical.charAt(index);
    }
}
