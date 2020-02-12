import java.util.Arrays;
import java.util.Scanner;

/**
 * Дана квадратная матрица. Найдите определитель данной матрицы.
 *В первой строке задается число n(1≤n≤12). Следующие n строк содержат
 * n^2 целых чисел от −5 до 5 (по n чисел в каждой строке) — элементы матрицы.
 * Выведите одно целое число — определитель данной матрицы.
 * Не проходит 21ый тест wrong answer.
 */

public class DetBareiss {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[][] A = new double[n][n];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                A[i][j] = sc.nextDouble();
            }
        }

        System.out.println(Math.round(bareiss(A, n)));
    }

    static double bareiss(double[][] matrix, int n){
        int sign = 0;

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

    static void printArray(double[][] A){
        int n = A.length;
        for(int i = 0; i < n; i++){
            System.out.println();
            for (int j = 0; j < n; j++){
                System.out.print(A[i][j] + " ");
            }
        }
    }

//    public static double determinant(double[][] matrix) {
//        double det = 0;
//        int sign = 0;
//        if (matrix.length == 1) {
//            return matrix[0][0];
//        } else {
//            for (int i = 0; i < matrix.length; i++) {
//                if (i % 2 == 1) {
//                    sign = -1;
//                } else {
//                    sign = 1;
//                }
//                det = det + sign * matrix[i][0] * determinant(getTruncateMatrix(matrix, i, 0));
//            }
//            return det;
//        }
//    }
//
//    public static double[][] getTruncateMatrix(double[][] matrix, int i, int j) {
//        int size = matrix.length - 1;
//        int new_i = 0;
//        int new_j = 0;
//        double[][] newMatrix = new double[size][size];
//        for (int ii = 0; ii <= size; ii++) {
//            if (ii != i) {
//                double[] newLine = new double[size];
//                new_j = 0;
//                for (int jj = 0; jj <= size; jj++) {
//                    if (jj != j) {
//                        newLine[new_j] = matrix[ii][jj];
//                        new_j++;
//                    }
//                }
//                newMatrix[new_i] = newLine;
//                new_i++;
//            }
//        }
//        return newMatrix;
//    }

}

/*
&& matrix[k][k-1] != 0

3
0 2 1
-2 8 1
9 1 3
-44

4
0 1 1 1
1 1 1 0
1 1 1 1
1 1 0 0
-1

2
4 2
5 3
2

1
1

1
0

2
0 2
0 0
0

2
0 2
0 3
0

2
0 2
5 0
-10

3
3 0 2
0 1 4
1 1 1
-11

3
0 5 -4
3 2 1
2 -4 5
-1

3
0 0 0
1 0 1
0 1 1
0

3
-1 1 0
0 0 -1
1 -1 1
0

3
0 0 0
0 1 1
1 0 1
0

3
0 1 0
-1 0 1
1 0 0
1

3
0 0 0
0 1 0
0 1 0
0

3
-1 0 1
0 0 -1
0 0 0
0

4
0 1 1 1
1 1 1 0
1 1 1 1
1 1 0 0
-1


4
-1 0 0 1
0 0 1 1
0 -1 1 0
-1 -1 -1 0
-1

4
1 0 0 1
0 1 1 1
1 1 0 1
1 0 1 0
2

4
1 1 -1 -1
0 1 -1 1
0 1 -1 -1
1 -1 0 0
-2

4
0 0 0 1
0 1 0 1
0 0 0 0
1 1 0 1
0

4
-1 -1 1 1
1 -1 1 0
1 0 0 -1
1 0 -1 0
-1
*/

//        for (int j = 0; j < n; j++) {
//            for(int i = j+1; i < n; i++) {
//                for(int k = 0; k < n; k++){
//                    matrix[i][k] = (matrix[j][j]*matrix[i][k] - matrix[i][j]*matrix[j][k])/matrix[j][j];
//                }
//
//            }
//        }
//



