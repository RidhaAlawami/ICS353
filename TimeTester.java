import java.io.FileNotFoundException;
import java.io.IOException;

public class TimeTester {

	public static void main(String[] args) throws IOException {
		Matrices Matrices;
		MatrixMultiplication multiplication = new MatrixMultiplication();
		ReadAndWriteFiles readAndWrite = new ReadAndWriteFiles();
		String input;
		String inputFile, outputFile;
		boolean powerOf2 = true;
		int matrixSize;
		int [][] matrixC;
		
		//iterative results 
		for( int i = 30; i < 607; i++){

			inputFile = "matrices/Matrix"+i+".txt";
			Matrices = readAndWrite.readMatrices(i, inputFile);
			
			
			if(!isPowerOfTwo(i)){
				powerOf2 = false;
			}
			if(!powerOf2){
				
				matrixSize = NextPowerOfTwo(i);
				int [][] newMatrixA = Matrices.AddPadding(Matrices.getMatrixA(), matrixSize);
				Matrices.setMatrixA(newMatrixA);
				
				int [][] newMatrixB = Matrices.AddPadding(Matrices.getMatrixB(), matrixSize);
				Matrices.setMatrixB(newMatrixB);
			}
			
			
			//multiplication of matrices iterative 
			long start = System.currentTimeMillis();
			matrixC = multiplication.IterativeMultiplication(Matrices.getMatrixA(), Matrices.getMatrixB());
			long now = System.currentTimeMillis();
			
			double timeTaken = (now - start) / 1000.0;
			/////////////
			readAndWrite.writeTimeToFile("iterative.txt", "iterative", i ,timeTaken );
		
		}
		System.out.println("done");
		//Strassen b1 results 
				for( int i = 30; i < 607; i++){
					inputFile = "matrices/Matrix"+i+".txt";
					Matrices = readAndWrite.readMatrices(i, inputFile);
					
					
					if(!isPowerOfTwo(i)){
						powerOf2 = false;
					}
					if(!powerOf2){
						
						matrixSize = NextPowerOfTwo(i);
						int [][] newMatrixA = Matrices.AddPadding(Matrices.getMatrixA(), matrixSize);
						Matrices.setMatrixA(newMatrixA);
						
						int [][] newMatrixB = Matrices.AddPadding(Matrices.getMatrixB(), matrixSize);
						Matrices.setMatrixB(newMatrixB);
					}
					
					
					//multiplication of matrices strassenB1 
					long start = System.currentTimeMillis();
					matrixC = multiplication.StrassenB1(Matrices.getMatrixA(), Matrices.getMatrixB());
					long now = System.currentTimeMillis();
					
					double timeTaken = (now - start) / 1000.0;
					/////////////
					readAndWrite.writeTimeToFile("strassenB1.txt", "strassenB1", i ,timeTaken );
				
				}
				System.out.println("done");
				//strassen  results 
				for(int b = 2; b < 256; b++){
					for( int i = 30; i < 316; i++){
						inputFile = "matrices/Matrix"+i+".txt";
						Matrices = readAndWrite.readMatrices(i, inputFile);
						
						
						if(!isPowerOfTwo(i)){
							powerOf2 = false;
						}
						if(!powerOf2){
							
							matrixSize = NextPowerOfTwo(i);
							int [][] newMatrixA = Matrices.AddPadding(Matrices.getMatrixA(), matrixSize);
							Matrices.setMatrixA(newMatrixA);
							
							int [][] newMatrixB = Matrices.AddPadding(Matrices.getMatrixB(), matrixSize);
							Matrices.setMatrixB(newMatrixB);
						}
						
						
						//multiplication of matrices strassenB1 
						long start = System.currentTimeMillis();
						matrixC = multiplication.Strassen(Matrices.getMatrixA(), Matrices.getMatrixB(),b);
						long now = System.currentTimeMillis();
						
						double timeTaken = (now - start) / 1000.0;
						/////////////
						readAndWrite.writeTimeToFile("strassenBase"+b+".txt", "strassen", i ,timeTaken );
						
					}
				}
				System.out.println("done");
		

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

}
