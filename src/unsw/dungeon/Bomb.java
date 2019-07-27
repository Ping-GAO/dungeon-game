package unsw.dungeon;

public class Bomb extends Entity {


    public Bomb(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "bomb";
    }


    public void Lit() {
        // System.out.println("light it up");

        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
    }

    public void After() {
        this.alive().set(false);

    }
}
