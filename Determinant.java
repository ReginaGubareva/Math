import java.util.Scanner;

public class Determinant {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[][] A = new double[n][n];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                A[i][j] = sc.nextDouble();
            }
        }

        for(int i = 0; i < n; i++){
            System.out.println();
            for (int j = 0; j < n; j++){
                System.out.print(A[i][j] + " ");
            }
        }

        System.out.println();
        System.out.println("Det:" + Math.round(det(A, n)));
    }

    static double det (double[][] a, int n){
        int D = 0;
        if(n == 1){
            return a[0][0];
        } else  if(n == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        } else {
            int sign = 1;
            for (int i = 0; i < a.length; i++){
                double[][] minor = getMinor(a,0, i, n);
                D += a[0][i]*sign*det(minor, n - 1);
                sign = -sign;
            }
            return D;
        }
    }

    static double[][] getMinor(double[][] a, int p, int q, int n){
        double[][] temp = new double[n][n];
        int i = 0, j = 0;
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                if(row != p & col != q){
                    temp[i][j++] = a[row][col];
                }
                if (j == n - 1)
                {
                    j = 0;
                    i++;
                }
            }
        }
        return temp;
    }

}
