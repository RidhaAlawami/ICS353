
public class MatrixModifications {
	
	

	//This methods adds 1 to the array row and column.
	//and returns a new array
	//assuming its a matrix.
	public  int[][] AddPadding(int[][] array){
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
	public  int[][] RemovePadding(int[][] array){
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
		

}
