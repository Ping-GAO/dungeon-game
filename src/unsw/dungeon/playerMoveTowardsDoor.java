package unsw.dungeon;

public class playerMoveTowardsDoor implements playerMoveTowardsBehavior{
	private BagPack bagpack;
	private Door door;
	public playerMoveTowardsDoor(Door door) {
		this.bagpack = door.getDungeon().getPlayer().getBagPack();
		this.door = door;
	}
	@Override
	public void moveTowards() {
		boolean found = false;
		Key key = null;
	
		for(Entity e : bagpack.getBagPack()) {
			if(e.getName().equals("key")) {
				found = true;
				key = (Key) e;
				break;
			}
		}
		
		
		if(found) {
			door.isOpen().set(true);
			door.changeToOpenState();
			bagpack.getBagPack().remove(key);
		}
		
	}

}
