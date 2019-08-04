package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsEnemy implements PlayerMoveTowardsBehavior {
    private Enemy enemy;
    private Player player;

    public PlayerMoveTowardsEnemy(Enemy enemy) {
        this.enemy = enemy;
        this.player = enemy.getDungeon().getPlayer();
    }

    @Override
    public void moveTowards() {
        enemy.alive().setValue(false);
        player.setEnemyKilled(player.getEnemyKilled() + 1);
        player.setMessage("You killed an enemy.");
    }
}
