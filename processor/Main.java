package processor;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static int n;
    private static int m;

    public static void main(String[] args) {

        int userChoice;
        do {
            printMenu();
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
            }
        } while (userChoice != 0);
    }

    private static void multiplyMatrices() {
        System.out.print("Enter size of first matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix a = Matrix.initialize(n, m);

        System.out.println("Enter first matrix:");
        a.readMatrix();

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix b = Matrix.initialize(n, m);

        System.out.println("Enter second matrix:");
        b.readMatrix();

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
        matrix.readMatrix();

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
        a.readMatrix();

        System.out.print("Enter size of second matrix: ");
        n = scanner.nextInt();
        m = scanner.nextInt();
        Matrix b = Matrix.initialize(n, m);

        System.out.println("Enter second matrix:");
        b.readMatrix();

        try {
            MatrixProcessor.add(a, b).print();
        } catch (WrongDimensionsException e) {
            System.out.println("The operation cannot be performed.");
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");
    }
}
