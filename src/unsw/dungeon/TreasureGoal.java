package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class TreasureGoal implements Goal {


    private Player player;

    public TreasureGoal(Player player) {
        this.player = player;
    }


    @Override
    public boolean evaluate() {
        return player.getBagPack().gettreasureNum() == 7;
    }
}
