package unsw.dungeon;

public class playerMoveTowardsBoulder implements playerMoveTowardsBehavior {
	private Boulder boulder;

	private Player player;

	public playerMoveTowardsBoulder(Boulder boulder) {
		this.player = boulder.getDungeon().getPlayer();
		this.boulder = boulder;
	}

	@Override
	public void moveTowards() {
		if (player.getX() != boulder.getX()) {
			if (player.getX() > boulder.getX()) {
				// left
				Entity next = findEntityAt(boulder.getX() - 1, boulder.getY());

				next.PerformBeMovedTowardsbyBoulder(boulder);
			} else {
				// right
				
				Entity next = findEntityAt(boulder.getX() + 1, boulder.getY());
				System.out.println("right  is " + next.getName());
				next.PerformBeMovedTowardsbyBoulder(boulder);
			}
		} else {
			if (player.getY() > boulder.getY()) {
				// up
				if (boulder.getY() > 0) {
					Entity next = findEntityAt(boulder.getX(), boulder.getY() - 1);

					next.PerformBeMovedTowardsbyBoulder(boulder);

				}
			} else {
				if (boulder.getY() < boulder.getDungeon().getHeight() - 1) {
					Entity next = findEntityAt(boulder.getX(), boulder.getY() + 1);
					next.PerformBeMovedTowardsbyBoulder(boulder);

				}
			}
		}
	}

	public Entity findEntityAt(int x, int y) {
		Entity found = null;
		for (Entity e : boulder.getDungeon().getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				found = e;
				
				return found;
			}
		}
		EmptySpace emptySpace = new EmptySpace(boulder.getDungeon(), x, y,"emptySpace");
		boulder.getDungeon().addEntity(emptySpace);
		return emptySpace;
	}

}
