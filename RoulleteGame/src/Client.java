import java.util.Scanner;

public class Client {
	
	private String name;
	private int amount=100;
	private int amountBetted;
	private boolean isConnected=false;
	private boolean isWinner=false;
	private int[] chosenNumbers;
	private Game.TypesOfBet typeOfBet;
	
	public void setName(String name){
		this.name = name;
	}
	
	public boolean setAmountBetted(int amountBetted){
		if(amountBetted>=1&&amountBetted<=10){
			this.amountBetted = amountBetted;
			return true;
		}
		else return false;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public void setIsConnected(boolean isConnected){
		this.isConnected = isConnected;
	}
	
	public void setIsWinner(boolean isWinner){
		this.isWinner = isWinner;
	}
	public void setChosenNumbers(int[] chosenNumbers){
		this.chosenNumbers=chosenNumbers;
	}
	public void setTypesOfBet(Game.TypesOfBet typeOfBet){
		this.typeOfBet = typeOfBet;
	}
	public String getName(){
		return this.name;
	}
	public int getAmountBetted(){
		return this.amountBetted;
	}
	public int getAmount(){
		return this.amount;
	}
	public boolean getIsConnected(){
		return this.isConnected;
	}
	public boolean getIsWinner(){
		return this.isWinner;
	}
	public int[] getChosenNumbers(){
		return this.chosenNumbers;
	}
	public Game.TypesOfBet getTypesOfBet(){
		return this.typeOfBet;
	}
	
	
	public int bet(){
		Scanner s = new Scanner(System.in);
		int amount = s.nextInt();
		while(true){
			if(setAmountBetted(amount) == true){
				if(amount<=this.amount){
					this.amount = this.amount-amount;
					System.out.println("You bet " + amount + "$!");
					break;
				}
				else{
					System.out.println("You have'n money!Please bet an amount less than" + (this.amount+1) + "$!");
					amount = s.nextInt();
				}
			}
			else{
				System.out.println("Incorrect amount! Choose another!");
				amount = s.nextInt();
			}
		}
		return amount;
	}
	
	
	public void playSingle(int winNumber){
		Scanner s = new Scanner(System.in);
		int chosenNumber;
		System.out.println("Choose a number between 0 and 36");
		while(true){
			chosenNumber = s.nextInt();
			if(chosenNumber>=0 && chosenNumber<=36){
				break;
			}
			else{
				System.out.println("Incorrect number. Try to choose another!");
			}
		}
		this.chosenNumbers = new int[1];
		this.chosenNumbers[0]=chosenNumber;
		if(chosenNumber==winNumber){
			isWinner=true;
		}
		else{
			isWinner=false;
		}
		typeOfBet = Game.TypesOfBet.SINGLE;
		
	}
	
	public void playSplit(int winNumber) {
		Scanner s = new Scanner(System.in);
		this.chosenNumbers = new int[2];
		int chosenNumber;
		String option;
		System.out.println("Type 'v' for vertically or 'h' for horizontally!");
		while(true){
			option = s.nextLine();
			if(option.equals("v") || option.equals("h")){
				break;
			}
			else{
				System.out.println("Incorrect option! Try to type another!");
			}
		}
		System.out.println("Choose the start's number in your choice!");
		while(true){
			//chosenNumber = Integer.parseInt(s.nextLine());
			chosenNumber = s.nextInt();
			if(option.equals("v")&&chosenNumber<=33&&chosenNumber>0){
				this.chosenNumbers[0] = chosenNumber;
				this.chosenNumbers[1]= chosenNumber + 3;
				break;
			}
			else if(option.equals("h")&&chosenNumber%3!=0&&chosenNumber>0){
				this.chosenNumbers[0] = chosenNumber;
				this.chosenNumbers[1]= chosenNumber + 1;
				break;
			}
			else{
				System.out.println("Incorrect choice! Try to type another!");
			}
			
		}
		if(this.chosenNumbers[0]==winNumber||this.chosenNumbers[1]==winNumber){
			isWinner=true;
		}
		else{
			isWinner=false;
		}
		typeOfBet = Game.TypesOfBet.SPLIT;
		
	}
	
	public void playRouge(int winNumber){
		if(Game.redNumbers.contains(winNumber) == true){
			isWinner = true;
		}
		else{
			isWinner = false;
		}
		typeOfBet = Game.TypesOfBet.ROUGE;
	}
	
	public void playPair(int winNumber){
		if(winNumber % 2==0){
			isWinner = true;
		}
		else{
			isWinner = false;
		}
		typeOfBet = Game.TypesOfBet.PAIR;
	}
	public void playStreet(int winNumber){
		
		Scanner s = new Scanner(System.in);
		int chosenLine;
		System.out.println("Choose the number of the line. Must be between 1 and 12");
		while(true){
			chosenLine = s.nextInt();
			if(chosenLine>=1 && chosenLine<=12){
				break;
			}
			else{
				System.out.println("Incorrect number. Try to choose another!");
			}
		}
		if(winNumber==Game.table[chosenLine][0]||winNumber==Game.table[chosenLine][1]||winNumber==Game.table[chosenLine][2]){
			isWinner=true;
		}
		else{
			isWinner=false;
		}
		typeOfBet = Game.TypesOfBet.STREET;
		
	}
	
	public void playCorner(int winNumber) {
		Scanner s = new Scanner(System.in);
		this.chosenNumbers = new int[4];
		int chosenNumber;
		System.out.println("Choose the first number in the chosen corner !");
		while(true){
			//chosenNumber = Integer.parseInt(s.nextLine());
			chosenNumber = s.nextInt();
			if(chosenNumber<=33&&chosenNumber>0){
				this.chosenNumbers[0] = chosenNumber;
				this.chosenNumbers[1]= chosenNumber + 1;
				this.chosenNumbers[2]= chosenNumber + 3;
				this.chosenNumbers[3]= chosenNumber + 4;
				break;
			}
			
			else{
				System.out.println("Incorrect choice! Try to type another!");
			}
			
		}
		if(this.chosenNumbers[0]==winNumber||this.chosenNumbers[1]==winNumber||this.chosenNumbers[2]==winNumber||this.chosenNumbers[3]==winNumber){
			isWinner=true;
		}
		else{
			isWinner=false;
		}
		typeOfBet = Game.TypesOfBet.CORNER;
		
	}
	
	
}
