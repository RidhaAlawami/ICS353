import java.util.Arrays;

final class Matrices {
    private int[][] matrixA;
    private int[][] matrixB;

    public Matrices(int[][] matrixA, int[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }
    
    public void setMatrixA(int[][] matrixA) {
    	this.matrixA = matrixA;
    }
    
    public void setMatrixB(int[][] matrixB) {
    	this.matrixB = matrixB;
    }

    public int[][] getMatrixA() {
        return matrixA;
    }

    public int[][] getMatrixB() {
        return matrixB;
    }
    
    public void printMatrixA(){
    	System.out.println(Arrays.deepToString(this.getMatrixA()));
    }
    
    public void printMatrixB(){
    	System.out.println(Arrays.deepToString(this.getMatrixB()));
    }
    
    public void printBothArrays() {
    	printMatrixA();
    	printMatrixB();
    }
}