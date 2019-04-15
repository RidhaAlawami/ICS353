public class MatrixMultiplication {

	public MatrixMultiplication() {
		
	}
	
	
	// matrix iterative
	public int[][] IterativeMultiplication(int [][] matrixA, int [][] matrixB) {
		int n = matrixA.length;
		int [][] matrixC = new int[n][n];
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				matrixC[row][column] = 0;
				for (int k = 0; k < n; k++) {
					matrixC[row][column] += matrixA[row][k] * matrixB[k][column];
				}
			}
		}

		return matrixC;
	}

	/// Strassen base 1
	public int[][] StrassenB1(int [][] matrixA, int [][] matrixB) {
		
		int n = matrixA.length;
		
		int[][] matrixC = new int[n][n];
		
		
		if (n == 1){
			matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
		}
		else {
			//step 1
			//matrix A
			int[][] A11 = new int[n/2][n/2];
			int[][] A12 = new int[n/2][n/2];
			int[][] A21 = new int[n/2][n/2];
			int[][] A22 = new int[n/2][n/2];
			
			//matrix B
			int[][] B11 = new int[n/2][n/2];
			int[][] B12 = new int[n/2][n/2];
			int[][] B21 = new int[n/2][n/2];
			int[][] B22 = new int[n/2][n/2];
			
			//fill the matrices AXX
			split(matrixA, A11, 0, 0);
			split(matrixA, A12, 0, n/2);
			split(matrixA, A21, n/2, 0);
			split(matrixA, A22, n/2, n/2);
		

			//fill the matrices BXX
			split(matrixB, B11, 0, 0);
			split(matrixB, B12, 0,  n/2);
			split(matrixB, B21, n/2, 0);
			split(matrixB, B22, n/ 2, n/2);
			

			// step 2
			//create 10 matrices 
			int[][] S1 = subtract(B12, B22);
			int[][] S2 = add(A11, A12);
			int[][] S3 = add(A21, A22); //here * B11
			int[][] S4 = subtract(B21, B11);
			int[][] S5 = add(A11, A22); //here
			int[][] S6 = add(B11, B22); //here - S5 * S6
			int[][] S7 = subtract(A12, A22);
			int[][] S8 = add(B21, B22);
			int[][] S9 = subtract(A21, A11);
			int[][] S10 = add(B11, B12);
			
			
			// step 3
			int[][] P5 = StrassenB1(S5, S6);
			int[][] P3 = StrassenB1(S3, B11);
			int[][] P1 = StrassenB1(A11, S1);
			int[][] P4 = StrassenB1(A22, S4);
			int[][] P2 = StrassenB1(S2, B22);
			int[][] P7 = StrassenB1(S9, S10);
			int[][] P6 = StrassenB1(S7, S8);
			
			
			
			int[][] C11 = add(subtract(add(P5,P4),P2),P6);
			int[][] C12 = add(P1, P2);
			int[][] C21 = add(P3, P4);
			int[][] C22 = add(subtract(add(P5,P1),P3),P7);

			// step 5
			join(C11, matrixC, 0, 0);
			join(C12, matrixC, 0, n/2);
			join(C21, matrixC, n/2,0);
			join(C22, matrixC, n/2, n/2);
		}
		return matrixC;
	}

	// Strassen Bigger than 1
	public int[][] Strassen(int matrixA[][], int matrixB[][], int baseCase) {
		
		// matrix dimension
		int n = matrixA.length;
		
		int [][] matrixC = new int[n][n];
		
		if (n <= baseCase)
			matrixC = IterativeMultiplication(matrixA,matrixB);
		else {
			//step 1
			//matrix A
			int[][] A11 = new int[n/2][n/2];
			int[][] A12 = new int[n/2][n/2];
			int[][] A21 = new int[n/2][n/2];
			int[][] A22 = new int[n/2][n/2];
			
			//matrix B
			int[][] B11 = new int[n/2][n/2];
			int[][] B12 = new int[n/2][n/2];
			int[][] B21 = new int[n/2][n/2];
			int[][] B22 = new int[n/2][n/2];
			
			//fill the matrices AXX
			split(matrixA, A11, 0, 0);
			split(matrixA, A12, 0, n/2);
			split(matrixA, A21, n/2, 0);
			split(matrixA, A22, n/2, n/2);
		

			//fill the matrices BXX
			split(matrixB, B11, 0, 0);
			split(matrixB, B12, 0,  n/2);
			split(matrixB, B21, n/2, 0);
			split(matrixB, B22, n/ 2, n/2);

			// step 2
			
			//create 10 matrices
			int[][] S1 = subtract(B12, B22);
			int[][] S2 = add(A11, A12);
			int[][] S3 = add(A21, A22);
			int[][] S4 = subtract(B21, B11);
			int[][] S5 = add(A11, A22);
			int[][] S6 = add(B11, B22);
			int[][] S7 = subtract(A12, A22);
			int[][] S8 = add(B21, B22);
			int[][] S9 = subtract(A21, A11);
			int[][] S10 = add(B11, B12);
			
			// step 3
			int[][] P5 = Strassen(S5, S6,baseCase);
			int[][] P3 = Strassen(S3, B11,baseCase);
			int[][] P1 = Strassen(A11, S1,baseCase);
			int[][] P4 = Strassen(A22, S4,baseCase);
			int[][] P2 = Strassen(S2, B22,baseCase);
			int[][] P7 = Strassen(S9, S10,baseCase);
			int[][] P6 = Strassen(S7, S8,baseCase);
						
						
						
			int[][] C11 = add(subtract(add(P5,P4),P2),P6);
			int[][] C12 = add(P1, P2);
			int[][] C21 = add(P3, P4);
			int[][] C22 = add(subtract(add(P5,P1),P3),P7);

			// step 5
			join(C11, matrixC, 0, 0);
			join(C12, matrixC, 0, n / 2);
			join(C21, matrixC, n / 2, 0);
			join(C22, matrixC, n / 2, n / 2);
		}
		
		
		
		return matrixC;
	}

	

	// add matrices
	private int[][] add(int [][] matrix1, int [][] matrix2) {

		int n = matrix1.length;
		int [][] resultMatrix = new int[n][n];

		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				resultMatrix[row][column] = matrix1[row][column] + matrix2[row][column];
			}
		}

		return resultMatrix;

	}

	// subtract matrices
	private int[][] subtract(int[][] matrix1, int[][] matrix2) {

		int n = matrix1.length;
		int[][] resultMatrix = new int[n][n];

		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				resultMatrix[row][column] = matrix1[row][column] - matrix2[row][column];
			}
		}

		return resultMatrix;

	}
	

	
	//split the matrix
    public void split(int[][] original, int[][] split, int rowSplit, int columnSplit) 
    {
        for(int row = 0, row2 = rowSplit; row < split.length; row++, row2++)
            for(int column = 0, column2 = columnSplit; column < split.length; column++, column2++)
                split[row][column] = original[row2][column2];
    }
    
    //join the splits 
    public void join(int[][] split, int[][] original, int rowSplit, int columnSplit) 
    {
        for(int row = 0, row2 = rowSplit; row < split.length; row++, row2++)
            for(int column = 0, column2 = columnSplit; column < split.length; column++, column2++)
                original[row2][column2] = split[row][column];
    } 

//
//	// split matrix
//	private void split(int[][] original, int[][] split, int rowSplit, int columnSplit) {
//		for (int row = 0, row2 = rowSplit; row < split.length; row++, row2++)
//			for (int column = 0, column2 = columnSplit; column < split.length; column++, column2++) {
//				split[row][column] = original[row2][column2];
//			}
//	}
//
//	// join matrices
//	private void join(int[][] split, int[][] original, int rowSplit, int columnSplit) {
//		for (int row = 0, row2 = rowSplit; row < split.length; row++, row2++){
//			for (int column = 0, column2 = columnSplit; column < split.length; column++, column2++) {
//				original[row2][column2] = split[row][column];
//			}
//		}
//	}

}
