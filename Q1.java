import java.io.*;
public class Q1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		int num;
		System.out.println("please enter a whole number");
		num = Integer.parseInt(br.readLine());
		System.out.println(sumDownBy2(num));
	}
	static int sumDownBy2(int n){
		if (n<1){
			return 0;
		}
		return n+sumDownBy2(n-2);
	}
}
