package unsw.dungeon;

public class playerMoveTowadsPressurePlate implements playerMoveTowardsBehavior {
	private PressuredPlate pressuredPlate;
	private Player player;
	private Dungeon dungeon;

	public playerMoveTowadsPressurePlate(PressuredPlate pressuredPlate) {
		this.player = pressuredPlate.getDungeon().getPlayer();
		this.pressuredPlate = pressuredPlate;
		this.dungeon = pressuredPlate.getDungeon();
	}

	@Override
	public void moveTowards() {
		
		int index = dungeon.getEntities().indexOf(pressuredPlate);
		pressuredPlate.isActive().set(false);

		Boulder boulder = (Boulder) dungeon.getEntities().get(index);
		System.out.println("boudler corrideanete is "+ boulder.getX()+boulder.getY());
		if (player.getX() != pressuredPlate.getX()) {
			if (player.getX() > pressuredPlate.getX()) {
				// left
				Entity next = findEntityAt(pressuredPlate.getX() - 1, pressuredPlate.getY());

				next.PerformBeMovedTowardsbyBoulder(boulder);
			} else {
				// right
				Entity next = findEntityAt(boulder.getX() + 1, boulder.getY());

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
		for (Entity e : dungeon.getEntities()) {
			if (e.getX() == x && e.getY() == y) {
				found = e;
				return found;
			}
		}
		EmptySpace emptySpace = new EmptySpace(dungeon, x, y, "emptySpace");
		dungeon.addEntity(emptySpace);
		return emptySpace;
	}
}
