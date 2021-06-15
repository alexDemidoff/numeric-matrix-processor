package processor;

public class Matrix implements MatrixOperations {

    private final double[][] cells;
    private final int n;
    private final int m;

    private Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        cells = new double[n][m];
    }

    public static Matrix initialize(int n, int m) {
        return new Matrix(n, m);
    }

    public void print() {
        String[][] res = new String[this.getRowCount()][this.getColumnCount()];
        int[] maxLengthInColumn = new int[this.getColumnCount()];

        //array of string representation of numbers
        for (int j = 0; j < this.getColumnCount(); j++) {
            for (int i = 0; i < this.getRowCount(); i++) {
                double element = this.get(i, j);
                res[i][j] = String.valueOf(element % 1 == 0 ? (int) element : String.format("%.2f", element));
            }
        }

        //array of number lengths
        for (int j = 0; j < this.getColumnCount(); j++) {
            int maxLength = res[0][j].length();
            for (int i = 1; i < this.getRowCount(); i++) {
                if (res[i][j].length() > maxLength) {
                    maxLength = res[i][j].length();
                }
            }

            maxLengthInColumn[j] = maxLength;
        }

        //printing
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                System.out.printf("%" + maxLengthInColumn[j] + "s ", res[i][j]);
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

    @Override
    public Matrix add(Matrix m, Status status) {
        Matrix res = null;

        if (this.getRowCount() == m.getRowCount() && this.getColumnCount() == m.getColumnCount()) {
            res = Matrix.initialize(this.getRowCount(), m.getColumnCount());

            for (int i = 0; i < res.getRowCount(); i++) {
                for (int j = 0; j < res.getColumnCount(); j++) {
                    res.set(i, j, this.get(i, j) + m.get(i, j));
                }
            }

            status.isSuccess(true);
        } else {
            status.isSuccess(false);
            status.setMessage("Matrices should have the same dimensions.");
        }

        return res;
    }

    @Override
    public Matrix multiplyBy(double c, Status status) {
        Matrix res = Matrix.initialize(this.getRowCount(), this.getColumnCount());

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                res.set(i, j, c * this.get(i, j));
            }
        }

        status.isSuccess(true);
        return res;
    }

    @Override
    public Matrix multiplyBy(Matrix m, Status status) {
        Matrix res = null;

        if (this.getColumnCount() == m.getRowCount()) {
            res = Matrix.initialize(this.getRowCount(), m.getColumnCount());

            for (int k = 0; k < m.getColumnCount(); k++) {
                for (int i = 0; i < this.getRowCount(); i++) {
                    double sum = 0;
                    for (int j = 0; j < this.getColumnCount(); j++) {
                        sum += this.get(i, j) * m.get(j, k);
                    }
                    res.set(i, k, sum);
                }
            }

            status.isSuccess(true);
        } else {
            status.isSuccess(false);
            status.setMessage("Columns of first matrix should be equal to rows of second matrix.");
        }

        return res;
    }

    @Override
    public Matrix transpose(int mode, Status status) {
        status.isSuccess(true);

        switch (mode) {
            case MAIN_DIAGONAL:
                return transposeAlongMainDiagonal();
            case SIDE_DIAGONAL:
                return transposeAlongSideDiagonal();
            case VERTICAL_LINE:
                return transposeAlongVerticalLine();
            case HORIZONTAL_LINE:
                return transposeAlongHorizontalLine();
            default:
                return this;
        }
    }

    private Matrix getMinor(int skipRow, int skipColumn) {
        Matrix res = Matrix.initialize(this.getRowCount() - 1, this.getColumnCount() - 1);

        int r = 0;
        int c = -1;
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                if (i != skipRow && j != skipColumn) {
                    c++;
                    res.set(r, c, this.get(i, j));

                    if (c == res.getColumnCount() - 1) {
                        c = -1;
                        r++;
                    }
                }
            }
        }

        return res;
    }

    @Override
    public double calculateDeterminant() {
        return calculateDeterminant(this);
    }

    private double calculateDeterminant(Matrix m) {
        double det = 0;

        if (m.getRowCount() == 1) {
            return m.get(0, 0);
        }

        int sign = 1;
        int i = 0; // first-row expansion
        for (int j = 0; j < m.getColumnCount(); j++) {
            det += m.get(i, j) * sign * calculateDeterminant(m.getMinor(i, j));
            sign = -sign;
        }

        return det;
    }

    @Override
    public Matrix inverse(Status status) {
        Matrix cofactorMatrix;
        Matrix res = null;

        double det = calculateDeterminant();

        if (det == 0) {
            status.isSuccess(false);
            status.setMessage("This matrix doesn't have an inverse.");
        } else {
            cofactorMatrix = Matrix.initialize(this.getRowCount(), this.getColumnCount());
            int sign;
            for (int i = 0; i < this.getRowCount(); i++) {
                for (int j = 0; j < this.getColumnCount(); j++) {
                    sign = (i + j) % 2 == 0 ? 1 : -1;
                    cofactorMatrix.set(i, j, sign * calculateDeterminant(getMinor(i, j)));
                }
            }

            res = cofactorMatrix.transpose(MAIN_DIAGONAL, status).multiplyBy(1 / det, status);
            status.isSuccess(true);
        }

        return res;
    }

    private Matrix transposeAlongHorizontalLine() {
        Matrix res = Matrix.initialize(this.getRowCount(), this.getColumnCount());

        for (int j = 0; j < this.getColumnCount(); j++) {
            for (int i = 0; i < this.getRowCount(); i++) {
                res.set(this.getRowCount() - 1 - i, j, this.get(i, j));
            }
        }

        return res;
    }

    private Matrix transposeAlongVerticalLine() {
        Matrix res = Matrix.initialize(this.getRowCount(), this.getColumnCount());

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                res.set(i, this.getColumnCount() - 1 - j, this.get(i, j));
            }
        }

        return res;
    }

    private Matrix transposeAlongSideDiagonal() {
        return this.transposeAlongMainDiagonal().transposeAlongHorizontalLine().transposeAlongVerticalLine();
    }

    private Matrix transposeAlongMainDiagonal() {
        Matrix res = Matrix.initialize(this.getColumnCount(), this.getRowCount());

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                res.set(j, i, this.get(i, j));
            }
        }

        return res;
    }

    public void setRow(int rowIndex, String[] row) {
        for (int j = 0; j < getColumnCount(); j++) {
            set(rowIndex, j, Double.parseDouble(row[j]));
        }
    }
}
