package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class BoulderMoveTowardsPassThrough implements BoulderMoveTowardsBehaviour {
	Entity entity;
	Boulder boulder;

	public BoulderMoveTowardsPassThrough(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void setBoulder(Boulder boulder) {
		this.boulder = boulder;
	}

	@Override
	public void moveTowards() {
		boulder.x().set(entity.getX());
		boulder.y().set(entity.getY());
	}

}
