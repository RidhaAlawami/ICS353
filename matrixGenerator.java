import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class matrixGenerator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		

		// print the matrixC as
		/*
		 * 2 * 2 matrix 1 2 4 5
		 * 
		 */
		for(int n = 30; n < 400; n++){
			BufferedWriter writer = new BufferedWriter(new FileWriter("matrices/Matrix"+n+".txt"));
			for (int row = 0; row < n; row++) {
				for (int column = 0; column < n; column++) {
					//random numbers from -100 to 100 
				    int random = rand.nextInt(100 + 1 + 100) - 100;
					writer.write(random + " ");
				}
				writer.newLine();
			}
			for (int row = 0; row < n; row++) {
				for (int column = 0; column < n; column++) {
					//random numbers from -100 to 100 
				    int random = rand.nextInt(100 + 1 + 100) - 100;
					writer.write(random + " ");
	
				}
				writer.newLine();
			}
			writer.close();
			
			}
		
		System.out.println("Done");
		
		}
	
	
	

}
