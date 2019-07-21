package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private ArrayList<Entity> BagPack;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.BagPack = new ArrayList<Entity>();

	}

	public void moveUp() {
		if (getY() > 0) {
			Entity next = findEntityAt(getX(), getY() - 1);
			next.PerformMoveTowards();
		}

	}

	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1) {
			Entity next = findEntityAt(getX(), getY() + 1);
			next.PerformMoveTowards();
		}

	}

	public void moveLeft() {
		if (this.getX() > 0) {
			Entity next = findEntityAt(getX() - 1, getY());
			next.PerformMoveTowards();
		}

	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Entity next = findEntityAt(getX() + 1, getY());
			next.PerformMoveTowards();
		}

	}

	public Entity findEntityAt(int x, int y) {
		Entity found = null;
		for (Entity e : dungeon.getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				found = e;
				return found;
			}
		}
		EmptySpace emptySpace = new EmptySpace(dungeon, x, y);
		dungeon.addEntity(emptySpace);
		return emptySpace;
	}

}
