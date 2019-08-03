package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class PlayerMoveTowardsGnome implements PlayerMoveTowardsBehavior {

    private Gnome gnome;
    private Player player;


    public PlayerMoveTowardsGnome(Gnome gnome) {
        this.gnome = gnome;
        this.player = gnome.getDungeon().getPlayer();


    }

    @Override
    public void moveTowards() {
        int cnt = 0;
        for (Entity e : player.getBagPack().getBagPack()) {
            if (e.getName().equals("monsterPart")) {
                cnt++;
            }
        }
        while (cnt != 0) {
            for (Entity e : player.getBagPack().getBagPack()) {
                if (e.getName().equals("monsterPart")) {
                    player.getBagPack().getBagPack().remove(e);
                    break;
                }
            }
            player.getBagPack().getBagPack().add(new Treasure(gnome.getDungeon(), gnome.getX(), gnome.getY()));
            cnt--;
        }
        player.setMessage("Trade monster part to treasure.");
    }

}
