package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class EnemyGoal implements Goal {

    private Player player;

    public EnemyGoal(Player player) {
        this.player = player;
    }


    @Override
    public boolean evaluate() {

        if (player.getEnemyKilled() != 3) {
            player.setMessage("EnemyGoal is not meet.");
            return false;
        } else {
            return true;
        }

    }
}
