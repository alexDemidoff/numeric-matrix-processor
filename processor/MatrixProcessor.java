package processor;

public class MatrixProcessor {

    public static Matrix add(Matrix a, Matrix b) throws DifferentDimensionsException {
        Matrix sum = new Matrix();

        if (a.getRowCount() == b.getRowCount() && a.getColumnCount() == b.getColumnCount()) {
            sum.initialize(a.getRowCount(), a.getColumnCount());

            for (int i = 0; i < sum.getRowCount(); i++) {
                for (int j = 0; j < sum.getColumnCount(); j++) {
                    sum.set(i, j, a.get(i, j) + b.get(i, j));
                }
            }
        } else {
            throw new DifferentDimensionsException();
        }

        return sum;
    }

    public static Matrix multiplyByNumber(int c, Matrix matrix) {
        Matrix res = new Matrix();

        for (int i = 0; i < matrix.getRowCount(); i++) {
            for (int j = 0; j < matrix.getColumnCount(); j++) {
                res.set(i, j, c * matrix.get(i, j));
            }
        }

        return res;
    }
}
