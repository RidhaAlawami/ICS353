import java.util.Arrays;

//3/29/2019
//Ridha Alawami
//A simple example of padding a 2-D array (matrix) and unpadding it.

public class ArrayPaddingTest {
	
	public static void main(String[] args) {
		int[][] a = {{1,2}, {1,2}};
		int[][] b = {{5,3,0},{2, 2, 0},{0,0,0}};
		
		System.out.println("Array a");
		PrintArray(a);
		
		System.out.println("My padded array a");
		PrintArray(AddPadding(a));
		
		System.out.println("Array b");
		PrintArray(b);
		
		System.out.println("My unpadded array b");
		PrintArray(RemovePadding(b));
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

	private static void PrintArray(int[][] array) {
		System.out.println(Arrays.deepToString(array));
	}
}
