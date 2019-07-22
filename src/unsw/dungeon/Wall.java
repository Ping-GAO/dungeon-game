package unsw.dungeon;

public class Wall extends Entity {

    public Wall(Dungeon dungeon, int x, int y,String name) {
        super(dungeon, x, y,name);
        this.setMoveTowardsBehavior(new playerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new boulderMoveTowardsNoWay());
    }

}
