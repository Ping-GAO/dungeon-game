package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsExit implements PlayerMoveTowardsBehavior {
	private Player player;

	public PlayerMoveTowardsExit(Entity entity) {
		this.player = entity.getDungeon().getPlayer();
	}

    /**
     * almost beat the game
     */
    @Override
    public void moveTowards() {
        this.player.setAtExit(true);
        if (player.getSubGoal().evaluate()) {
            player.setMessage("You beat the game");
        } else {
            // the output message is set by the specific goal class
            this.player.setAtExit(false);
        }

    }


}
