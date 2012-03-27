import java.util.Scanner;

public class BattleShipTester {

	/**
	 * Class runs the battleship game and handles errors Instantiates relevant
	 * player objects based on user choice Outputs winner and handles some more
	 * errors if any
	 * 
	 * @param args
	 * @author aditya naganath
	 */
	public static void main(String[] args) {

		try {

			System.out.println("Welcome to Battleship");

			BattleshipGame b = new BattleshipGame();
			Scanner input = new Scanner(System.in);

			Player p1 = null;
			Player p2 = null;

			String choice;
			do {

				System.out
						.println("Play against another player or computer or watch two computers play? (h/c/w)");
				choice = input.nextLine();
			} while (!(choice.equals("h") || choice.equals("c") || choice
					.equals("w")));

			if (choice.equals("h")) {
				p1 = new HumanPlayer();
				p2 = new HumanPlayer();
				b.initialize(p1, p2);
			}

			else if (choice.equals("c")) {
				p1 = new HumanPlayer();
				p2 = new ComputerPlayer();
				b.initialize(p1, p2);
			}

			else if (choice.equals("w")) {

				p1 = new ComputerPlayer();
				p2 = new ComputerPlayer();
				b.initialize(p1, p2);

			}

			Player winner = b.playGame();

			if (winner == p1) {
				System.out.println("Player 1 won!");

			}

			else if (winner == p2) {
				System.out.println("Player 2 won!");
			}

		}

		catch (StringIndexOutOfBoundsException e) {

		}
		
		catch (IndexOutOfBoundsException e) {
			
		}

	}

}
