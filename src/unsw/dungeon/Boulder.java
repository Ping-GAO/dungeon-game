package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new playerMoveTowardsBoulder(this));
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.name = "boulder";
    }

}
