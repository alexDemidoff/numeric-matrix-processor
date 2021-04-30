package processor;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int n;
    private static int m;

    public static void main(String[] args) {

        int userChoice;
        do {
            printMainMenu();
            System.out.print("Your choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    addMatrices();
                    break;
                case 2:
                    multiplyByConstant();
                    break;
                case 3:
                    multiplyMatrices();
                    break;
                case 4:
                    transposeMatrix();
                    break;
                case 5:
                    calculateDeterminant();
                    break;
                case 6:
                    inverseMatrix();
                    break;
            }
        } while (userChoice != 0);
    }

    private static void inverseMatrix() {
        System.out.print("Enter matrix size: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix matrix = Matrix.initialize(n, m);

        System.out.println("Enter matrix:");
        matrix.read();

        MatrixProcessor.inverseMatrix(matrix);
    }

    private static void calculateDeterminant() {
        System.out.print("Enter matrix size: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix matrix = Matrix.initialize(n, m);

        System.out.println("Enter matrix:");
        matrix.read();

        System.out.println("The result is: ");
        double det = MatrixProcessor.calculateDeterminant(matrix);
        if (det % 1 == 0) {
            System.out.println((int) det);
        } else {
            System.out.println(det);
        }
    }

    private static void transposeMatrix() {
        printTransposeMenu();

        System.out.print("Your choice: ");
        int userChoice = scanner.nextInt();

        System.out.print("Enter matrix size: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix matrix = Matrix.initialize(n, m);

        System.out.println("Enter matrix:");
        matrix.read();

        System.out.println("The result is:");
        MatrixProcessor.transpose(matrix, userChoice).print();
    }

    private static void multiplyMatrices() {
        System.out.print("Enter size of first matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix a = Matrix.initialize(n, m);

        System.out.println("Enter first matrix:");
        a.read();

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix b = Matrix.initialize(n, m);

        System.out.println("Enter second matrix:");
        b.read();

        try {
            MatrixProcessor.multiply(a, b).print();
        } catch (WrongDimensionsException e) {
            System.out.println("ERROR");
        }
    }

    private static void multiplyByConstant() {
        System.out.print("Enter size of matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix matrix = Matrix.initialize(n, m);

        System.out.println("Enter matrix");
        matrix.read();

        System.out.print("Enter constant: ");
        double c = scanner.nextDouble();

        System.out.println("The result is:");
        MatrixProcessor.multiplyByNumber(c, matrix).print();
    }

    private static void addMatrices() {
        System.out.print("Enter size of first matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix a = Matrix.initialize(n, m);

        System.out.println("Enter first matrix:");
        a.read();

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix b = Matrix.initialize(n, m);

        System.out.println("Enter second matrix:");
        b.read();

        try {
            MatrixProcessor.add(a, b).print();
        } catch (WrongDimensionsException e) {
            System.out.println("The operation cannot be performed.");
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    private static void printTransposeMenu() {
        System.out.println();
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }
}
