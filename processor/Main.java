package processor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Matrix> matrices = new ArrayList<>();
    private static final Status status = new Status();
    private static Action nextAction;
    private static Operation performedOperation;
    private static Matrix currentMatrix;

    private static int transpositionMode;
    private static int currentRowIndex = 0;
    private static int currentMatrixIndex = 0;

    public static void main(String[] args) {
        nextAction = Action.USER_CHOOSES_MAIN_MENU_ITEM;
        printMainMenu();

        do {
            //System.out.println(nextAction);//debug
            takeAction(scanner.nextLine());
        } while (nextAction != Action.EXIT);
    }

    private static void takeAction(String line) {
        if (nextAction == Action.USER_CHOOSES_MAIN_MENU_ITEM) {
            setOperation(Integer.parseInt(line));
            matrices.clear();
        }

        switch (performedOperation) {
            case MATRIX_ADDITION:
            case MATRIX_MULTIPLICATION:
                switch (nextAction) {
                    case USER_CHOOSES_MAIN_MENU_ITEM:
                        currentMatrixIndex = 1;

                        nextAction = Action.USER_ENTERS_MATRIX_SIZE;
                        System.out.print("Enter size of first matrix: ");
                        break;
                    case USER_ENTERS_MATRIX_SIZE:
                        currentMatrix = initialize(line);

                        currentRowIndex = 0;

                        nextAction = Action.USER_ENTERS_MATRIX;
                        System.out.println(currentMatrixIndex < 2 ? "Enter first matrix:" : "Enter second matrix:");
                        break;
                    case USER_ENTERS_MATRIX:
                        if (currentRowIndex < currentMatrix.getRowCount()) {
                            String[] row = line.split(" ");
                            currentMatrix.setRow(currentRowIndex, row);

                            if (currentRowIndex == currentMatrix.getRowCount() - 1) {
                                matrices.add(currentMatrix);

                                if (currentMatrixIndex < 2) {
                                    currentMatrixIndex++;

                                    nextAction = Action.USER_ENTERS_MATRIX_SIZE;
                                    System.out.println("Enter size of second matrix: ");
                                } else {
                                    if (performedOperation == Operation.MATRIX_ADDITION) {
                                        printResult(matrices.get(0).add(matrices.get(1), status));
                                    } else {
                                        printResult(matrices.get(0).multiplyBy(matrices.get(1), status));
                                    }

                                    nextAction = Action.USER_CHOOSES_MAIN_MENU_ITEM;
                                    printMainMenu();
                                }
                            }

                            currentRowIndex++;
                        }
                        break;
                }
                break;

            case MULTIPLYING_BY_CONSTANT:
                switch (nextAction) {
                    case USER_CHOOSES_MAIN_MENU_ITEM:
                        nextAction = Action.USER_ENTERS_MATRIX_SIZE;
                        System.out.print("Enter matrix size: ");
                        break;
                    case USER_ENTERS_MATRIX_SIZE:
                        currentMatrix = initialize(line);

                        currentRowIndex = 0;

                        nextAction = Action.USER_ENTERS_MATRIX;
                        System.out.println("Enter matrix:");
                        break;
                    case USER_ENTERS_MATRIX:
                        if (currentRowIndex < currentMatrix.getRowCount()) {
                            String[] row = line.split(" ");
                            currentMatrix.setRow(currentRowIndex, row);

                            if (currentRowIndex == currentMatrix.getRowCount() - 1) {
                                matrices.add(currentMatrix);

                                nextAction = Action.USER_ENTERS_CONSTANT;
                                System.out.print("Enter constant: ");
                            }

                            currentRowIndex++;
                        }
                        break;
                    case USER_ENTERS_CONSTANT:
                        double constant = Double.parseDouble(line);

                        printResult(matrices.get(0).multiplyBy(constant, status));

                        nextAction = Action.USER_CHOOSES_MAIN_MENU_ITEM;
                        printMainMenu();
                        break;
                }
                break;

            case TRANSPOSING:
                switch (nextAction) {
                    case USER_CHOOSES_MAIN_MENU_ITEM:
                        nextAction = Action.USER_CHOOSES_TRANSPOSE_MENU_ITEM;
                        printTransposeMenu();
                        break;
                    case USER_CHOOSES_TRANSPOSE_MENU_ITEM:
                        setTranspositionMode(Integer.parseInt(line));

                        nextAction = Action.USER_ENTERS_MATRIX_SIZE;
                        System.out.print("Enter matrix size: ");
                        break;
                    case USER_ENTERS_MATRIX_SIZE:
                        currentMatrix = initialize(line);

                        currentRowIndex = 0;

                        nextAction = Action.USER_ENTERS_MATRIX;
                        System.out.println("Enter matrix:");
                        break;
                    case USER_ENTERS_MATRIX:
                        if (currentRowIndex < currentMatrix.getRowCount()) {
                            String[] row = line.split(" ");
                            currentMatrix.setRow(currentRowIndex, row);

                            if (currentRowIndex == currentMatrix.getRowCount() - 1) {
                                matrices.add(currentMatrix);

                                printResult(matrices.get(0).transpose(transpositionMode, status));

                                nextAction = Action.USER_CHOOSES_MAIN_MENU_ITEM;
                                printMainMenu();
                            }

                            currentRowIndex++;
                        }
                        break;
                }
                break;

            case CALCULATING_DETERMINANT:
            case INVERTING:
                switch (nextAction) {
                    case USER_CHOOSES_MAIN_MENU_ITEM:
                        nextAction = Action.USER_ENTERS_MATRIX_SIZE;
                        System.out.print("Enter matrix size: ");
                        break;
                    case USER_ENTERS_MATRIX_SIZE:
                        currentMatrix = initialize(line);

                        currentRowIndex = 0;

                        nextAction = Action.USER_ENTERS_MATRIX;
                        System.out.println("Enter matrix:");
                        break;
                    case USER_ENTERS_MATRIX:
                        if (currentRowIndex < currentMatrix.getRowCount()) {
                            String[] row = line.split(" ");
                            currentMatrix.setRow(currentRowIndex, row);

                            if (currentRowIndex == currentMatrix.getRowCount() - 1) {
                                matrices.add(currentMatrix);

                                if (performedOperation == Operation.CALCULATING_DETERMINANT) {
                                    printResult(matrices.get(0).calculateDeterminant());
                                } else {
                                    printResult(matrices.get(0).inverse(status));
                                }

                                nextAction = Action.USER_CHOOSES_MAIN_MENU_ITEM;
                                printMainMenu();
                            }

                            currentRowIndex++;
                        }
                        break;
                }
                break;

            case EXIT:
                nextAction = Action.EXIT;
                break;
        }
    }

    private static Matrix initialize(String line) {
        String[] sizeStr = line.split(" ");
        int n = Integer.parseInt(sizeStr[0]);
        int m = Integer.parseInt(sizeStr[1]);

        return Matrix.initialize(n, m);
    }

    private static void setTranspositionMode(int item) {
        switch (item) {
            case 1:
                transpositionMode = Matrix.MAIN_DIAGONAL;
                break;
            case 2:
                transpositionMode = Matrix.SIDE_DIAGONAL;
                break;
            case 3:
                transpositionMode = Matrix.VERTICAL_LINE;
                break;
            case 4:
                transpositionMode = Matrix.HORIZONTAL_LINE;
                break;
        }
    }

    private static void setOperation(int item) {
        switch (item) {
            case 1:
                performedOperation = Operation.MATRIX_ADDITION;
                break;

            case 2:
                performedOperation = Operation.MULTIPLYING_BY_CONSTANT;
                break;

            case 3:
                performedOperation = Operation.MATRIX_MULTIPLICATION;
                break;

            case 4:
                performedOperation = Operation.TRANSPOSING;
                break;

            case 5:
                performedOperation = Operation.CALCULATING_DETERMINANT;
                break;

            case 6:
                performedOperation = Operation.INVERTING;
                break;

            case 0:
                performedOperation = Operation.EXIT;
                break;
        }
    }

    private static void printResult(double result) {
        System.out.println("The result is:");
        System.out.println(result);
    }

    private static void printResult(Matrix result) {
        if (status.isSuccess()) {
            System.out.println("The result is:");
            result.print();
        } else {
            System.out.println(status.getMessage());
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
