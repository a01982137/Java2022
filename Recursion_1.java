import java.io.*;
public class Recursion_1 {
	private static int rec_count=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(factorial(5));
	}
	public static long factorial (int n){
		if (n==1){
			System.out.print("N = "+n);
			System.out.print(" | n-1 = "+(n-1));
			System.out.println(" } recursive call #"+rec_count);
			return 1;
		}
		System.out.print("N = "+n);
		System.out.print(" | n-1 = "+(n-1));
		System.out.println(" | recursive call #"+rec_count);
		rec_count++;
		return n*factorial(n-1);
	}

}
