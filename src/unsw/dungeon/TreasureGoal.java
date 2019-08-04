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

        if (player.getBagPack().gettreasureNum() != 7) {
            player.setMessage("TreasureGoal is not meet.");
            return false;
        } else {
            return true;
        }
    }
}
