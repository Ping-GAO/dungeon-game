package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsExit implements PlayerMoveTowardsBehavior {
	private Player player;

	public PlayerMoveTowardsExit(Entity entity) {
		this.player = entity.getDungeon().getPlayer();
	}

	@Override
	public void moveTowards() {
		// System.out.println("You beat the game");
		// player.alive().set(false);
		player.setMessage("You beat the game");

	}


}
