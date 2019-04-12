import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class driver {
	public static void main(String[] args) {
		int n;
		String input_file_name = "";
		Scanner kb = new Scanner(System.in);
		System.out.println("Input format: n *ENTER* InputFileName.txt");
		System.out.println("For example: 1011 \ninput_file_name.txt");
		n = kb.nextInt();
		input_file_name = kb.next();

		// get matrix A and matrix B
		Matrices readMatrcies = matrix(ConvertToDecimal(n), input_file_name);
		
		// if needed
		Matrices paddedMatrices;
		
//		readMatrcies.printBothArrays();
		
		
		MatrixMultiplication inputMatrices = new MatrixMultiplication();
		MatrixModifications matrixModificationsA, matrixModificationsB, matrixModificationsC;

		
		
		long iterativeStartTime = System.nanoTime();
		int[][] iterativeResults = inputMatrices.IterativeMultiplication(readMatrcies.getMatrixA(),
				readMatrcies.getMatrixB(), ConvertToDecimal(n));
		long iterativeEndTime = System.nanoTime();
		long iterativeTotalTime = iterativeEndTime - iterativeStartTime;
		System.out.println("Iterative Multiplication took: " + iterativeTotalTime / (Math.pow(10, 6)));
		
		//Write output to file.
		try {
			writeToFile("Iterative results ", "IterativeMultiplication", iterativeResults, n);
			writeTimeToFile("Iterative results time", "IterativeMultiplication", iterativeTotalTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("IterativeMultiplication Results:");
//		for (int i = 0; i < iterativeResults.length; i++) {
//			for (int j = 0; j < iterativeResults[i].length; j++) {
//				System.out.print(iterativeResults[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		//The padding step for strassens' functions
		boolean result = n > 0 && ((n & (n - 1)) == 0);
		System.out.println(result);
		if(result) {
			paddedMatrices = readMatrcies;
		}
		else {
			matrixModificationsA = new MatrixModifications(readMatrcies.getMatrixA());
			matrixModificationsB = new MatrixModifications(readMatrcies.getMatrixB());
			paddedMatrices = new Matrices(matrixModificationsA.AddPadding(), matrixModificationsB.AddPadding());
		}
		
		System.out.println("Enter a B > 1 for the strassen:");
		int b = kb.nextInt();
		long strassenB1StartTime = System.nanoTime();
		int[][] strassenB1Results = inputMatrices.StrassenB1(paddedMatrices.getMatrixA(), paddedMatrices.getMatrixB());
		long strassenB1EndTime = System.nanoTime();
		long strassenB1TotalTime = iterativeEndTime - iterativeStartTime;
		System.out.println("StrassenB1 Multiplication took: " + strassenB1TotalTime / (Math.pow(10, 6)));
		
		try {
			writeToFile("StrassenB1 results ", "StrassenB1", strassenB1Results, n);
			writeTimeToFile("StrassenB1 results time", "StrassenB1", strassenB1TotalTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		matrixModificationsC = new MatrixModifications(strassenB1Results);
		paddedMatrices.setMatrixC(matrixModificationsC.RemovePadding());
		System.out.println("StrassenB1");
		paddedMatrices.printMatrixC();
		
		for (int i = 0; i < strassenB1Results.length; i++) {
			for (int j = 0; j < strassenB1Results[i].length; j++) {
				System.out.print(strassenB1Results[i][j] + "\t");
			}
			System.out.println();
		}	

		long strassenStartTime = System.nanoTime();
		int[][] strassenResults = inputMatrices.Strassen(paddedMatrices.getMatrixA(), paddedMatrices.getMatrixB(), b);
		long strassenEndTime = System.nanoTime();
		long strassenTotalTime = iterativeEndTime - iterativeStartTime;
		System.out.println("StrassenB1 Multiplication took: " + strassenTotalTime / (Math.pow(10, 6)));
		
		try {
			writeToFile("Strassen results of " + " Base " + b, "Strassen", strassenResults, n);
			writeTimeToFile("Strassen results time", "Strassen", strassenTotalTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		paddedMatrices.setMatrixC(strassenResults);
		System.out.println("Strassen");
		for (int i = 0; i < strassenResults.length; i++) {
			for (int j = 0; j < strassenResults[i].length; j++) {
				System.out.print(strassenResults[i][j] + "\t");
			}
			System.out.println();
		}
		paddedMatrices.printMatrixC();

	}

	// get the matrices from the file
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

	// write the result to file
	public static void writeToFile(String fileName, String functionName, int matrixC[][], int n) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(functionName + " matrix multiplication result of " + n + "*" + ":\n");
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

	public static void writeTimeToFile(String fileName, String functionName, long time) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		double timeInSeconds = time / Math.pow(10, 6);
		writer.write(functionName + " took " + timeInSeconds + " seconds" + ":\n");

		writer.close();
	}

	// convert binary to decimal
	private static int ConvertToDecimal(int n) {

		int Decimal = 0;
		int power = 0;

		while (n != 0) {
			int reminder = n % 10;
			Decimal += reminder * Math.pow(2, power);

			n = n / 10;
			power++;
		}

		return Decimal;
	}
}