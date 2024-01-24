package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeDecoder {

    private final File filepath;
    private int column_num;
    private int row_num;

    public MazeDecoder(File filepath) {
        this.filepath= filepath;
    }

    private int[][] getMazeSize() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        int column_num = reader.readLine().length();
        int row_num = 1;

        while (reader.readLine() != null) {
            row_num++;
        }
        reader.close();

        this.column_num = column_num;
        this.row_num = row_num;
        
        int[][] maze_array = new int[row_num][column_num];
        return maze_array;
    }

    public void print() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("# ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("- ");
                }
            }
            System.out.print(System.lineSeparator());
        }
        reader.close();
    }

    public int[][] decode() throws IOException {
        int[][] decoded = getMazeSize();
        System.out.println("The maze is " + row_num + " rows by " + column_num + " columns");
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        int row_count = 0;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    decoded[row_count][idx] = 1;
                } else if (line.charAt(idx) == ' ') {
                    decoded[row_count][idx] = 0;
                }
            }
            row_count++;
        }
        reader.close();

        return decoded;
    }
}
