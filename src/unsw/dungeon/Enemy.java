package unsw.dungeon;

import javafx.scene.image.Image;

public class Enemy extends Entity {

    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "enemy";
        this.setEntityImage(new Image("images/deep_elf_master_archer.png"));
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public void checkIfPlayer(Pair pair) {
        if (pair.x == dungeon.getPlayer().getY()) {
            if (pair.y == dungeon.getPlayer().getX()) {
                dungeon.getPlayer().alive().setValue(false);
                dungeon.getPlayer().setMessage("You died.");
                System.out.println("died");
            }
        }
    }

}
