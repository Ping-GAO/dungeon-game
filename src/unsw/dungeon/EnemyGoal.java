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
        return player.getEnemyKilled() == 3;
    }
}
