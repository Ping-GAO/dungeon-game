package unsw.dungeon;

public class playerMoveTowardsExit implements playerMoveTowardsBehavior {
	private Player player;

	public playerMoveTowardsExit(Entity entity) {
		this.player = entity.getDungeon().getPlayer();
	}

	@Override
	public void moveTowards() {
		System.out.println("You beat the game");
		player.alive().set(false);

	}

}
