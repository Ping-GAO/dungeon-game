package unsw.dungeon;

public class Bomb extends Entity {
	private boolean isLit;

	public Bomb(Dungeon dungeon, int x, int y, String name) {
		super(dungeon, x, y, name);
		this.setMoveTowardsBehavior(new playerMoveTowardsPassThrough(this));
		this.setPickUpBehavior(new PickUpIntoBag(this));
		this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
		this.isLit = false;
	}

	public boolean isLit() {
		return isLit;
	}

	public void setLit(boolean isLit) {
		this.isLit = isLit;
	}

	public void Lit() {
		// System.out.println("light it up");

		this.setMoveTowardsBehavior(new playerMoveTowardsNoWay());
		this.setPickUpBehavior(new PickUpNoWay());
	}

	public void After() {
		this.alive().set(false);
		Entity toRemove = null;
		for (Entity e : dungeon.getEntities()) {
			if (e != null) {
				if (e.getName().equals("bomb") && e.getX() == this.getX() && this.getY() == e.getY()) {
					toRemove = e;
				}
			}
		}
		dungeon.getEntities().remove(toRemove);
	}
}
