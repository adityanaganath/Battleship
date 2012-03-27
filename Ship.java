import java.util.*;

public class Ship {

	/**
	 * Ship class characterizes a ship based on location coordinates, name and
	 * size setLocation coordinates extrapolates the x and y coordinates given
	 * the initial coordinates and ship orientation. shipFate method returns a
	 * string of the result by comparing the target with each of the location
	 * coordinates
	 */

	private ArrayList<Integer> xLocationCoordinates;
	private ArrayList<Integer> yLocationCoordinates;
	private String name;
	private int size;

	public Ship(int s) {

		xLocationCoordinates = new ArrayList<Integer>();
		yLocationCoordinates = new ArrayList<Integer>();
		size = s;
	}

	public int getSize() {
		return size;
	}

	public void setName(String n) {
		name = n;
	}

	public ArrayList<Integer> getXCoordinates() {
		return xLocationCoordinates;
	}

	public ArrayList<Integer> getYCoordinates() {
		return yLocationCoordinates;
	}

	public void setLocationCoordinates(Location coordinates, Ship s) {

		int i;
		if (coordinates.isShipHorizontal() == true) {
			for (i = coordinates.getY(); i <= (coordinates.getY() + (s
					.getSize() - 1)); i++) {
				yLocationCoordinates.add(i);
				xLocationCoordinates.add(coordinates.getX());
			}
		} else {
			for (i = coordinates.getX(); i <= (coordinates.getX() + (s
					.getSize() - 1)); i++) {
				xLocationCoordinates.add(i);
				yLocationCoordinates.add(coordinates.getY());
			}
		}
	}

	public String shipFate(int x, int y) {

		String fate = "miss";

		for (int j = 0; j < xLocationCoordinates.size(); j++) {
			if (x == xLocationCoordinates.get(j).intValue()
					&& y == yLocationCoordinates.get(j).intValue()) {
				xLocationCoordinates.remove(j);
				yLocationCoordinates.remove(j);

				if (xLocationCoordinates.isEmpty()) {
					fate = "sunk";
					System.out.println("sunk" + " " + name);
					break;
				} else {
					System.out.println(xLocationCoordinates.size());
					fate = "hit";
					break;
				}
			}
		}

		return fate;

	}
}
