
public class add {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(add(5,9));
	}
	static int add(int i, int j) { // assumes i &gt;= 0
		if (i == 0)
		return j;
		else
		System.out.println(i+" "+j);
		return add(i-1, j+1);
		}
}
