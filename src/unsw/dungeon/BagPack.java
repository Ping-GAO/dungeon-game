package unsw.dungeon;

import java.util.ArrayList;

public class BagPack {
	private ArrayList<Entity> bagPack;
	public BagPack() {
		bagPack = new ArrayList<Entity>();
	}
	public ArrayList<Entity> getBagPack() {
		return bagPack;
	}
	public void addToBagPack(Entity entity) {
		bagPack.add(entity);
	}
}
