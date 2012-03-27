import java.util.*;

public class ComputerPlayer implements Player {

	/**
	 * AI class implements the player interface IVs declared. First two array
	 * lists deal with possible shots that can be fired(based on strategy -
	 * mostly checkerboard) Second two array lists keep track of shots fired so
	 * that the AI does not repeat them
	 */
	private int x;
	private int y;
	private ArrayList<Integer> xShotsToBeFired = new ArrayList<Integer>();
	private ArrayList<Integer> yShotsToBeFired = new ArrayList<Integer>();
	private ArrayList<Integer> xShotsFired = new ArrayList<Integer>();
	private ArrayList<Integer> yShotsFired = new ArrayList<Integer>();
	private boolean isNearTarget;
	private boolean isSunk;
	private boolean shipHorizontal;
	/**
	 * counter keeps track of number of hits - useful when the AI must follow a
	 * ship till it sinks reference integers are the reference points for the AI
	 * once it lands a hit i.e. the points within close proximity that it must
	 * check boolean shipPursuit ensures that the AI pursues a ship once hit
	 * instead of picking a target number from the list of targets
	 */
	private int counter;
	private int reference1;
	private int reference2;
	private int randomChoice;
	private boolean shipPursuit;

	public ComputerPlayer() {

		reference1 = 0;
		reference2 = 0;
		counter = 0;
		shipHorizontal = false;
		shipPursuit = false;

		generatePossibleTargets();

	}

	private void generatePossibleTargets() {
		int ytarget;
		int xtarget;

		/**
		 * Generating a checkerboard set
		 */
		for (xtarget = 0; xtarget < 10; xtarget++) {
			if (xtarget % 2 != 0) {
				for (ytarget = 1; ytarget < 10; ytarget = ytarget + 2) {
					xShotsToBeFired.add(xtarget);
					yShotsToBeFired.add(ytarget);
				}
			}

			else {
				for (ytarget = 0; ytarget < 10; ytarget = ytarget + 2) {
					xShotsToBeFired.add(xtarget);
					yShotsToBeFired.add(ytarget);
				}
			}
		}

	}

	@Override
	public Location placeShip(int size, boolean retry) {

		x = (int) (Math.random() * 10);
		y = (int) (Math.random() * 10);

		int choice = (int) (Math.random() * 10);
		if (choice < 5) {
			ShipLocation s = new ShipLocation(x, y, "y");
			return s;
		} else {
			ShipLocation s = new ShipLocation(x, y, "n");
			return s;
		}
	}

	public Location getTarget() {

		/**
		 * With the help of the reference integers, checking the four points
		 * within close proximity if a ship is hit
		 */
		if (isNearTarget) {

			yShotsToBeFired.add(0, reference1 - 1);
			xShotsToBeFired.add(0, reference2);

			yShotsToBeFired.add(0, reference1);
			xShotsToBeFired.add(0, reference2 - 1);

			yShotsToBeFired.add(0, reference1 + 1);
			xShotsToBeFired.add(0, reference2);

			yShotsToBeFired.add(0, reference1);
			xShotsToBeFired.add(0, reference2 + 1);
		}
		/**
		 * filtering valid input and also preventing the AI from shooting at the
		 * same coordinates twice
		 */
		boolean isValid = false;

		Location tl = null;

		while (!isValid) {

			tl = getTargetHelper();

			isValid = true;

			if (tl.getX() < 0 || tl.getX() > 9 || tl.getY() < 0
					|| tl.getY() > 9)
				isValid = false;

			for (int i = 0; (i < xShotsFired.size() && isValid); i++) {
				if (xShotsFired.get(i) == tl.getX()
						&& yShotsFired.get(i) == tl.getY()) {

					isValid = false;
				}
			}
		}

		xShotsFired.add(0, tl.getX());
		yShotsFired.add(0, tl.getY());

		reference1 = tl.getY();
		reference2 = tl.getX();

		return tl;

	}

	private Location getTargetHelper() {
		/**
		 * If there's more than one hit, then the AI will check if the ship its
		 * been hitting is horizontal If it is, then it pursues it till its sunk
		 * If it turns out that the ship vertical, AI will run its normal method
		 * which incidentally checks vertical points first
		 */
		if (shipPursuit) {
			if (counter > 1) {
				ShipLocation l = null;
				for (int j = 0; j < xShotsFired.size(); j++) {
					if (xShotsFired.get(0) == xShotsFired.get(j)
							&& Math.abs(yShotsFired.get(0) - yShotsFired.get(j)) == 1) {
						shipHorizontal = true;

						break;
					}
				}

				if (shipHorizontal) {
					for (int i = 0; i < xShotsToBeFired.size(); i++) {
						if (xShotsToBeFired.get(i) == xShotsFired.get(0)
								&& Math.abs(yShotsToBeFired.get(i)
										- yShotsFired.get(0)) == 1) {
							l = new ShipLocation(xShotsToBeFired.remove(i),
									yShotsToBeFired.remove(i), "y");
							break;

						}
					}

				}

				if (l != null)
					return l;
			}
			ShipLocation t = new ShipLocation(xShotsToBeFired.remove(0),
					yShotsToBeFired.remove(0), "y");

			return t;
		} else {
			randomChoice = (int) (Math.random() * (xShotsToBeFired.size()));

			ShipLocation t = new ShipLocation(
					xShotsToBeFired.remove(randomChoice),
					yShotsToBeFired.remove(randomChoice), "y");
			return t;
		}

	}

	@Override
	public void setResult(boolean hit, boolean sunk) {

		/**
		 * Outputs AIs result Sets boolean value of hit to isNearTarget and
		 * increments counter if there's a hit If a ship is sunk, AI's values
		 * are reset so that it can look for another ship
		 */
		if (hit) {
			System.out.println("Computer Player hit a ship!");
		} else if (!(hit || sunk)) {
			System.out.println("Computer Player missed!");
		}
		isNearTarget = hit;

		if (isNearTarget) {
			counter++;
			shipPursuit = true;
		}

		isSunk = sunk;

		if (isSunk) {
			isNearTarget = false;
			shipHorizontal = false;
			shipPursuit = false;
			counter = 0;
		}

	}

}
