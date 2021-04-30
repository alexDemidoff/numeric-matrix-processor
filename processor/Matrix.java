package processor;

import java.util.Scanner;

public class Matrix {

    private static final Scanner scanner = new Scanner(System.in);
    private double[][] cells;
    private int n;
    private int m;

    private Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        cells = new double[n][m];
    }

    public static Matrix initialize(int n, int m) {
        return new Matrix(n, m);
    }

    public void read() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = scanner.nextDouble();
            }
        }
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cells[i][j] % 1 == 0) {
                    System.out.print((int) cells[i][j] + " ");
                } else {
                    System.out.print(cells[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void set(int i, int j, double element) {
        cells[i][j] = element;
    }

    public double get(int i, int j) {
        return cells[i][j];
    }

    public int getRowCount() {
        return n;
    }

    public int getColumnCount() {
        return m;
    }

    public Matrix getCofactor(int row, int column) {
        Matrix res = Matrix.initialize(this.getRowCount() - 1, this.getColumnCount() - 1);

        int r = 0;
        int c = 0;
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                if (i != row && j != column) {
                    res.set(r, c++, this.get(i, j));

                    if (c == res.getColumnCount() - 1) {
                        c = 0;
                        r++;
                    }
                }
            }
        }

        return res;
    }
}
