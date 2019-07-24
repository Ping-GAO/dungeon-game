package unsw.dungeon;

public class GetDestroyed implements GetBombedBehavior {
	private Entity entity;

	public GetDestroyed(Entity enity) {
		this.entity = enity;
	}

	@Override
	public void getBombed() {
		entity.alive().set(false);
	}

}
