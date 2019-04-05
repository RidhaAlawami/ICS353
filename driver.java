
public class driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
    //closet 2^x number to a given input 
	private static int NextPowerOfTwo(int n){
			
			int closestNumber = 0; 
			
			double log2n = Math.log(n) / Math.log(2);
			
			double power = Math.ceil(log2n);
			
			closestNumber = (int) Math.pow(2, power);
			
			return closestNumber; 
		}
		

}
