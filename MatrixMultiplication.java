import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class traditional_multiplication {

	static int [][] matrixC;
	static int [][] matrixA;
	static int [][] matrixB;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int n;	
	
		//matrix multiplication from file
		String input_file_name = "", output_file_name = "";
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Input format: n *space* InputFileName.txt *space* OutputFileName.txt");
		System.out.println("1011 input_file_name.txt OutputFileName.txt");
		
		n = kb.nextInt();
		input_file_name = kb.next();
		output_file_name = kb.next();
		
		System.out.println("input " +  input_file_name);
		System.out.println("output " + output_file_name);
		System.out.println("size " + n + " " + ConvertToDecimal(n));
		
		matrixA = matrix(ConvertToDecimal(n), input_file_name);
		matrixB = matrix(ConvertToDecimal(n), input_file_name);
		
		matrixC = IterativeMultiplication(matrixA, matrixB,ConvertToDecimal(n));
		
		writeToFile(output_file_name, matrixC, ConvertToDecimal(n));
		PrintArray(matrixA);
		PrintArray(matrixB);
		PrintArray(matrixC);

	}

	
	
	//matrix c size will derived from the user input n 
	//where matrixA n x n and matrixB n x n 
	//thus matrixC n x n 
	// the product of two matrices => matrixC(m,n) = matrixA(m,k) * matrixB(k,n)
	public static int [][] IterativeMultiplication(int matrixA[][], int matrixB[][], int n){
		matrixC = new int[n][n];
		for(int row = 0; row < n; row++){
			for(int column = 0; column < n; column++){
				matrixC[row][column] = 0;
				for(int k = 0; k< n; k++){
					matrixC[row][column] += matrixA[row][k] * matrixB[k][column];
				}
			}	
		}
				
		return matrixC;
	}
	
	
	//write the answer to external file
	public static void writeToFile(String fileName, int matrixC [][],  int n) throws IOException{
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write("iterative matrix multiplication result of "+n +"*" +  ":\n");
		writer.newLine();
		
		//print the matrixC as 
		/*
		 * 2 * 2 matrix 
		 * 1   2
		 * 4   5
		 * 
		 */
		for(int row = 0; row < n; row++){
			for(int column = 0; column < n; column++){
				writer.write(matrixC[row][column] + " ");
			}
			writer.newLine();
		}
		
		
		writer.close();	
	}
	
	
	//This methods adds 1 to the array row and column.
		//and returns a new array
		//assuming its a matrix.
		public static int[][] AddPadding(int[][] array){
			int[][] my_padded_array = null;
			if(array == null || array.length == 0)
				return array;
			
			else {
				int array_size = array.length + 1;
				
				my_padded_array = new int[array_size][array_size];
				
				for(int i = 0; i < array.length; i++) {
					for(int j = 0; j < array[i].length; j++) {
						my_padded_array[i][j] = array[i][j];
					}
				}	
			}
			
			return my_padded_array;
		}
		
		//This methods removes 1 from the array row and column.
		//and returns a new array
		//assuming its a matrix.
		public static int[][] RemovePadding(int[][] array){
			int[][] my_unpadded_array = null;
			if(array == null || array.length == 0)
				return array;
			
			else {
				int array_size = array.length - 1;
				
				my_unpadded_array = new int[array_size][array_size];
				for(int i = 0; i < array_size; i++) {
					for(int j = 0; j < array_size; j++) {
						my_unpadded_array[i][j] = array[i][j];
					}
				}	
			}
			return my_unpadded_array;
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
		

		
		//print the array 
		private static void PrintArray(int[][] array) {
			System.out.println(Arrays.deepToString(array));
		}
	
	
		//convert binary to decimal 
		private static int ConvertToDecimal(int n){
			
			int Decimal = 0;
			int power = 0;
			
			while(n != 0){
				int reminder = n%10;
				Decimal +=  reminder * Math.pow(2, power);
				
				n = n / 10;
				power++;
			}

			
			return Decimal;
		}
		

}
