import java.io.*;
public class Q2 {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		double denom, divis;
		System.out.println("Enter denom");
		denom=Double.parseDouble(br.readLine());
		System.out.println("Enter divisor");
		divis=Double.parseDouble(br.readLine());
		System.out.println(harmonicSum(denom,divis));
	}
	static double harmonicSum(double i, double n){
		if (n==i)
			return 1;
		return (i/n)+(harmonicSum(i,n-1));
	}

}
