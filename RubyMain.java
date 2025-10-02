/* RAT MAZE 
 * Ruby Sun
 * 13/12/2022
 * Java, Neon.1 Release (4.6.1)
 * =======================================================
 * Problem Definition: Required to find the shortest path in a maze, given a rat, a cheese and the exit
 * 	(must show the shortest path from rat to cheese and from the cheese to the exit + output the amount of steps it'd take)
 * Input: no user input but must read a file
 * Output: the amount of steps from rat to cheese, cheese to exit, show the original maze and the path the rat will take, anything to 
 * 	make it more easy for user to understand (eg. a legend)
 * Process: create a matrix of the imported file's maze
 * 	within the grid, recursively test out every path via backtracking
 */
import java.io.*;
import java.util.*; 
public class RubyMain {
	/* main method
	 * Procedural method is called automatically, used to organized the calling of other methods in the class 
	 * 
	 * List of local variables
	 * r - object used to gain acess to the non-static methods defined in the class <type RubyMain>
	 * maze - array used to hold maze size 8 by 12 from file <type String[][]>
	 * matrix - matrix version of the maze <type int [][]>
	 * rat - holds the coordinates of the rat <type int[]>
	 * exit - holds the coordinates of the exit <type int[]>
	 * cheese - holds the cooridnates of the cheese <type int[]>
	 * visited - tracks the areas that have already been visited <type int[][]>
	 */
	public static void main(String[] args)throws IOException {							//main method
		String[][] maze=new String[8][12];
		int[][] matrix=new int[8][12];
		int[]rat=new int[2];
		int[]exit=new int[2];
		int[]cheese=new int[2];
		int[][] visited = new int[8][12];
		int minLength;
		maze=getMaze();
		Obtain o = new Obtain(maze);
		o.setRat(locate(maze, "r ", "m "));
		rat=o.getRat();
		exit=locate(maze, "x ", null);
		cheese=locate(maze, "c ", null);
		System.out.println("ORIGINAL MAZE\n======================");
		legend();
		printArr(maze);
		System.out.println("\n");
		if(checkRCE(maze)>1){
			System.out.println("Please update your maze file!");
		}
		else{
			if(rat[0]==-1||exit[0]==-1||cheese[0]==-1){
				sadRat();
				if(rat[0]==-1)
					System.out.println("You're missing a rat :(");
				if(exit[0]==-1)
					System.out.println("You're missing an exit :(");
				if(cheese[0]==-1)
					System.out.println("You're missing a cheese :(");
			}
			else{
				matrix=convertMatrix(maze);
				visited[rat[0]][rat[1]] = 1; //RAT'S POSITION MARKED ON MATRIX
				System.out.println("RAT > CHEESE\n======================");
				minLength=pathLength(matrix, o.getRat()[0], rat[1], cheese[0], cheese[1],  maze,visited);
				System.out.println("\nIt will take the rat "+minLength+" moves to get to the cheese\n");
				maze=getMaze();
				maze[rat[0]][rat[1]]=". "; //setting rat's positional marker to the path marker because it's no longer there
				System.out.println("CHEESE > EXIT\n======================");
				minLength=pathLength(matrix, cheese[0], cheese[1], exit[0], exit[1], maze,visited);
				System.out.println("\nIt will take the rat "+minLength+" moves to get from the cheese to the exit");
				System.out.println("\nYay! Rat got the cheese!\n");
				rat();
			}
		}
	}

	public static void legend(){
		System.out.println("LEGEND\n------\nRat: R\nCheese: C\nExit: X\nBarrier: B\nWalkable Area: .\nPath taken: u");
		System.out.println("======================");
	}

