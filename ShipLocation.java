public class ShipLocation implements Location {
	/**
	 * Class implements the location interface Returns the x and y coordinates
	 * and ship orientation
	 */
	private int xcoord;
	private int ycoord;
	private boolean horizontal;

	public ShipLocation(int x, int y, String horizontal) {

		xcoord = x;
		ycoord = y;
		if (horizontal.equals("y")) {
			this.horizontal = true;
		} else {
			this.horizontal = false;
		}
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return xcoord;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return ycoord;
	}

	@Override
	public boolean isShipHorizontal() {
		// TODO Auto-generated method stub
		return horizontal;
	}
}
