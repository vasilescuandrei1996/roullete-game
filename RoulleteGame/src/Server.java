import java.util.Scanner;

public class Server {
	
	//public static Scanner s;
	public static void listMenuBet(){
		System.out.println("Choose a type of bets!");
		System.out.println("1)Single");
		System.out.println("2)Split");
		System.out.println("3)Rouge");
		System.out.println("4)Pair");
		System.out.println("5)Street");
		System.out.println("6)Corner");
	}
	public static void listStartMenu(){
		System.out.println("Welcome to a Roullete Game!");
		System.out.println("Choose an option!");
		System.out.println("1)Play the game");
		System.out.println("0)Exit");
	}
	
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		boolean test = true;
		while(test == true){
			Server.listStartMenu();
			String option = s.nextLine();
			switch(option){
			case "1":{
				System.out.println("Let's start the game!");
				Game game = new Game();
				System.out.println("Choose the numbers of players. Must to be between 2 and 8");
				while((game.setNumberOfPlayers(s.nextInt()) == false)){
					System.out.println("Incorrect number! Try to type another!");
				}
				game.addPlayers();
				game.playGame();
				test=false;
				break;
			}
			case "0":{
				System.out.println("The application was closed!");
				test=false;
				break;
			}
			default:{
				System.out.println("Incorrect option! Try to tape another");
				break;
			}
			}
		}
		
		s.close();
		
		}

}
