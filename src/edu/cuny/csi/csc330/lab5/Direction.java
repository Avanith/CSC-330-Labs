/**
 * Partially completed Direction ENUM 
 */

package edu.cuny.csi.csc330.lab5;

import edu.cuny.csi.csc330.util.Randomizer;

public enum Direction {
	NONE, NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST;
	// !!! Add 4 more Direction Values - NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST

	// methods
	public Direction getFavorite() {
		return SOUTH; // It's getting cold! ...
	}

	public Direction getNextRandom() {

		/******************************
		 * !!!!! WHAT CHANGES NEED TO BE MADE HERE SO THAT THE 4 NEW RANDOM DIRECTIONS
		 * ARE CONSIDERED
		 */
		int direction = Randomizer.generateInt(1, 8);

		// 1 = south, 2 = west, 3 = north, 4 = east
		// 5 = northeast, 6 = northwest, 7 = south-west, 8 = south-east
		if (direction == 1) { // south
			return SOUTH;
		} else if (direction == 2) { // west
			return WEST;
		} else if (direction == 3) { // north
			return NORTH;
		} else if (direction == 4) { // east
			return EAST;
		} else if (direction == 5) { // northeast
			return NORTHEAST;
		} else if (direction == 6) { // northwest
			return NORTHWEST;
		} else if (direction == 7) { // south-west
			return SOUTHWEST;
		} else { // south-east
			return SOUTHEAST;
		}
	}

	public static void main(String[] args) {

		int c = 0;
		while (c++ < 100) {
			System.out.println(Direction.NONE.getNextRandom());
		}

	}
}
