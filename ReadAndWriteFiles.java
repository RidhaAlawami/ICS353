import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadAndWriteFiles {

	
	
	//read matrix 
	
	public  Matrices readMatrices(int n, String input_file_name) throws FileNotFoundException{
		
		int[][] matrixA = new int[n][n];
		int[][] matrixB = new int[n][n];
		

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(input_file_name)));
		
		while(scanner.hasNextLine()){
			//matrix A
			for(int row = 0; row < n;row++){
				String [] line = scanner.nextLine().trim().split(" ");
				for(int column = 0; column < n; column++){
					matrixA[row][column] = Integer.parseInt(line[column]);
				}
			}
			
			//Matrix B
			for(int row = 0; row < n;row++){
				String [] line = scanner.nextLine().trim().split(" ");
				for(int column = 0; column < n; column++){
					matrixB[row][column] = Integer.parseInt(line[column]);
				}
			}
			
			
		}
			
			
		return new Matrices(matrixA, matrixB);
		
	}
	

	
	// read the result to file
	public  void writeToFile(String fileName, int matrixC[][], int n, String method) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		switch (method){
		case "iterative":
			writer.write("iterative matrix multiplication result of " + n + "*" + ":\n");
			writer.newLine();
			
			for (int row = 0; row < n; row++) {
				for (int column = 0; column < n; column++) {
					writer.write(matrixC[row][column] + " ");
				}
				writer.newLine();
			}

			writer.close();
			
			break;
		
		case "StrassenB1":
			writer.write("Strassen base 1 matrix multiplication result of " + n + "*" + ":\n");
			writer.newLine();
			
			for (int row = 0; row < n; row++) {
				for (int column = 0; column < n; column++) {
					writer.write(matrixC[row][column] + " ");
				}
				writer.newLine();
			}

			writer.close();
			
			break;
			
		case "Strassen":
			writer.write("strassen matrix multiplication result of " + n + "*" + ":\n");
			writer.newLine();
			
			for (int row = 0; row < n; row++) {
				for (int column = 0; column < n; column++) {
					writer.write(matrixC[row][column] + " ");
				}
				writer.newLine();
			}

			writer.close();
			
			break;
				
		
		}

}

	
	public  void writeTimeToFile(String fileName, String functionName,int size, double time) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("results2/"+fileName,true));
		writer.append(functionName + " size  "+size+" took " + time + " seconds" + ":\n");
		//writer.append( time +"\n");
		writer.close();
	}


}