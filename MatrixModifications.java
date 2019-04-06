
public class MatrixModifications {

	private static int matrix_size_before_padding;

	// This methods adds a padding factor using GetPaddingFactor to the matrix row
	// and column.
	// and returns a new matrix
	// assuming the input is a matrix.
	public int[][] AddPadding(int[][] matrix) {
		int[][] my_padded_matrix = null;
		if (matrix == null || matrix.length == 0)
			return matrix;

		else {
			// to be used in RemovePadding
			matrix_size_before_padding = matrix.length;
			int matrix_size = NextPowerOfTwo(matrix.length);

			my_padded_matrix = new int[matrix_size][matrix_size];

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					my_padded_matrix[i][j] = matrix[i][j];
				}
			}
		}

		return my_padded_matrix;
	}

	// This methods removes the previously added padding factor from the matrix row
	// and column.
	// and returns a new matrix
	// assuming the input is a matrix.
	public int[][] RemovePadding(int[][] matrix) {
		int[][] my_unpadded_matrix = null;
		if (matrix == null || matrix.length == 0)
			return matrix;

		else {
			int matrix_size = matrix_size_before_padding;

			my_unpadded_matrix = new int[matrix_size][matrix_size];
			for (int i = 0; i < matrix_size; i++) {
				for (int j = 0; j < matrix_size; j++) {
					my_unpadded_matrix[i][j] = matrix[i][j];
				}
			}
		}
		return my_unpadded_matrix;
	}

	// closet 2^x number to a given input
	private static int NextPowerOfTwo(int n) {

		int closestNumber = 0;

		double log2n = Math.log(n) / Math.log(2);

		double power = Math.ceil(log2n);

		closestNumber = (int) Math.pow(2, power);

		return closestNumber;
	}

	// this takes the n of the matrix and calculates the factor based on the minimum
	// between the n and a 2 to power.
	// to make the padding in a power of two.
//	public int GetPaddingFactor(int n) {
//		int padding_factor = 0;
//		int temp = 2, counter = 1;
//		boolean factor_found = false;
//		
//		while(!factor_found) {
//			if(n-temp >0) {
//				temp = (int) Math.pow(2, ++counter);
//			}
//			else {
//				padding_factor = temp - n;
//				factor_found = true;
//			}
//		}
//		return padding_factor;
//	}
}
