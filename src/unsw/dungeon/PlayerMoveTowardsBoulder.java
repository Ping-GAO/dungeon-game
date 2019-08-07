package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsBoulder implements PlayerMoveTowardsBehavior {
	private Boulder boulder;
	private Dungeon dungeon;
	private Player player;

	public PlayerMoveTowardsBoulder(Boulder boulder) {
		this.player = boulder.getDungeon().getPlayer();
		this.boulder = boulder;
		this.dungeon = boulder.getDungeon();
	}

	@Override
	public void moveTowards() {
		checkIfOnSwitch(boulder.getX(), boulder.getY());
		if (player.getX() != boulder.getX()) {
			if (player.getX() > boulder.getX()) {
				// left
				List<Entity> list = findAllEntityAt(boulder.getX() - 1, boulder.getY());
				for (Entity e : list) {
					e.PerformBeMovedTowardsbyBoulder(boulder);
				}
			} else {
				// right
				List<Entity> list = findAllEntityAt(boulder.getX() + 1, boulder.getY());
				for (Entity e : list) {
					e.PerformBeMovedTowardsbyBoulder(boulder);
				}
			}
		} else {
			if (player.getY() > boulder.getY()) {
				// up
				if (boulder.getY() > 0) {
					List<Entity> list = findAllEntityAt(boulder.getX(), boulder.getY() - 1);
					for (Entity e : list) {
						e.PerformBeMovedTowardsbyBoulder(boulder);
					}
				}
			} else {
				// down
				if (boulder.getY() < boulder.getDungeon().getHeight() - 1) {
					List<Entity> list = findAllEntityAt(boulder.getX(), boulder.getY() + 1);
					for (Entity e : list) {
						e.PerformBeMovedTowardsbyBoulder(boulder);
					}
				}
			}
		}
	}

	public void checkIfOnSwitch(int x, int y) {
		FloorSwitch floorSwitch = null;
		for (Entity e : boulder.getDungeon().getEntities()) {
			if (e != null) {
				if (e.getX() == x && e.getY() == y && e.getName().equals("floorSwitch")) {
					floorSwitch = (FloorSwitch) e;
				}
			}
		}
		if (floorSwitch != null) {
			floorSwitch.deactivate();
            player.setMessage("Floor switch deactivate.");
		}
	}


	public List<Entity> findAllEntityAt(int x, int y) {

		List<Entity> list = new ArrayList<>();
		for (Entity e : dungeon.getEntities()) {

            if (e != null && e.getX() == x && e.getY() == y) {
				list.add(e);
			}
		}

		if (list.isEmpty()) {
			EmptySpace emptySpace = new EmptySpace(dungeon, x, y);
			dungeon.addEntity(emptySpace);
			list.add(emptySpace);
		}
		list.sort((lhs, rhs) -> Integer.compare(rhs.getPushpriority(), lhs.getPushpriority()));
		return list;
	}

}
