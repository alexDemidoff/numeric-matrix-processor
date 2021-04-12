package processor;

import java.util.Scanner;

public class Matrix {

    private static final Scanner scanner = new Scanner(System.in);
    private int[][] matrix;
    private int n;
    private int m;

    public void initialize(int n, int m) {
        this.n = n;
        this.m = m;

        matrix = new int[n][m];
    }

    public void readMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void set(int i, int j, int element) {
        matrix[i][j] = element;
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public int getRowCount() {
        return n;
    }

    public int getColumnCount() {
        return m;
    }
}
