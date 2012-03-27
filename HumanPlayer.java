import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {
	/**
	 * Class implements the player interface IVs for getting ship placements
	 * coordinates and attack coordinates Error handling to check for input
	 * mismatch (consequently null pointer) exceptions Also makes a base check
	 * of x and y to ensure that they are within the suitable range Consequently
	 * creates a location from Ship Location Set result informs player of the
	 * result of the last shot
	 */
	private int x;
	private int y;
	private ArrayList<Integer> xDone = new ArrayList<Integer>();
	private ArrayList<Integer> yDone = new ArrayList<Integer>();
	private String response;
	private boolean alreadyFired;

	public HumanPlayer() {
		alreadyFired = false;
	}

	public Location placeShip(int size, boolean retry) {

		while (true) {
			try {
				Scanner input = new Scanner(System.in);

				System.out.println("\n" + "You must place your ships");
				System.out
						.println("Where would you like to place your ship of size"
								+ " " + size);

				do {

					System.out
							.println("Enter the desired x coordinate (between 0 and 9)");
					x = input.nextInt();
				} while (x < 0 || x > 9);

				do {

					System.out
							.println("Enter the desired y coordinate (between 0 and 9)");
					y = input.nextInt();
				} while (y < 0 || y > 9);

				do {

					System.out.println("Place ship horizontally? (y/n)");
					response = input.next();
				}

				while (!(response.equals("y") || response.equals("n")));
			}

			catch (Exception e) {

				System.out.println("\n" + "Please reformat input");
				continue;
			}
			break;
		}

		ShipLocation s = new ShipLocation(x, y, response);

		return s;
	}

	public Location getTarget() {

		while (true) {
			try {
				Scanner shot = new Scanner(System.in);

				do {
					System.out
							.println("\n"
									+ "Player:Enter the desired X coordinate you would like to attack");

					x = shot.nextInt();
				} while (x < 0 || x > 9);

				do {
					System.out
							.println("Enter the desired Y coordinate you would like to attack ");

					y = shot.nextInt();
				} while (y < 0 || y > 9);

				for (int i = 0; i < xDone.size(); i++) {
					if (xDone.get(i) == x && yDone.get(i) == y) {
						alreadyFired = true;
						break;
					}

				}

				if (alreadyFired) {
					System.out.println("\n" + "Already used these coordinates");
					alreadyFired = false;
					continue;
				}

				else {

					xDone.add(x);
					yDone.add(y);
				}

			} catch (Exception e) {
				System.out.println("\n"
						+ "Invalid attack coordinates. Please re-enter");
				continue;
			}
			break;
		}

		ShipLocation t = new ShipLocation(x, y, "y");
		return t;

	}

	@Override
	public void setResult(boolean hit, boolean sunk) {

		if (hit) {
			System.out.println("You hit a ship!");
		}

		else if (!(hit || sunk)) {
			System.out.println("You missed!");
		}

		else if (sunk) {
			System.out.println("Sunk a ship");
		}

	}
}
