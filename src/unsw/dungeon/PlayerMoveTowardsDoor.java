package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsDoor implements PlayerMoveTowardsBehavior {
	private BagPack bagpack;
	private Door door;
	private Dungeon dungeon;

	public PlayerMoveTowardsDoor(Door door) {
		this.dungeon = door.getDungeon();
		this.bagpack = dungeon.getPlayer().getBagPack();
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
					dungeon.getPlayer().setMessage("Key matched the door.\n");
					found = true;
					break;
				} else {

					dungeon.getPlayer().setMessage("Key didn't match the door");
				}
			}
		}
		if (found) {
			door.changeToOpenState();
			bagpack.getBagPack().remove(key);
		}
	}
}
