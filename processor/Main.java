package processor;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Matrix a = new Matrix();
        Matrix b = new Matrix();
        Matrix sum;

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        a.initialize(n, m);
        a.readMatrix();

        n = scanner.nextInt();
        m = scanner.nextInt();

        b.initialize(n, m);
        b.readMatrix();

        try {
            sum = MatrixProcessor.add(a, b);
            sum.print();
        } catch (DifferentDimensionsException e) {
            System.out.println("ERROR");
        }
    }
}
