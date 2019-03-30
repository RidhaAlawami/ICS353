import java.util.Arrays;

final class ReadingResults {
    private int[][] first;
    private int[][] second;

    public ReadingResults(int[][] first, int[][] second) {
        this.first = first;
        this.second = second;
    }
    
    public void setFirst(int[][] first) {
    	this.first = first;
    }
    
    public void setSecond(int[][] second) {
    	this.second = second;
    }

    public int[][] getFirst() {
        return first;
    }

    public int[][] getSecond() {
        return second;
    }
    
    public void printFirst(){
    	System.out.println(Arrays.deepToString(this.getFirst()));
    }
    
    public void printSecond(){
    	System.out.println(Arrays.deepToString(this.getSecond()));
    }
    
    public void printBothArrays() {
    	printFirst();
    	printSecond();
    }
}

// ...

//public static MyResult something() {
//    int number1 = 1;
//    int number2 = 2;
//
//    return new MyResult(number1, number2);
//}
//
//public static void main(String[] args) {
//    MyResult result = something();
//    System.out.println(result.getFirst() + result.getSecond());
//}