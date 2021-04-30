package processor;

public class MatrixProcessor {

    public static final int MAIN_DIAGONAL = 1;
    public static final int SIDE_DIAGONAL = 2;
    public static final int VERTICAL_LINE = 3;
    public static final int HORIZONTAL_LINE = 4;

    private static Matrix res;

    public static Matrix add(Matrix a, Matrix b) throws WrongDimensionsException {
        if (a.getRowCount() == b.getRowCount() && a.getColumnCount() == b.getColumnCount()) {
            res = Matrix.initialize(a.getRowCount(), a.getColumnCount());

            for (int i = 0; i < res.getRowCount(); i++) {
                for (int j = 0; j < res.getColumnCount(); j++) {
                    res.set(i, j, a.get(i, j) + b.get(i, j));
                }
            }
        } else {
            throw new WrongDimensionsException();
        }

        return res;
    }

    public static Matrix multiplyByNumber(double c, Matrix matrix) {
        res = Matrix.initialize(matrix.getRowCount(), matrix.getColumnCount());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                res.set(i, j, c * matrix.get(i, j));
            }
        }

        return res;
    }

    public static Matrix multiply(Matrix a, Matrix b) throws WrongDimensionsException {
        if (a.getColumnCount() == b.getRowCount()) {
            res = Matrix.initialize(a.getRowCount(), b.getColumnCount());

            for (int k = 0; k < b.getColumnCount(); k++) {
                for (int i = 0; i < a.getRowCount(); i++) {
                    double sum = 0;
                    for (int j = 0; j < a.getColumnCount(); j++) {
                        sum += a.get(i, j) * b.get(j, k);
                    }
                    res.set(i, k, sum);
                }
            }
        } else {
            throw new WrongDimensionsException();
        }

        return res;
    }

    public static double calculateDeterminant(Matrix m) {
        double det = 0;

        if (m.getRowCount() == 1) {
            return m.get(0, 0);
        }

        int sign = 1;
        int i = 0; // first-row expansion
        for (int j = 0; j < m.getColumnCount(); j++) {
            det += sign * m.get(i, j) * calculateDeterminant(m.getCofactor(i, j));
            sign = -sign;
        }

        return det;
    }

    public static Matrix transpose(Matrix matrix, int mode) {
        switch (mode) {
            case MAIN_DIAGONAL:
                return transposeAlongMainDiagonal(matrix);
            case SIDE_DIAGONAL:
                return transposeAlongSideDiagonal(matrix);
            case VERTICAL_LINE:
                return transposeAlongVerticalLine(matrix);
            case HORIZONTAL_LINE:
                return transposeAlongHorizontalLine(matrix);
            default:
                return matrix;
        }
    }

    private static Matrix transposeAlongHorizontalLine(Matrix matrix) {
        res = Matrix.initialize(matrix.getRowCount(), matrix.getColumnCount());

        for (int j = 0; j < matrix.getColumnCount(); j++) {
            for (int i = 0; i < matrix.getRowCount(); i++) {
                res.set(matrix.getRowCount() - 1 - i, j, matrix.get(i, j));
            }
        }

        return res;
    }

    private static Matrix transposeAlongVerticalLine(Matrix matrix) {
        res = Matrix.initialize(matrix.getRowCount(), matrix.getColumnCount());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                res.set(i, matrix.getColumnCount() - 1 - j, matrix.get(i, j));
            }
        }

        return res;
    }

    private static Matrix transposeAlongSideDiagonal(Matrix matrix) {
        res = transposeAlongMainDiagonal(matrix);
        res = transposeAlongHorizontalLine(res);
        return transposeAlongVerticalLine(res);
    }

    private static Matrix transposeAlongMainDiagonal(Matrix matrix) {
        res = Matrix.initialize(matrix.getColumnCount(), matrix.getRowCount());

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                res.set(j, i, matrix.get(i, j));
            }
        }

        return res;
    }
}
