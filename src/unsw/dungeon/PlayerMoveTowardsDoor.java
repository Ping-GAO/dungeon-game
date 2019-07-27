package unsw.dungeon;

public class PlayerMoveTowardsDoor implements PlayerMoveTowardsBehavior {
	private BagPack bagpack;
	private Door door;

	public PlayerMoveTowardsDoor(Door door) {
		this.bagpack = door.getDungeon().getPlayer().getBagPack();
		this.door = door;
	}

	@Override
	public void moveTowards() {
		boolean found = false;
		Key key = null;

		for (Entity e : bagpack.getBagPack()) {
			if (e.getName().equals("key")) {
				key = (Key) e;
				if (key.getId() == door.getId()) {
					System.out.println("key has id " + key.getId() + " door has id " + door.getId());
					System.out.println("matched!");
					found = true;
					break;
				} else {
					System.out.println("key has id " + key.getId() + " door has id " + door.getId());
				}
			}
		}
		if (found) {
			door.changeToOpenState();
			bagpack.getBagPack().remove(key);
		}
	}
}
