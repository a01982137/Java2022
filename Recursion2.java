
public class Recursion2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recursion2 r2= new Recursion2();
		r2.yesExit(3);
	}
	public void yesExit(int n){
		System.out.println("Meow-cursion with base case");
		if(n>1)
			yesExit(n-1);
	}

}
