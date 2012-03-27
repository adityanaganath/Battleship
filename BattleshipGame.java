import java.util.*;

public class BattleshipGame implements Game {

	/**
	 * Declaring all relevant IVs Each player has his/its own ships, ship
	 * locations and boards Class implements the game interface and consequently
	 * all its methods
	 */
	private ArrayList<Ship> Player1Ships = new ArrayList<Ship>();
	private ArrayList<Location> Player1ShipsLocation = new ArrayList<Location>();
	private ArrayList<Ship> Player2Ships = new ArrayList<Ship>();
	private ArrayList<Location> Player2ShipsLocation = new ArrayList<Location>();
	private Player p1;
	private Player p2;
	private String Player1Board[][];
	private String Player2Board[][];

	public BattleshipGame() {

	}

	private void gameSetup() {
		/**
		 * Adding all the ships from the game interface to each player's set of
		 * ships
		 */
		Ship first1 = new Ship(CARRIER);
		first1.setName("Carrier");

		Ship first2 = new Ship(CARRIER);
		first2.setName("Carrier");

		Ship second1 = new Ship(BATTLESHIP);
		second1.setName("Battleship");

		Ship second2 = new Ship(BATTLESHIP);
		second2.setName("Battleship");

		Ship third1 = new Ship(SUBMARINE);
		third1.setName("Submarine");

		Ship third2 = new Ship(SUBMARINE);
		third2.setName("Submarine");

		Ship fourth1 = new Ship(CRUISER);
		fourth1.setName("Cruiser");

		Ship fourth2 = new Ship(CRUISER);
		fourth2.setName("Cruiser");

		Ship fifth1 = new Ship(DESTROYER);
		fifth1.setName("Destroyer");

		Ship fifth2 = new Ship(DESTROYER);
		fifth2.setName("Destroyer");

		Player1Ships.add(first1);
		Player2Ships.add(first2);
		Player1Ships.add(second1);
		Player2Ships.add(second2);
		Player1Ships.add(third1);
		Player2Ships.add(third2);
		Player1Ships.add(fourth1);
		Player2Ships.add(fourth2);
		Player1Ships.add(fifth1);
		Player2Ships.add(fifth2);
	}

	/**
	 * Checks to see if player's one ship placement choice is invalid by
	 * checking for overlaps with existing ships
	 * 
	 * @param l
	 *            -ship location passed by the player
	 * @param s
	 *            - ship whose location was passed
	 * @return - boolean that determines overlap or not.
	 */

