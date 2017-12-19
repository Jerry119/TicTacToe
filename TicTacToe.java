import java.util.Scanner;

public class TicTacToe{
	private char[][] board = new char [3][3];
	private char player = 'X'; 
	private boolean [][] vacant = new boolean [3][3];
	
	public TicTacToe() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++){
				board[i][j] = ' ';
				vacant[i][j] = true;
			}
		
	}
	

	public boolean play(String s) {
		boolean valid;
		int row = 0, column = 0;
		
		if (s.length() != 2)
			valid = false;
		else if (Character.toUpperCase(s.charAt(0)) < 'A' || Character.toUpperCase(s.charAt(0)) > 'C')
			valid = false;
		else if (s.charAt(1) < '1' || s.charAt(1) > '3')
			valid = false;
		else {
			
			switch(Character.toUpperCase(s.charAt(0)))
			{
			case 'A': row = 0;
					  break;
			case 'B': row = 1;
				      break;
			case 'C': row = 2;
					  break; 
			}
			switch(s.charAt(1))
			{
			case '1': column = 0;
					  break;
			case '2': column = 1;
					  break;
			case '3': column = 2;
					  break;
			}
			
			if (vacant[row][column]){
				vacant[row][column] = false;
				board[row][column] = player;
				valid = true;
			}
			else
				valid = false;
		}

		return valid; 
	}
	

	public void switchTurn() {
		player = player == 'X'? 'O': 'X';
	}
	

	public boolean won() {
		if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && !vacant[0][0])
			return true;
		if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && !vacant[1][0])
			return true;
		if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && !vacant[2][0])
			return true;
		if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && !vacant[0][0])
			return true;
		if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && !vacant[0][1])
			return true;
		if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && !vacant[0][2])
			return true;
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && !vacant[0][0])
			return true;
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && !vacant[0][2])
			return true;
		
		return false;
	}
	
	public boolean stalemate() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++){
				if (vacant[i][j] == true)
					return false;
			}
				
		 return true; 
	}
	
	public char getPlayer() {
		return player;
	}
	
	public void print() {
		System.out.println();
		System.out.println("\t  1 2 3");
		System.out.println();
		System.out.println("\tA "+board[0][0]+"|"+board[0][1]+"|"+board[0][2]);
		System.out.println("\t  -----");
		System.out.println("\tB "+board[1][0]+"|"+board[1][1]+"|"+board[1][2]);
		System.out.println("\t  "+"-----");
		System.out.println("\tC "+board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		String s;
		System.out.println("Welcome to tic-tac-toe");
		System.out.println("Enter coordinates for your move following the X and O prompts");
		
		while(!game.stalemate()) {
			game.print();
			System.out.print(game.getPlayer() + ": ");
			s = in.next();
			System.out.println();
			
			while (!game.play(s)){
				System.out.println("The move you entered is invalid. Please enter again.");
				System.out.print(game.getPlayer() + ": ");
				s = in.next();
				System.out.println();
			}
				
			if (game.won())
				break;
			game.switchTurn();
		}
		game.print();
		if(game.won()){
			System.out.println("Player "+game.getPlayer()+" Wins!!!!");
		} else {
			System.out.println("Stalemate");
		}
		in.close();
	}
	
}
