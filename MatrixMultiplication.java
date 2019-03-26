import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixMultiplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n;
		String input_file_name = "", output_file_name = "";
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Input format: n *space* InputFileName.txt *space* OutputFileName.txt");
		System.out.println("matrixMultiply 1011 input.txt output.txt");
		n = kb.nextInt();
		input_file_name = kb.next();
		output_file_name = kb.next();
		
		PrintArray(matrix(n, input_file_name));
	}
	
	//returns a matrix read from the file and stored in the array
	//it reads up to n rows and n columns if there are no values they are assigned to 0.
	private static int[][] matrix(int array_size, String input_file_name){
		int[][] array = new int[array_size][array_size];
		
		try {
			Scanner inputFile = new Scanner(new File(input_file_name));
			for(int i = 0; i < array_size; i++) {
				for(int j = 0; j < array_size; j++) {
					array[i][j] = inputFile.nextInt();
				}
				
			}
			
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find a file named " + input_file_name);
		}
		return array;
	}
	
	//https://stackoverflow.com/questions/19648240/java-best-way-to-print-2d-array
	//print a 2d array
	private static void PrintArray(int[][] array) {
		System.out.println(Arrays.deepToString(array));
	}
}