	public static String[][] getMaze() throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader("mazefile0.txt")));
		String[][] arrMaze = new String [8][12];
		while(sc.hasNextLine()) {
			for (int i=0; i<arrMaze.length; i++) {
				String[] line = sc.nextLine().trim().split(" ");
				for (int j=0; j<line.length; j++) {
					arrMaze[i][j]=line[j]+" ";
				}
			}
		}
		sc.close();
		return arrMaze;
	}

	public static void printArr(String[][] arr){
		for (int i=0; i<arr.length; i++) {
			if(i>0)
				System.out.println();
			for (int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
		}
	}

	public static int[] locate(String[][]arr, String a, String b){
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[i].length; j++) {
				if(arr[i][j].toLowerCase().equals(a)||arr[i][j].toLowerCase().equals(b)){
					return new int[]{i,j};
				}
			}
		}
		return new int[]{-1,-1};
	}

	public static int checkRCE(String[][]arr){
		int counter1=0;
		int counter2=0;
		int counter3=0;
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[i].length; j++) {
				if(arr[i][j].toLowerCase().equals("r ")||arr[i][j].equals("m ")){
					counter1++;
				}
				else if(arr[i][j].toLowerCase().equals("c ")){
					counter2++;
				}
				else if(arr[i][j].toLowerCase().equals("x ")){
					counter3++;
				}
			}
		}
		if(counter1>1)
			System.out.println("You can only have 1 rodent ");
		if(counter2>1)
			System.out.println("You can only have 1 cheese");
		if(counter3>1)
			System.out.println("You can only have 1 exit");
		if(counter1>1||counter2>1||counter3>1)
			return 2;
		return 1;
	}

	public static int[][] convertMatrix(String[][]arr){
		int[][] iArr=new int[8][12];
		//fill temp array with values
		for(int i=0;i<iArr.length;i++)
			for(int j=0;j<iArr[i].length;j++)
				iArr[i][j]=2;
		//compare against original array to find walls
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[i].length; j++) {
				if(arr[i][j].toLowerCase().equals("b ")){
					iArr[i][j]=0;
				}
				else{
					iArr[i][j]=1;
				}
			}
		}
		return iArr;
	}

	public static int pathLength(int[][] mat, int i, int j, int x, int y, String[][] arr, int[][] visited){
		int minPath;
		minPath = shortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0, arr, -1);
		if (minPath != Integer.MAX_VALUE) {
			System.out.println(minPath);
			shortestPath(mat, visited, i, j, x, y, Integer.MAX_VALUE, 0, arr, minPath);
			return minPath;
		}
		return -1;
	}

	public static int shortestPath(int[][] mat, int[][] visited, int i, int j, int x, int y, int minPath, int dist, String[][] arr, int min){
		// if the destination is found, update `minPath`
		if (i == x && j == y) {
			if(dist==min&&minPath>0){
				minPath=-1;
				legend();
				printPath(visited, arr);
				return minPath;
			}

			return Integer.min(dist, minPath);
		}
		// set (i, j) cell as visited
		visited[i][j] = 2;
		// go to the bottom cell
		if (checkValid(mat, visited, i + 1, j)){
			minPath = shortestPath(mat, visited, i + 1, j, x, y, minPath, dist + 1, arr, min);
		}
		// go to the right cell
		if (checkValid(mat, visited, i, j + 1)){
			minPath = shortestPath(mat, visited, i, j + 1, x, y, minPath, dist + 1, arr, min);
		}
		// go to the top cell
		if (checkValid(mat, visited, i - 1, j)){
			minPath = shortestPath(mat, visited, i - 1, j, x, y, minPath, dist + 1, arr, min);
		}
		// go to the left cell
		if (checkValid(mat, visited, i, j - 1)){
			minPath = shortestPath(mat, visited, i, j - 1, x, y, minPath, dist + 1, arr, min);
		}
		// backtrack: remove (i, j) from the visited matrix
		visited[i][j] = 0;
		return minPath;
	}

	public static boolean checkValid(int[][] mat, int[][] visited, int x, int y) {
		return (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length) && mat[x][y] == 1 && visited[x][y]!=2;
	}

	static void printPath(int[][] visited, String[][] arr) {
		for(int i=0;i<visited.length;i++){
			for(int j=0;j<visited[i].length;j++){
				if(visited[i][j]>1){
					arr[i][j]="u ";
				}
			}
		}
		printArr(arr);
	}

	static void rat(){
		System.out.println("                         _     __,..---'''-._                   ';-,");
		System.out.println("                   ,    _/_),-'`             '-.                   `\\");
		System.out.println("    __            \\|.-;`    -_)                 '.                 ||");
		System.out.println("  .'o O'-.        /`   <   ,                      \\               .'/");
		System.out.println(" / O o_.-`|       '.___,__/                 .-'    \\_         _.-'.'");
		System.out.println("/O_.-'  O |          |\\  \\      \\         /`        _`'''''''`_.-'");
		System.out.println("| o   o  o|             _/;--._, >        |   --.__/ `'''''''`");
		System.out.println("|o   o O.-`           (((-'  __//`'-......-;\\      )");
		System.out.println("| O _.-'                   (((-'       __//  '--. /");
		System.out.println("'--`                                 (((-'    __//");
		System.out.println("                                            (((-'");

	}

	static void sadRat(){
		System.out.println("  q-p	  q-p");
		System.out.println(" /   \\	 /\\\"/\\");
		System.out.println("(     )	(`=*=')");
		System.out.println(" `-(-'	 ^---^`-._");
		System.out.println("    ) 		");
		System.out.println("");
		System.out.println("");

	}


}

class Obtain{
	private int[] rat=new int[2];
	private int[] getExit=new int[2];
	private int[] getCheese=new int[2];
	String[][] getMaze;

	public Obtain(String[][] maze){
		getMaze=maze;
	}
	
	public void setRat(int[] ratPos){
		rat=ratPos;
	}
	
	public int[] getRat(){
		return rat;
	}
	
}
