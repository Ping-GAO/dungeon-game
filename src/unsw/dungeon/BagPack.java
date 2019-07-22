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

	@Override
	public String toString() {
		String result = "";
		
		
		for(int i=0;i<bagPack.size();i++) {
			result += bagPack.get(i).getName();
			if(i!=bagPack.size()-1) {
				result += " ";
			}
		}
		return result;
	}
}
