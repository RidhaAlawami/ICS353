import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingArrayTest {
	public static void main(String[] args) {
		
		int n;	

		//matrix multiplication from file
		String input_file_name = "";
		Scanner kb = new Scanner(System.in);

		System.out.println("Input format: n *ENTER* InputFileName.txt");
		System.out.println("For example: 1011 \ninput_file_name.txt");

		n = kb.nextInt();
		input_file_name = kb.next();
		
		ReadingResults myResults = matrix(n, input_file_name);

		myResults.printBothArrays();
	}

	private static ReadingResults matrix(int array_size, String input_file_name){
		int[][] firstArray = new int[array_size][array_size];
		int[][] secondArray = new int[array_size][array_size];

		try {
			Scanner inputFile = new Scanner(new File(input_file_name));
			int i = 0;
			int counter = 0;
			boolean middle = false;
			while(counter < array_size*2) {
					if(counter < array_size) {
						for(int j = 0; j < array_size; j++) {
							firstArray[i][j] = inputFile.nextInt();
						}
						
						i++;
						counter++;
					}

					else if(counter >= array_size) {
						if(!middle) {
							i = 0;
							middle = true;
						}
						for(int j = 0; j < array_size; j++) {
							secondArray[i][j] = inputFile.nextInt();
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
		return new ReadingResults(firstArray, secondArray);
	}
	
}
