package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class GoalFactory {
    private Player player;

    public GoalFactory(Player player) {
        this.player = player;
    }

    public Goal getGoal(String goalType) {
        if (goalType == null) {
            return null;
        }
        if (goalType.equalsIgnoreCase("exit")) {
            return new ExitGoal(player);

        } else if (goalType.equalsIgnoreCase("enemies")) {
            return new EnemyGoal(player);

        } else if (goalType.equalsIgnoreCase("treasure")) {
            return new TreasureGoal(player);
        } else if (goalType.equalsIgnoreCase("boulders")) {
            return new BoulderGoal();
        }

        return null;
    }
}
