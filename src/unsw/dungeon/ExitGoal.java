package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class ExitGoal implements Goal {

    private Player player;

    public ExitGoal(Player player) {
        this.player = player;
    }


    @Override
    public boolean evaluate() {
        return player.isAtExit();
    }


}
