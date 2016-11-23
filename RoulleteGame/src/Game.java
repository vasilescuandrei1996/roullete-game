import java.util.*;

public class Game {

	private int numberOfPlayers;
	private ArrayList<Client> listOfPlayers;
	private int amountBetted=0;
	public enum TypesOfBet { SINGLE, SPLIT, STREET, CORNER,DOUBLE_STREET,TRIO,BASKET,TOP_LINE,MANQUE,PASSE,ROUGE,NOIR,PAIR,IMPAIR,DOZEN,COLUMN,SNAKE };
	public static  int[][] table;
	public static  ArrayList<Integer> redNumbers;
	
	public boolean setNumberOfPlayers(int numberOfPlayers){
		if(numberOfPlayers>=2&&numberOfPlayers<=8){
			this.numberOfPlayers = numberOfPlayers;
			return true;
		}
		else return false;
	}
	public void setAmountBetted(int amountBetted){
		this.amountBetted = amountBetted;
	}
	public int getNumberOfPlayers(){
		return this.numberOfPlayers;
	}
	public int getAmountBetted(){
		return this.amountBetted;
	}
	public ArrayList<Client> getListOfPlayers(){
		return this.listOfPlayers;
	}
	public Game(){
		listOfPlayers = new ArrayList<Client>();
		redNumbers = new ArrayList<Integer>();
		redNumbers.add(2);
		redNumbers.add(4);
		redNumbers.add(6);
		redNumbers.add(8);
		redNumbers.add(10);
		redNumbers.add(11);
		redNumbers.add(13);
		redNumbers.add(17);
		redNumbers.add(20);
		redNumbers.add(22);
		redNumbers.add(24);
		redNumbers.add(26);
		redNumbers.add(28);
		redNumbers.add(29);
		redNumbers.add(31);
		redNumbers.add(33);
		redNumbers.add(33);
		table = new int[13][3];
		for(int j=0;j<3;j++){
			table[0][j]=0;
		}
		int count = 1;
		for(int i=1;i<13;i++){
			for(int j=0;j<3;j++){
				table[i][j] = count;
				count = count + 1;
			}
		}
	}
	
	public boolean verifyIfNameExist(String name){
		for(int i=0;i<listOfPlayers.size();i++){
			if(listOfPlayers.get(i).getName().equals(name) == true){
				return true;
			}
		}
		return false;
	}
	
	public int numberOfWinners(){
		int numberOfWinners=0;
		for(int i=0;i<listOfPlayers.size();i++){
			if(listOfPlayers.get(i).getIsWinner()==true){
				numberOfWinners = numberOfWinners + 1;
			}
		}
		return numberOfWinners;
	}
	
	
	public void addPlayers(){
		Scanner s = new Scanner(System.in);
		for(int i=0;i<numberOfPlayers;i++){
			Client client = new Client();
			System.out.println("Choose a name for the Player " + (i+1));
			String name;
			name = s.nextLine();
			while(verifyIfNameExist(name) == true){
				System.out.println("This name was chosen. Choose another!");
				name = s.nextLine();
			}
			System.out.println(name + " is connected!");
			client.setName(name);
			client.setIsConnected(true);
			listOfPlayers.add(i, client);
			
		}
		//s.close();
	}
	
	
	
	public void allocateTheWin(){
		
		int oldAmount;
		int newAmount=0;
		Client client;
		for(int i=0;i<listOfPlayers.size();i++){
			client = listOfPlayers.get(i);
			if(client.getIsWinner()){
				oldAmount = client.getAmount();
				switch(client.getTypesOfBet()){
				case SINGLE:{
					newAmount = oldAmount + client.getAmountBetted() * 35;
					break;
				}
				case SPLIT:{
					newAmount = oldAmount + client.getAmountBetted() * 17;
					break;
				}
				case ROUGE:{
					newAmount = oldAmount + client.getAmountBetted();
					break;
				}
				case PAIR:{
					newAmount = oldAmount + client.getAmountBetted();
					break;
				}
				case STREET:{
					newAmount = oldAmount + client.getAmountBetted() * 11;
					break;
				}
				case CORNER:{
					newAmount = oldAmount + client.getAmountBetted() * 8;
					break;
				}
				}
				client.setAmount(newAmount);
				System.out.println(client.getName() + ", you win " + (newAmount-oldAmount) + "$ and now you have " + client.getAmount() + "$!");
			}
		}
		
	}
	
	public void elimanatePlayers(){
		Client client;
		for(int i=0;i<listOfPlayers.size();i++){
			client = listOfPlayers.get(i);
			if(client.getAmount()==0){
				numberOfPlayers-=1;
				System.out.println(client.getName() + " are 0$ and he is eliminated!");
				client.setIsConnected(false);
				listOfPlayers.remove(i);
			}
		}
	}
	
	public void playGame(){
		Scanner s = new Scanner(System.in);
		int winNumber;
		int numberOfWinners;
		Client client;
		boolean test;
		String option;
		while(true){
			amountBetted = 0;
			winNumber = (int)(Math.random()*36+1);
			System.out.println(winNumber + "!Ups!You show the number!" );
			
			for(int i=0;i<numberOfPlayers;i++){
				client = listOfPlayers.get(i);
				System.out.println(client.getName() + " you have " + client.getAmount() + "$.Choose a bet between 1-10 $!");
				amountBetted += client.bet();
				test = true;
				while(test == true){
					Server.listMenuBet();
					option = s.nextLine();
					switch(option){
					case "1":{
						client.playSingle(winNumber);
						test=false;
						break;
					}
					case "2":{
						client.playSplit(winNumber);
						test=false;
						break;
					}
					case "3":{
						client.playRouge(winNumber);
						test=false;
						break;
					}
					case "4":{
						client.playPair(winNumber);
						test=false;
						break;
					}
					case "5":{
						client.playStreet(winNumber);
						test=false;
						break;
					}
					case "6":{
						client.playCorner(winNumber);
						test=false;
						break;
					}
					default:{
						System.out.println("Incorrect option.Choose another!");
						break;
					}
					}
				}
				
				
			}
			System.out.println("Winning number is" + winNumber);
			numberOfWinners=numberOfWinners();
			System.out.println(numberOfWinners + " winners in this game!");
			if(numberOfWinners > 0){
				allocateTheWin();
			}
			elimanatePlayers();
			if(listOfPlayers.size()==1){
				System.out.println("The winner is " + listOfPlayers.get(0).getName());
				break;
			}
			System.out.println("\n");
			
		
			
			
		}
		
		
	}

	
}



