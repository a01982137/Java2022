
public class TreeRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fun(4);

	}
	static void fun(int n){
		if (n>0){
			fun(n-1); //LEFT NODE
			System.out.print(" "+n); //PARENT NODE
			fun(n-1); //RIGHT NODE
		}
	}

}
