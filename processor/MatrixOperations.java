package processor;

public interface MatrixOperations {
    int MAIN_DIAGONAL = 1;
    int SIDE_DIAGONAL = 2;
    int VERTICAL_LINE = 3;
    int HORIZONTAL_LINE = 4;

    Matrix add(Matrix m, Status status);

    Matrix multiplyBy(double c, Status status);

    Matrix multiplyBy(Matrix m, Status status);

    Matrix transpose(int mode, Status status);

    double calculateDeterminant();

    Matrix inverse(Status status);
}
