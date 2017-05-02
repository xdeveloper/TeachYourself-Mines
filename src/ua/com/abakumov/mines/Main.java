package ua.com.abakumov.mines;

import static ua.com.abakumov.mines.Main.Direction.*;

public class Main {

    // -1 - mine
    // 0  - dirt
    // 1  - road
    private int[][] fieldReady = {
            {0, 1, 1, -1},
            {1, 1, 0, 1},
            {1, 0, 1, 0},
            {1, -1, 1, 0},
    };

    private int size;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        size = fieldReady.length;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (isRoad(row, col)) {
                    scanFrom(row, col, U);
                }
            }
        }

        // Max number is the cell we want to find out
        printMatrix(fieldReady);
    }

    enum Direction {N, S, W, E, U}

    private void scanFrom(int row, int col, Direction dir) {
        if (canMoveToWest(row, col)) {
            if (dir == W) {
                return;
            }
            increaseCell(row, col - 1);
            scanFrom(row, col - 1, E);
        }
        if (canMoveToEast(row, col)) {
            if (dir == E) {
                return;
            }
            increaseCell(row, col + 1);
            scanFrom(row, col + 1, W);
        }
        if (canMoveToNorth(row, col)) {
            if (dir == N) {
                return;
            }
            increaseCell(row - 1, col);
            scanFrom(row - 1, col, S);
        }
        if (canMoveToSouth(row, col)) {
            if (dir == S) {
                return;
            }
            increaseCell(row + 1, col);
            scanFrom(row + 1, col, N);
        }
    }

    private boolean isMine(int cell) {
        return cell == -1;
    }

    private boolean isDirt(int cell) {
        return cell == 0;
    }

    private boolean isRoad(int row, int col) {
        int cell = getCell(row, col);
        return !isDirt(cell) && !isMine(cell);
    }

    private void increaseCell(int row, int col) {
        setCell(row, col, getCell(row, col) + 1);
    }

    private void setCell(int row, int col, int val) {
        fieldReady[row][col] = val;
    }

    private void log(String s) {
        System.out.println(s);
    }

    private boolean canMoveToNorth(int row, int col) {
        return row != 0 && isRoad(row - 1, col);
    }

    private boolean canMoveToSouth(int row, int col) {
        return row + 1 < size && isRoad(row + 1, col);
    }

    private boolean canMoveToWest(int row, int col) {
        return col != 0 && isRoad(row, col - 1);
    }

    private boolean canMoveToEast(int row, int col) {
        return col + 1 < size && isRoad(row, col + 1);
    }

    private int getCell(int row, int col) {
        return this.fieldReady[row][col];
    }

    private static void printMatrix(int[][] m) {
        try {
            int columns = m[0].length;
            StringBuilder str = new StringBuilder("|\t");

            for (int[] aM : m) {
                for (int j = 0; j < columns; j++) {
                    str.append(aM[j]).append("\t");
                }

                System.out.println(str + "|");
                str = new StringBuilder("|\t");
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }

        System.out.println();
    }
}
