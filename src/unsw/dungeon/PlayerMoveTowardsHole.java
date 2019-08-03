package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsHole implements PlayerMoveTowardsBehavior {
    private Dungeon dungeon;
    private Hole hole;
    private Player player;

    public PlayerMoveTowardsHole(Hole hole) {
        this.dungeon = hole.getDungeon();
        this.hole = hole;
        this.player = dungeon.getPlayer();
    }


    @Override
    public void moveTowards() {
        // update the player coordinate into the other hole
        Hole theOtherOne = null;
        for (Entity e : dungeon.getEntities()) {
            if (e != null) {
                if (e.getName().equals("hole")) {
                    if (((Hole) e).getId() + hole.getId() == 0) theOtherOne = (Hole) e;
                }
            }
        }
        assert theOtherOne != null;
        player.x().set(theOtherOne.getX());
        player.y().set(theOtherOne.getY());

    }


}