	private boolean comparePlayer1Ships(Location l, Ship s) {
		boolean overlapping = false;

		for (int i = 0; i < Player1ShipsLocation.size(); i++) {

			if (Player1ShipsLocation.get(i).isShipHorizontal() == true) {
				/**
				 * Have for conditions - existing ship is horizontal and your
				 * ship is either horizontal or vertical or existing ship is
				 * vertical and your ship is either horizontal or vertical
				 * Overlap checking can be thought of as either a ship's
				 * extension overlaps or the the top left coordinate overlaps-
				 * Given that we are on the same row/column
				 */
				if (l.isShipHorizontal() == true) {
					if (((((l.getY() + s.getSize()) <= (Player1ShipsLocation
							.get(i).getY() + Player1Ships.get(i).getSize())) && (l
							.getY() + s.getSize() - 1 >= Player1ShipsLocation
							.get(i).getY())) || ((l.getY() >= Player1ShipsLocation
							.get(i).getY()) && (l.getY() <= (Player1ShipsLocation
							.get(i).getY() + Player1Ships.get(i).getSize() - 1))))
							&& (l.getX() == Player1ShipsLocation.get(i).getX())) {
						overlapping = true;
					}
				} else {

					if ((((l.getX() < Player1ShipsLocation.get(i).getX()) && (l
							.getX() + s.getSize() - 1 >= Player1ShipsLocation
							.get(i).getX())) || (l.getX() == Player1ShipsLocation
							.get(i).getX()))
							&& (l.getY() >= Player1ShipsLocation.get(i).getY() && (l
									.getY() <= (Player1ShipsLocation.get(i)
									.getY() + Player1Ships.get(i).getSize() - 1)))) {
						overlapping = true;
					}
				}
			} else if (Player1ShipsLocation.get(i).isShipHorizontal() == false) {
				if (l.isShipHorizontal() == true) {
					if ((((l.getY() <= Player1ShipsLocation.get(i).getY()) && (l
							.getY() + s.getSize() - 1 >= Player1ShipsLocation
							.get(i).getY())) || (l.getY() == Player1ShipsLocation
							.get(i).getY()))
							&& (l.getX() >= Player1ShipsLocation.get(i).getX() && (l
									.getX() <= (Player1ShipsLocation.get(i)
									.getX() + Player1Ships.get(i).getSize() - 1)))) {
						overlapping = true;
					}
				} else {

					if ((((l.getX() + s.getSize()) <= (Player1ShipsLocation
							.get(i).getX() + Player1Ships.get(i).getSize()) && (l
							.getX() + s.getSize() - 1 >= Player1ShipsLocation
							.get(i).getX())) || ((l.getX() >= Player1ShipsLocation
							.get(i).getX()) && (l.getX() <= (Player1ShipsLocation
							.get(i).getX() + Player1Ships.get(i).getSize() - 1))))
							&& (l.getY() == Player1ShipsLocation.get(i).getY())) {
						overlapping = true;

					}
				}
			}
		}

		return overlapping;
	}

	private boolean comparePlayer2Ships(Location l, Ship s) {
		boolean overlapping = false;

		for (int i = 0; i < Player2ShipsLocation.size(); i++) {

			if (Player2ShipsLocation.get(i).isShipHorizontal() == true) {

				if (l.isShipHorizontal() == true) {
					if (((((l.getY() + s.getSize()) <= (Player2ShipsLocation
							.get(i).getY() + Player2Ships.get(i).getSize())) && (l
							.getY() + s.getSize() - 1 >= Player2ShipsLocation
							.get(i).getY())) || ((l.getY() >= Player2ShipsLocation
							.get(i).getY()) && (l.getY() <= (Player2ShipsLocation
							.get(i).getY() + Player2Ships.get(i).getSize() - 1))))
							&& (l.getX() == Player2ShipsLocation.get(i).getX())) {
						overlapping = true;
					}
				} else {

					if ((((l.getX() < Player2ShipsLocation.get(i).getX()) && (l
							.getX() + s.getSize() - 1 >= Player2ShipsLocation
							.get(i).getX())) || (l.getX() == Player2ShipsLocation
							.get(i).getX()))
							&& (l.getY() >= Player2ShipsLocation.get(i).getY() && (l
									.getY() <= (Player2ShipsLocation.get(i)
									.getY() + Player2Ships.get(i).getSize() - 1)))) {
						overlapping = true;
					}
				}
			} else if (Player2ShipsLocation.get(i).isShipHorizontal() == false) {
				if (l.isShipHorizontal() == true) {
					if ((((l.getY() < Player2ShipsLocation.get(i).getY()) && (l
							.getY() + s.getSize() - 1 >= Player2ShipsLocation
							.get(i).getY())) || (l.getY() == Player2ShipsLocation
							.get(i).getY()))
							&& (l.getX() >= Player2ShipsLocation.get(i).getX() && (l
									.getX() <= (Player2ShipsLocation.get(i)
									.getX() + Player2Ships.get(i).getSize() - 1)))) {
						overlapping = true;
					}
				} else {

					if ((((l.getX() + s.getSize()) <= (Player2ShipsLocation
							.get(i).getX() + Player2Ships.get(i).getSize()) && (l
							.getX() + s.getSize() - 1 >= Player2ShipsLocation
							.get(i).getX())) || ((l.getX() >= Player2ShipsLocation
							.get(i).getX()) && (l.getX() <= (Player2ShipsLocation
							.get(i).getX() + Player2Ships.get(i).getSize() - 1))))
							&& (l.getY() == Player2ShipsLocation.get(i).getY())) {
						overlapping = true;

					}
				}
			}
		}

		return overlapping;
	}

