package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

	private BagPack bagPack;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y,String name) {
		super(dungeon, x, y,name);
		this.bagPack = new BagPack();

	}

	public BagPack getBagPack() {
		return bagPack;
	}

	public void moveUp() {
		if (getY() > 0) {
			Entity next = findEntityAt(getX(), getY() - 1);
			List<Entity> list = findAllEntityAt(getX(), getY() - 1);
			for(Entity e: list) {
				e.PerformBePickedUp();
			}
			next.PerformBeMovedTowardsbyPlayer();
		}

	}

	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1) {
			Entity next = findEntityAt(getX(), getY() + 1);
			List<Entity> list = findAllEntityAt(getX(), getY() + 1);
			for(Entity e: list) {
				e.PerformBePickedUp();
			}
			next.PerformBeMovedTowardsbyPlayer();
		}

	}

	public void moveLeft() {
		if (this.getX() > 0) {
			Entity next = findEntityAt(getX() - 1, getY());
			List<Entity> list = findAllEntityAt(getX() - 1, getY());
			for(Entity e: list) {
				e.PerformBePickedUp();
			}
			next.PerformBeMovedTowardsbyPlayer();
		}

	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Entity next = findEntityAt(getX() + 1, getY());
			List<Entity> list = findAllEntityAt(getX() + 1, getY());
			for(Entity e: list) {
				e.PerformBePickedUp();
			}
			next.PerformBeMovedTowardsbyPlayer();
		}

	}
	
	
	public boolean checkIfHaveBomb() {
		boolean found  = false;
		for(Entity e: this.bagPack.getBagPack()) {
			if(e.getName().equals("bomb")) {
				found = true;
			}
		}
		return found;
	}
	
	
	public Entity findEntityAt(int x, int y) {
		Entity found = null;
		for (Entity e : dungeon.getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				found = e;
				return found;
			}
		}
		EmptySpace emptySpace = new EmptySpace(dungeon, x, y,name);
		dungeon.addEntity(emptySpace);
		return emptySpace;
	}
	
	
	public List<Entity> findAllEntityAt(int x, int y) {
		
		List<Entity> list = new ArrayList<Entity> ();  
		for (Entity e : dungeon.getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				list.add(e);
				
			}
		}
		
		if(list.isEmpty()) {
			EmptySpace emptySpace = new EmptySpace(dungeon, x, y,name);
			dungeon.addEntity(emptySpace);
			list.add(emptySpace);
		}
		return list;
	}

}

