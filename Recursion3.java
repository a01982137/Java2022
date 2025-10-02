
public class Recursion3 {

	public static void main(String[] args) {
		Recursion3 r2 = new Recursion3();
		r2.yesExit(3);
	}
	public void yesExit(int n){
		System.out.println("Meow-cursion N="+n);
		if(n>1)
			yesExit(n-1);
		System.out.println("Goodbye N="+n);
	}
}
