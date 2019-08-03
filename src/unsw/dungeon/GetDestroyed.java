package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class GetDestroyed implements GetBombedBehavior {
	private Entity entity;

	public GetDestroyed(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void getBombed() {
		entity.alive().set(false);
	}

}
