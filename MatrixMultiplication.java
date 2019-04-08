public class MatrixMultiplication {

	// matrcies and the result matrix
	private int matrixSize;

	private int[][] matrixA;
	private int[][] matrixB;
	//private int[][] matrixC;

	// matrix splits
	private int[][] A11;
	private int[][] A12;
	private int[][] A21;
	private int[][] A22;

	// matrix B
	private int[][] B11;
	private int[][] B12;
	private int[][] B21;
	private int[][] B22;

	// 10 matrcies contain the sum and subtract of the above splits
	private int[][] S1;
	private int[][] S2;
	private int[][] S3;
	private int[][] S4;
	private int[][] S5;
	private int[][] S6;
	private int[][] S7;
	private int[][] S8;
	private int[][] S9;
	private int[][] S10;

	public MatrixMultiplication(int[][] matrixA, int[][] matrixB, int matrixSize) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixSize = matrixSize;
	}

	// matrix iterative
	public int[][] IterativeMultiplication() {
		int [][] matrixC = new int[matrixSize][matrixSize];
		for (int row = 0; row < matrixSize; row++) {
			for (int column = 0; column < matrixSize; column++) {
				matrixC[row][column] = 0;
				for (int k = 0; k < matrixSize; k++) {
					matrixC[row][column] += matrixA[row][k] * matrixB[k][column];
				}
			}
		}

		return matrixC;
	}

	/// Strassen base 1
	public int[][] StrassenB1(int [][] matrixA, int [][] matrixB) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		int n = matrixA.length;
		int [][] matrixC = new int[n][n];
		if (n == 1){
			matrixC[0][0] = matrixA[0][0] * matrixB[0][0];
		}
		else {
			// step 1
			splitTheMatrix(n);

			// step 2
			create10Matrcies();

			// step 3
			int[][] P1 = StrassenB1(A11, S1);
			int[][] P2 = StrassenB1(S2, B22);
			int[][] P3 = StrassenB1(S3, B11);
			int[][] P4 = StrassenB1(A22, S4);
			int[][] P5 = StrassenB1(S5, S6);
			int[][] P6 = StrassenB1(S7, S8);
			int[][] P7 = StrassenB1(S9, S10);
			
			
			
			// step 4
			int[][] C11 = subtract(add(P5, P4), add(P2, P6));
			int[][] C12 = add(P1, P2);
			int[][] C21 = add(P3, P2);
			int[][] C22 = subtract(add(P5, P1), subtract(P3, P7));

			// step 5
			join(C11, matrixC, 0, 0);
			join(C12, matrixC, 0, n / 2);
			join(C21, matrixC, n / 2, 0);
			join(C22, matrixC, n / 2, n / 2);
		}
		return matrixC;
	}

	// Strassen Bigger than 1
	public int[][] Strassen(int matrixA[][], int matrixB[][], int baseCase) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		// matrix dimension
		int n = matrixA.length;
		int [][] matrixC = new int[n][n];
		
		if (n <= baseCase)
			matrixC = IterativeMultiplication();
		else {
			// step 1
			splitTheMatrix(n);
			

			// step 2
			create10Matrcies();
			
			
			// step 3
			int[][] P1 = StrassenB1(A11, S1);
			int[][] P2 = StrassenB1(S2, B22);
			int[][] P3 = StrassenB1(S3, B11);
			int[][] P4 = StrassenB1(A22, S4);
			int[][] P5 = StrassenB1(S5, S6);
			int[][] P6 = StrassenB1(S7, S8);
			int[][] P7 = StrassenB1(S9, S10);

			
			// step 4
			int[][] C11 = subtract(add(P5, P4), add(P2, P6));
			int[][] C12 = add(P1, P2);
			int[][] C21 = add(P3, P2);
			int[][] C22 = subtract(add(P5, P1), subtract(P3, P7));

			// step 5
			join(C11, matrixC, 0, 0);
			join(C12, matrixC, 0, n / 2);
			join(C21, matrixC, n / 2, 0);
			join(C22, matrixC, n / 2, n / 2);
		}
		return matrixC;
	}

	// split the matrix to n/2
	private void splitTheMatrix(int n) {
		// matrix A
		A11 = new int[n / 2][n / 2];
		A12 = new int[n / 2][n / 2];
		A21 = new int[n / 2][n / 2];
		A22 = new int[n / 2][n / 2];

		// matrix B
		B11 = new int[n / 2][n / 2];
		B12 = new int[n / 2][n / 2];
		B21 = new int[n / 2][n / 2];
		B22 = new int[n / 2][n / 2];

		// fill the matrices AXX
		split(matrixA, A11, 0, 0);
		split(matrixA, A12, 0, n / 2);
		split(matrixA, A21, n / 2, 0);
		split(matrixA, A22, n / 2, n / 2);

		// fill the matrices BXX
		split(matrixB, B11, 0, 0);
		split(matrixB, B12, 0, n / 2);
		split(matrixB, B21, n / 2, 0);
		split(matrixB, B22, n / 2, n / 2);
	}

	// create the 10 matrix sum and subtract of the split matrices
	private void create10Matrcies() {
		S1 = subtract(B12, B22);
		S2 = add(A11, A12);
		S3 = add(A21, A22);
		S4 = subtract(B21, B11);
		S5 = add(A11, A22);
		S6 = add(B11, B22);
		S7 = subtract(A12, A22);
		S8 = add(B21, B22);
		S9 = subtract(A11, A21);
		S10 = add(B11, B12);

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

	// split matrix
	private void split(int[][] original, int[][] split, int rowSplit, int columnSplit) {
		for (int row = 0, row2 = rowSplit; row < split.length; row++, row2++)
			for (int column = 0, column2 = columnSplit; column < split.length; column++, column2++) {
				split[row][column] = original[row2][column2];
			}
	}

	// join matrices
	private void join(int[][] split, int[][] original, int rowSplit, int columnSplit) {
		for (int row = 0, row2 = rowSplit; row < split.length; row++, row2++){
			for (int column = 0, column2 = columnSplit; column < split.length; column++, column2++) {
				original[row2][column2] = split[row][column];
			}
		}
	}

}
