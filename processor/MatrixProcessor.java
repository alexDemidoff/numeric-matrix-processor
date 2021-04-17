package processor;

public class MatrixProcessor {

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
}
