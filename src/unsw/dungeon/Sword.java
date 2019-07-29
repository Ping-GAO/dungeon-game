package unsw.dungeon;

import javafx.scene.image.Image;

public class Sword extends Entity {
    private int durability;

    public void decreaseDurability() {
        this.durability--;
        if (durability == 0) {
            this.alive().setValue(false);
            this.dungeon.getPlayer().getBagPack().getBagPack().remove(this);
        }
    }

    public int getDurability() {
        return durability;
    }

    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsPassThrough(this));
        this.setPickUpBehavior(new PickUpIntoBag(this));
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "sword";
        this.setEntityImage(new Image("images/greatsword_1_new.png"));
        this.durability = 5;
    }

}
