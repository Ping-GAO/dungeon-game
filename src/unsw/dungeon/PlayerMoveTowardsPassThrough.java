package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsPassThrough implements PlayerMoveTowardsBehavior {
	private Player player;
	private Entity entity;

	public PlayerMoveTowardsPassThrough(Entity entity) {

		this.entity = entity;
		this.player = entity.getDungeon().getPlayer();
	}

	@Override
	public void moveTowards() {
		player.x().set(entity.getX());
		player.y().set(entity.getY());

	}


}
