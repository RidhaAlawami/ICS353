import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadAndWriteFiles {
	public static void main(String[] args) {

		// n*n
		int n;

		// matrix multiplication from file
		String input_file_name = "";
		Scanner kb = new Scanner(System.in);

		System.out.println("Input format: n *ENTER* InputFileName.txt");
		System.out.println("For example: 1011 \ninput_file_name.txt");

		n = kb.nextInt();
		input_file_name = kb.next();

		// get matrix A and matrix B
		Matrices readMatrcies = matrix(n, input_file_name);

		readMatrcies.printBothArrays();
	}

	// get the Matrices from the file
	public static Matrices matrix(int n, String input_file_name) {
		int[][] matrixA = new int[n][n];
		int[][] matrixB = new int[n][n];

		try {
			Scanner inputFile = new Scanner(new File(input_file_name));
			int i = 0;
			int counter = 0;
			boolean middle = false;
			while (counter < n * 2) {
				if (counter < n) {
					for (int j = 0; j < n; j++) {
						matrixA[i][j] = inputFile.nextInt();
					}

					i++;
					counter++;
				}

				else if (counter >= n) {
					if (!middle) {
						i = 0;
						middle = true;
					}
					for (int j = 0; j < n; j++) {
						matrixB[i][j] = inputFile.nextInt();
					}
					i++;
					counter++;
				}
			}

			inputFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find a file named " + input_file_name);
		}

		return new Matrices(matrixA, matrixB);
	}

	// read the result to file
	public static void writeToFile(String fileName, int matrixC[][], int n) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write("iterative matrix multiplication result of " + n + "*" + ":\n");
		writer.newLine();

		// print the matrixC as
		/*
		 * 2 * 2 matrix 1 2 4 5
		 * 
		 */
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				writer.write(matrixC[row][column] + " ");
			}
			writer.newLine();
		}

		writer.close();
	}

}