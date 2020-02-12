import java.util.*;
import java.lang.*;
import java.math.BigDecimal;

class Gauss{
	public static void main(String[] args){
		
		//Read expand matrix of system
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		double[][] exMatrix = new double[100][100];
		double[] x = new double[m]; 
		
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m+1; j++){
				exMatrix[i][j] = in.nextDouble();
			}
		}
			
		double[][] exMatrixTrapeziodal = trapeziodal(exMatrix, n, m);

		int flag = 0;
		for(int i = 0; i<n; i++){
			for(int j = 0; j < m+1; j++){
				if(exMatrixTrapeziodal[i][j] == 0.0){
					flag++;
				}
			}
		}
			
		if(flag == n*(m+1)){
			return;
		} else if(n < m){
			System.out.println("INF");
		} else{
			System.out.println("YES");
			
			for(int i = n-1; i>=0; i--){
				double r = 0;
			
				for(int j = x.length-1; j>i; j--){
					r += exMatrixTrapeziodal[i][j]*x[j];
				}
				x[i] = (exMatrixTrapeziodal[i][m] - r)/exMatrixTrapeziodal[i][i];
			}
	
			for(int j = 0; j<x.length; j++){
				System.out.format(x[j] + " ");
			}		
		}
	}	
	
	public static double[][] trapeziodal(double[][] array, int n, int m){
		
		for(int k = 0; k < n; k++){
			int index = k;
			
			//choose max element and swap
			for(int i = k+1; i<n; i++){
				if( Math.abs(array[i][k]) > Math.abs(array[index][k]))
					index = i;	
			}
			
			//sort	
			double[] temp;
     		temp=Arrays.copyOf(array[k], array[k].length);
      		array[k]=Arrays.copyOf(array[index], array[index].length);
      		array[index]=Arrays.copyOf(temp, temp.length);
			
			double[][] nullArray = new double[n][m+1];
			for(int i = 0; i<n; i++){
				for(int j = 0; j < m+1; j++){
					nullArray[i][j] = 0;
				}
			}
			
			 if (Math.abs(array[k][k]) <= 1e-10) {
                System.out.println("NO");
                return nullArray;
            }
			
			//devide row on max elemnt
			double pivot = array[k][k];
			for(int j = 0; j<m+1; j++){
				array[k][j] = array[k][j]/pivot;	
			}
			
			//nullification
			for (int i = 0; i < n; i++)
			{
				if ( k != i ){
					double factor = array[i][k];
			 		for (int j = 0; j<m+1; j++) {
					     array[i][j] = array[i][j] - factor*array[k][j];	
					} 
				}				
			}
		}
		
		return array;
	}
	
	
}

	/*for (int i = 0; i<exMatrixTrapeziodal.length; i++){
			int count = 0;
			for(int j = 0; j < size-1; j++){
				if(exMatrixTrapeziodal[i][j] == 0.0){
					
					count++;
				}
			    if((count == size-1) && (exMatrixTrapeziodal[exMatrixTrapeziodal.length-1][size-1] != 0.0)){
					System.out.println("NO");
					return;
				}
			}
		}*/

/*for(int i = 0; i<exMatrixTrapeziodal.length; i++){
			System.out.println();
			for(int j = 0; j < exMatrixTrapeziodal[0].length; j++){
				System.out.print(exMatrixTrapeziodal[i][j] + " ");
			}
		}
		System.out.println();*/

/*
8 8
5 10 13 8 4 3 2 16 18
17 4 3 3 19 22 16 15 21
4 16 19 11 7 6 18 21 34
25 16 17 0 1 23 17 13 8
27 31 26 23 14 14 18 35 47
47 0 8 13 11 88 46 51 34
0 5 2 43 58 13 14 63 13
1 5 11 28 37 8 13 26 11
*/


/*
yes
3 3
4 2 1 1
7 8 9 1
9 1 3 2

inf
2 3
1 3 4 4
2 1 4 5

no
3 3
1 3 2 7
2 6 4 8
1 4 3 1
*/