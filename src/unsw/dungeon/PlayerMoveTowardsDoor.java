package unsw.dungeon;

/**
 * @author Ping GAO
 */
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
					door.getDungeon().getPlayer().setMessage("Key matched the door.\n");
					found = true;
					break;
				} else {
					door.getDungeon().getPlayer().setMessage("Key didn't match the door");
				}
			}
		}
		if (found) {
			door.changeToOpenState();
			bagpack.getBagPack().remove(key);
		}
	}
}
