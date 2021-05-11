package processor;

public interface EnterMatrix {
    int MAIN_DIAGONAL = 1;
    int SIDE_DIAGONAL = 2;
    int VERTICAL_LINE = 3;
    int HORIZONTAL_LINE = 4;

    Matrix add(Matrix m);

    Matrix multiplyBy(double c);

    Matrix multiplyBy(Matrix m);

    Matrix transpose(int mode);

    double calculateDeterminant();

    Matrix inverse();
}
