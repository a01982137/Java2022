import java.io.*;
public class Palindrome {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Palindrome p = new Palindrome();
		String word, tempWord;
		System.out.println("Enter your word: ");
		word = p.getStr();
		if(checkWord(word))
			System.out.println(word+" is a palidrome");
		else 
			System.out.println(word+ " is not a palidrome");
	}
	String getStr()throws IOException{
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String str = br.readLine().toLowerCase();
		return str;
	}
	static boolean checkWord(String word){
		if(word.length()==1){
			return true;
		}
		if(word.charAt(0)==word.charAt(word.length()-1))
			return checkWord(word.substring(1,word.length()-1));
		return false;
		

	}
}
