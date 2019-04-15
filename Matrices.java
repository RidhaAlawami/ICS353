import java.util.Arrays;

final class Matrices {
	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] matrixC;
	public Matrices(int[][] matrixA, int[][] matrixB) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
	}
	public void setMatrixA(int[][] matrixA) {
		this.matrixA = matrixA;
	}
	public void setMatrixB(int[][] matrixB) {
		this.matrixB = matrixB;
	}
	public void setMatrixC(int[][] matrixC) {
		this.matrixC = matrixC;
	}
	public int[][] getMatrixA() {
		return matrixA;
	}
	public int[][] getMatrixB() {
		return matrixB;
	}
	public int[][] getMatrixC() {
		return matrixC;
	}
	public void printMatrixA() {
		System.out.println(Arrays.deepToString(this.getMatrixA()));
	}
	public void printMatrixB() {
		System.out.println(Arrays.deepToString(this.getMatrixB()));
	}
	public void printMatrixC() {
		System.out.println(Arrays.deepToString(this.getMatrixC()));
	}
	public void printBothArrays() {
		printMatrixA();
		printMatrixB();
	}

	public int[][] AddPadding(int[][] matrix, int matrixSize) {
		int[][] my_padded_matrix = null;
		my_padded_matrix = new int[matrixSize][matrixSize];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				my_padded_matrix[i][j] = matrix[i][j];
			}
		}
		return my_padded_matrix;
	}

	public int[][] RemovePadding(int[][] my_padded_matrix, int matrixSize) {
		int[][] my_unpadded_matrix = null;
		
		my_unpadded_matrix = new int[matrixSize][matrixSize];
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				my_unpadded_matrix[i][j] = my_padded_matrix[i][j];
			}
		}
		return my_unpadded_matrix;
	}



}