	private void setPlayer1Ships() {
		/**
		 * Checks for overlapping and whether a ship falls out of bounds for
		 * each ship Boolean retry keeps track of this As long as retry is true,
		 * we keep asking the player to choose placing coordinates
		 */

		for (Ship gameShips : Player1Ships) {

			System.out.println("Player 1's turn");
			boolean retry = true;
			Location l = p1.placeShip(gameShips.getSize(), false);

			int xsize = 1;

			if (!l.isShipHorizontal()) {
				xsize = gameShips.getSize();
			}

			int ysize = 1;
			if (l.isShipHorizontal()) {
				ysize = gameShips.getSize();
			}

			if ((!comparePlayer1Ships(l, gameShips))
					&& ((l.getX() + xsize <= SIZE && l.getX() >= 0) && (l
							.getY() + ysize <= SIZE && l.getY() >= 0))) {
				retry = false;
				Player1ShipsLocation.add(l);
			}

			while (retry) {

				System.out
						.println("\n"
								+ "Your ship cannot have such a designation. Please re-enter your desired coordinates");
				l = p1.placeShip(gameShips.getSize(), retry);

				xsize = 1;
				if (!l.isShipHorizontal()) {
					xsize = gameShips.getSize();
				}

				ysize = 1;
				if (l.isShipHorizontal()) {
					ysize = gameShips.getSize();
				}

				if ((!comparePlayer1Ships(l, gameShips))
						&& ((l.getX() + xsize <= SIZE && l.getX() >= 0) && (l
								.getY() + ysize <= SIZE && l.getY() >= 0))) {
					retry = false;
					Player1ShipsLocation.add(l);
				}
			}
		}

	}

	private void setPlayer2Ships() {

		for (Ship gameShips : Player2Ships) {

			System.out.println("Player 2's turn");
			boolean retry = true;
			Location l = p2.placeShip(gameShips.getSize(), false);

			int xsize = 1;
			if (l.isShipHorizontal() == false) {
				xsize = gameShips.getSize();
			}

			int ysize = 1;
			if (l.isShipHorizontal() == true) {
				ysize = gameShips.getSize();
			}

			if ((!comparePlayer2Ships(l, gameShips))
					&& ((l.getX() + xsize <= SIZE && l.getX() >= 0) && (l
							.getY() + ysize <= SIZE && l.getY() >= 0))) {
				retry = false;
				Player2ShipsLocation.add(l);

			}

			while (retry) {

				System.out
						.println("\n"
								+ "Your ship cannot have such a designation. Please re-enter your desired coordinates");
				l = p2.placeShip(gameShips.getSize(), retry);

				xsize = 1;
				if (l.isShipHorizontal() == false) {
					xsize = gameShips.getSize();
				}

				ysize = 1;
				if (l.isShipHorizontal() == true) {
					ysize = gameShips.getSize();
				}
				if ((comparePlayer2Ships(l, gameShips) == false)
						&& ((l.getX() + xsize <= SIZE && l.getX() >= 0) && (l
								.getY() + ysize <= SIZE && l.getY() >= 0))) {
					retry = false;
					Player2ShipsLocation.add(l);

				}
			}
		}

	}

	/**
	 * Board is 10 by 10 (2D array of strings) Every space occupied by a ship is
	 * "1" A space not occupied is represented by a "0" A spot that is hit is
	 * changed from "1" to "0"
	 */
	private void displayPlayer1Board() {
		Player1Board = new String[10][10];

		for (Ship gameShip : Player1Ships) {
			for (int j = 0; j < (gameShip.getXCoordinates().size()); j++) {
				for (int i = 0; i < (gameShip.getYCoordinates().size()); i++) {
					Player1Board[gameShip.getXCoordinates().get(i)][gameShip
							.getYCoordinates().get(j)] = "1";
				}
			}
		}

		for (int a = 0; a < 10; a++) {
			System.out.println("");
			for (int b = 0; b < 10; b++) {
				if (Player1Board[a][b] == null) {
					System.out.print("" + "0");
				}

				else {
					System.out.print("" + Player1Board[a][b]);
				}
			}
		}

	}

