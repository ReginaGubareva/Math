import java.util.Scanner;

public class MatrixCalculator {

    public static void main(String[] args) {

        int operation = 1;
        while (operation != 0) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");

            Scanner sc = new Scanner(System.in);
            operation = sc.nextInt();
            switch (operation) {
                case (1):
                    System.out.println("Your choice: 1");
                    System.out.println("Enter size of first matrix: ");
                    int n1 = sc.nextInt();
                    int m1 = sc.nextInt();
                    double[][] matrix1 = new double[n1][m1];
                    System.out.println("Enter first matrix: ");
                    readMatrix(matrix1, sc);
                    System.out.println("Enter size of second matrix:");
                    int n2 = sc.nextInt();
                    int m2 = sc.nextInt();
                    if (n1 != n2 & m1 != m2) {
                        System.out.println();
                        System.out.println("ERROR");
                    } else {
                        double[][] matrix2 = new double[n2][m2];
                        System.out.println("Enter second matrix: ");
                        readMatrix(matrix2, sc);
                        System.out.println("The sum result is: ");
                        printArray(sum(matrix1, matrix2));
                        System.out.println();
                    }
                    break;
                case (2):
                    System.out.println("Your choice: 2");
                    System.out.println("Enter size of matrix: ");
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    double[][] matrix = new double[n][m];
                    System.out.println("Enter matrix: ");
                    readMatrix(matrix, sc);
                    System.out.println("Enter number:");
                    double C = sc.nextDouble();
                    System.out.println("The multiplication to number result is: ");
                    muxToNumber(matrix, C);
                    printArray(matrix);
                    System.out.println();
                    break;
                case (3):
                    System.out.println("Your choice: 3");
                    System.out.println("Enter size of first matrix: ");
                    n1 = sc.nextInt();
                    m1 = sc.nextInt();
                    matrix1 = new double[n1][m1];
                    System.out.println("Enter first matrix: ");
                    readMatrix(matrix1, sc);
                    System.out.println("Enter size of second matrix:");
                    n2 = sc.nextInt();
                    m2 = sc.nextInt();
                    if (n1 != m2 & m1 != n2) {
                        System.out.println();
                        System.out.println("ERROR");
                    } else {
                        double[][] matrix2 = new double[n2][m2];
                        System.out.println("Enter second matrix: ");
                        readMatrix(matrix2, sc);
                        System.out.println("The multiplication result is: ");
                        printArray(mux(matrix1, matrix2));
                    }
                    System.out.println();
                    break;
                case (4):
                    transpose(sc);
                    System.out.println();
                    break;
                case (5):
                    det(sc);
                    System.out.println();
                    break;
                case(6):
                    System.out.println("Your choice: 6");
                    System.out.println("Enter matrix size: ");
                    n = sc.nextInt();
                    m = sc.nextInt();
                    System.out.println("Enter matrix: ");
                    double[][] A = new double[n][m];
                    readMatrix(A, sc);
                    System.out.println();
                    System.out.println("The result is: ");
                    printArray(inverse(A));
                    System.out.println();
                    System.out.println();
                    break;
                case (0):
                    System.out.println("Your choice: 0");
                    break;
                default:
                    break;
            }
        }
    }

    public static void readMatrix(double[][] matrix, Scanner sc) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = sc.nextDouble();
            }
        }
    }

    private static double[][] inverse(double[][] matrix) {
        double[][] inverse = new double[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(minor(matrix, i, j));

        // adjugate and determinant
        double det = 1.0 / determinant(matrix);
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    private static double[][] minor(double[][] matrix, int row, int column) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return minor;
    }

    public static void det(Scanner sc){
        System.out.println("Your choice: 5");
        System.out.println("Enter matrix size: ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println("Enter matrix: ");
        double[][] A = new double[n][n];
        readMatrix(A, sc);
        System.out.println();
        System.out.println("The result is: ");
        System.out.println(determinant(A));
        System.out.println();
    }

    public static double determinant(double[][] matrix) {
        int sign = 0;
        int n = matrix.length;
        for (int k = 1; k < n; k++) {
            if(matrix[k-1][k-1] == 0 ){
                sign = swapRows(matrix, n, k-1, k, sign);
            }
            for (int j = k; j < n; j++) {
                double m = matrix[j][k - 1] / matrix[k - 1][k - 1];
                for (int i = 0; i < n; i++) {
                    matrix[j][i] = matrix[j][i] - m * matrix[k - 1][i];
                }
            }
        }

        double det = 1.0;
        for (int i = 0; i < n; i++) {
            det *= matrix[i][i];
        }

        if (sign % 2 != 0) {
            det = -det;
        }
        return det;
    }

    static int swapRows (double[][] A, int n, int row, int rowNext, int sign){
        sign++;
        for(int i = 0; i < n; i++){
            double temp = A[rowNext][i];
            A[rowNext][i] = A[row][i];
            A[row][i] = temp;
        }
        return sign;
    }


    public static void transpose(Scanner sc){
        System.out.println("Your choice: 4");
        System.out.println();
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");

        int type = -1;
        type = sc.nextInt();

        switch (type){
            case (1):
                System.out.println("Your choice: 1");
                System.out.println("Enter matrix size: ");
                int n = sc.nextInt();
                int m = sc.nextInt();
                System.out.println("Enter matrix: ");
                double[][] matrix = new double[n][m];
                readMatrix(matrix, sc);
                System.out.println("Result is: ");
                printArray(transposeMainDiagonal(matrix, n, m));
                System.out.println();
                break;
            case (2):
                System.out.println("Your choice: 2");
                System.out.println("Enter matrix size: ");
                n = sc.nextInt();
                m = sc.nextInt();
                System.out.println("Enter matrix: ");
                matrix = new double[n][m];
                readMatrix(matrix, sc);
                System.out.println("Result is: ");
                printArray(transposeSideDiagonal(matrix, n, m));
                System.out.println();
                break;
            case (3):
                System.out.println("Your choice: 3");
                System.out.println("Enter matrix size: ");
                n = sc.nextInt();
                m = sc.nextInt();
                System.out.println("Enter matrix: ");
                matrix = new double[n][m];
                readMatrix(matrix, sc);
                System.out.println("Result is: ");
                printArray(transposeVerticalLine(matrix, n, m));
                break;
            case (4):
                System.out.println("Your choice: 4");
                System.out.println("Enter matrix size: ");
                n = sc.nextInt();
                m = sc.nextInt();
                System.out.println("Enter matrix: ");
                matrix = new double[n][m];
                readMatrix(matrix, sc);
                System.out.println("Result is: ");
                printArray(transposeHorizontalLine(matrix, n, m));
                System.out.println();
                break;
            default:
                break;
        }
    }

    public static double[][] transposeMainDiagonal(double[][] matrix, int n, int m){
        double[][] transpose = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    public static double[][] transposeSideDiagonal(double[][] matrix, int n, int m){
        double[][] transpose = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                transpose[j][i] = matrix[n-1-i][m-1-j];
            }
        }
        return transpose;
    }

    public static double[][] transposeVerticalLine(double[][] matrix, int n, int m){
        double[][] transpose = new double[m][n];
        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                transpose[i][j] = matrix[i][m-1-j];
            }
        }
        return transpose;
    }

    public static double[][] transposeHorizontalLine(double[][] matrix, int n, int m){
        double[][] transpose = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                transpose[i][j] = matrix[n-1-i][j];
            }
        }
        return transpose;
    }

    public static double[][] mux(double[][] matrix1, double[][] matrix2) {
        double[][] mux = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    mux[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return mux;
    }

    public static void muxToNumber(double[][] matrix, double C) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = matrix[row][col] * C;
            }
        }
    }

        public static double[][] sum( double[][] matrix1, double[][] matrix2) {
            double[][] sum = new double[matrix1.length][matrix1[0].length];
            for (int row = 0; row < matrix1.length; row++) {
                for (int col = 0; col < matrix1[0].length; col++) {
                    sum[row][col] = matrix1[row][col] + matrix2[row][col];
                }
            }
            return sum;
        }

        public static void printArray(double[][] matrix){
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    System.out.printf("%.2f", matrix[row][col]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
}





/*
1 2 3 4 5
4 5 6 4 3
0 0 0 1 5
1 3 9 8 7
5 8 4 7 11

1 7 7
6 6 4
4 2 1


first
0,65 0,67 76,4 23,2
-0,7 -13,1 -7,2 9,2
-0,7 -5,5 -1,5 0,4
-1,0 12,6 0,8 -0,4

second
-5,5 -0,3 -1,2 10,2
-1,0 0,8 0,8 -9,5
-45,5 45,5 56,5 13,7
-10,7 11,9 2,2 1,2

result
-3728.69 3752.62 4367.40 1074.79
246.11 -228.39 -396.20 29.71
73.32 -67.68 -87.43 25.04
-39.22 42.02 55.60 -119.42

first:
3 4 55
4 1 1
9 0 0

second:
4 9 77
13 22 44
56 57 78

result:
7.00 13.00 132.00
17.00 23.00 45.00
65.00 57.00 78.00
 */
