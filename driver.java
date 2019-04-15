import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class driver {
	public static void main(String[] args) throws IOException {
		
		
		Matrices Matrices;
		MatrixMultiplication multiplication = new MatrixMultiplication();
		ReadAndWriteFiles readAndWrite = new ReadAndWriteFiles();
		String input;
		int t, b;
		String n, inputFile, outputFile;
		boolean valid = true ;
		boolean powerOf2 = true;
		int matrixSize;
		int [][] matrixC;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("input should be --> matrixMultiply t n b input_filename output_filename");
		System.out.println("Note that input and output should be given"
				+ " in the form of text files and the parameters \n t (multiplication algorithm:0 for iterative and 1 for Strassen’s),\n"
				+ " n (matrix dimensions),\n b (Strassen’s base case dimension)");
		System.out.println("For example: matrixMultiply 0 1011 0 input.txt output.txt");

		while(true){
				System.out.print("input: ");
				input = scanner.nextLine(); 
		
				String [] inputValues = input.split(" ");
				
				if(inputValues.length < 6){
					valid = false;
					System.out.println("wrong input, try again");
				}
				else 
					valid = true;
				
	
		
				if(valid){
					t = Integer.parseInt(inputValues[1]);
					n = inputValues[2];
					b = Integer.parseInt(inputValues[3]);
					
					inputFile = inputValues[4];
					outputFile = inputValues[5];
					
					valid = checkInput(t,n,inputFile,outputFile);
				
					if(!valid){
						System.out.println("wrong input, try again");
					}
					else{
						break;
					}
				}
				
		
		
		}
		//get the size as integer 
		matrixSize = ConvertToDecimal(Integer.parseInt(n));
		
		//read the matrcies 
		Matrices = readAndWrite.readMatrices(matrixSize, inputFile);
		
		

		
		
		//multiplication of matrices 
		
		if( t == 0){
			matrixC = multiplication.IterativeMultiplication(Matrices.getMatrixA(), Matrices.getMatrixB());
			Matrices.setMatrixC(matrixC);
		}
		else if( t == 1 && b == 0){
			//padding if not square power of two
			if(!isPowerOfTwo(matrixSize)){
				powerOf2 = false;
			}
			if(!powerOf2){
				
				matrixSize = NextPowerOfTwo(matrixSize);
				int [][] newMatrixA = Matrices.AddPadding(Matrices.getMatrixA(), matrixSize);
				Matrices.setMatrixA(newMatrixA);
				
				int [][] newMatrixB = Matrices.AddPadding(Matrices.getMatrixB(), matrixSize);
				Matrices.setMatrixB(newMatrixB);
			}
			//Multiplying here 
			matrixC = multiplication.StrassenB1(Matrices.getMatrixA(), Matrices.getMatrixB());
			Matrices.setMatrixC(matrixC);
		}
		else{
			//padding if not square power of two
			if(!isPowerOfTwo(matrixSize)){
				powerOf2 = false;
			}
			if(!powerOf2){
				
				matrixSize = NextPowerOfTwo(matrixSize);
				int [][] newMatrixA = Matrices.AddPadding(Matrices.getMatrixA(), matrixSize);
				Matrices.setMatrixA(newMatrixA);
				
				int [][] newMatrixB = Matrices.AddPadding(Matrices.getMatrixB(), matrixSize);
				Matrices.setMatrixB(newMatrixB);
			}
			//Multiplying  
			matrixC = multiplication.Strassen(Matrices.getMatrixA(), Matrices.getMatrixB(),b);
			Matrices.setMatrixC(matrixC);
		}
		
		
		//remove padding if there is one
		if(!powerOf2){
			matrixC = Matrices.RemovePadding(matrixC, matrixSize);
			Matrices.setMatrixC(matrixC);
			powerOf2 = true;
		}
		
		//write result to file 
		matrixSize = ConvertToDecimal(Integer.parseInt(n));
		if( t == 0){
			readAndWrite.writeToFile(outputFile, Matrices.getMatrixC(), matrixSize, "iterative");
		}
		else if( t == 1 && b == 0){
			readAndWrite.writeToFile(outputFile, Matrices.getMatrixC(), matrixSize, "StrassenB1");
		}
		else{
			readAndWrite.writeToFile(outputFile, Matrices.getMatrixC(), matrixSize, "Strassen");
		}
		
		System.out.println("Multiplication is Done.");
		
		
	}

	private static boolean checkInput(int t, String n, String inputFile, String outputFile ){
		boolean validity = false;
	
		if(t == 1 || t == 0)
			return validity = true;
		
		if (n.matches("[01]+") && !n.startsWith("0")) 
			return validity = true;
			
		
		String []input = inputFile.split(".");
		String []output = outputFile.split(".");
		
		if(input[1].equals("txt"))
			return validity = true;
		
		if(output[1].equals("txt"))
			return validity = true;
		
		
		return validity;
	}
	

	private static boolean isPowerOfTwo(int n){
		
		boolean result = n > 0 && ((n & (n - 1)) == 0);
		
		
		return result;
	}
	private static int NextPowerOfTwo(int n) {

		int closestNumber = 0;

		double log2n = Math.log(n) / Math.log(2);

		double power = Math.ceil(log2n);

		closestNumber = (int) Math.pow(2, power);

		return closestNumber;
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