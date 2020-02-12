import java.util.*;

public class Gauss2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int mon = sc.nextInt() + 1;
        double[][] matrix = new double[rows][mon];
        double[] ans = new double[mon - 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < mon; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        processing(matrix, 0, 0);
        for (int i = 0; i < matrix.length; i++) {
            int f = 0;
            for (int j = 0 ; j < mon; j++) {
                f = Math.abs(matrix[i][j]) < Math.pow(10, -12) ? f+1 : f;
            }
            if (f == mon) rows--;
        }
        double [][] m = new double[rows][mon];
        System.arraycopy(matrix, 0, m, 0, rows);
        if ((m[rows - 1][mon - 2] == 0 && Math.abs(m[rows - 1][mon - 1]) > Math.pow(10, -12)) || rows > mon) {
            System.out.println("NO");
        } else if (rows < mon - 1) {
            System.out.println("INF");
        } else {
            System.out.println("YES");
            for (int i = rows - 1; i >= 0; i--) {
                ans[i] = m[i][rows]/m[i][i];
                for (int r = i - 1; r >= 0; r--) {
                    m[r][rows] -= ans[i]*m[r][i];
                    m[r][i] = 0;
                }
            }
            for (int i = 0; i < ans.length; i++) {
                System.out.print(ans[i] + " ");
            }
        }
    }

    public static void swapRows(double [][] m, int zeroRow, int zeroCol) {
        for (int i = zeroRow + 1; i < m.length; i++) {
            if (m[i][zeroCol] != 0) {
                double [] t = m[zeroRow];
                m[zeroRow] = m[i];
                m[i] = t;
                break;
            }
        }
    }

    public static int processing(double [][] m, int row, int col) {
        if (row >= m.length - 1 || col >= m[0].length - 1) return 1;
        if (m[row][col] == 0) swapRows(m, row, col);
        if (m[row][col] == 0) return processing(m, row, col + 1);
        for (int i = row + 1; i < m.length; i++) {
            if (m[i][col] != 0) {
                double k = m[row][col]/m[i][col];
                m[i][col] = 0;
                for (int a = col + 1; a < m[0].length; a++) {
                    m[i][a] = m[row][a] - m[i][a] * k;
                }
            }
        }
        return processing(m, row + 1, col + 1);
    }
}