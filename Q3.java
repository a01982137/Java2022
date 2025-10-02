import java.io.*;
class Q3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		double k;
		System.out.println("Enter denom");
		k=Double.parseDouble(br.readLine());
		System.out.println(geometricSum(k));

	}
	static double geometricSum(double k){
		if(k==0){
			return 1;
		}
		return 1/(Math.pow(2, k))+geometricSum(k-1);
	}
	

}