	private void displayPlayer2Board() {
		Player2Board = new String[10][10];

		for (Ship gameShip : Player2Ships) {
			for (int j = 0; j < (gameShip.getXCoordinates().size()); j++) {
				for (int i = 0; i < (gameShip.getYCoordinates().size()); i++) {
					Player2Board[gameShip.getXCoordinates().get(i)][gameShip
							.getYCoordinates().get(j)] = "1";
				}
			}
		}

		for (int a = 0; a < 10; a++) {
			System.out.println("");
			for (int b = 0; b < 10; b++) {
				if (Player2Board[a][b] == null) {
					System.out.print("" + "0");
				}

				else {
					System.out.print("" + Player2Board[a][b]);
				}
			}
		}

	}

	/**
	 * Initializes players and sets up board and ships Sets location coordinates
	 * in the ship class
	 */
	@Override
	public void initialize(Player p1, Player p2) {

		this.p1 = p1;
		this.p2 = p2;

		gameSetup();
		setPlayer1Ships();
		setPlayer2Ships();

		for (int i = 0; i < 5; i++) {
			Player1Ships.get(i).setLocationCoordinates(
					Player1ShipsLocation.get(i), Player1Ships.get(i));
			Player2Ships.get(i).setLocationCoordinates(
					Player2ShipsLocation.get(i), Player2Ships.get(i));
		}
	}

	private void Player1Turn() {

		System.out.println("\n" + "Player1's turn");
		System.out.println("Your board");

		displayPlayer1Board();

		System.out.println();

		Location l1 = p1.getTarget();

		boolean miss = true;

		for (int i = 0; i < Player2Ships.size(); i++) {
			/**
			 * method called from the ship class that determines the result of a
			 * shot setResult method from player class is called that officially
			 * sets the result based on the string returned from the shipFate
			 * method.
			 */
			String fate = Player2Ships.get(i).shipFate(l1.getX(), l1.getY());

			if (fate.equals("miss")) {

			} else if (fate.equals("hit")) {
				p1.setResult(true, false);
				miss = false;
				break;
			} else if (fate.equals("sunk")) {
				p1.setResult(false, true);
				miss = false;
				Player2Ships.remove(Player2Ships.get(i));
				break;
			}
		}
		if (miss) {
			p1.setResult(false, false);
		}

	}

	private void Player2Turn() {

		System.out.println("\n" + "Player2's turn");
		System.out.println("Your board");

		displayPlayer2Board();

		System.out.println();

		Location l2 = p2.getTarget();

		boolean miss = true;

		for (int i = 0; i < Player1Ships.size(); i++) {

			String fate = Player1Ships.get(i).shipFate(l2.getX(), l2.getY());

			if (fate.equals("miss")) {

			}

			else if (fate.equals("hit")) {
				p2.setResult(true, false);
				miss = false;
				break;
			} else if (fate.equals("sunk")) {
				p2.setResult(false, true);
				miss = false;
				Player1Ships.remove(Player1Ships.get(i));
				break;
			}
		}
		if (miss) {
			p2.setResult(false, false);
		}

	}

	@Override
	/**
	 * Runs game till either player's ships are all sunk
	 */
	public Player playGame() {

		while (!(Player1Ships.isEmpty() || Player2Ships.isEmpty())) {

			Player1Turn();
			Player2Turn();

		}
		if (Player1Ships.isEmpty()) {
			return p2;
		} else if (Player2Ships.isEmpty()) {
			return p1;
		}
		return null;
	}

}
