package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @author Ping GAO
 */
public abstract class Entity {

    protected IntegerProperty x, y;
    private BooleanProperty alive;
    private PlayerMoveTowardsBehavior moveTowardsBehavior;
    private BoulderMoveTowardsBehaviour boulderMoveTowadsBeheavior;
    private GetBombedBehavior getBombedBehavior;
    private PickUpBehavior pickUpBehavior;
    protected Dungeon dungeon;
    protected String name;
    protected ImageView imageView;
    private Image EntityImage;
    private int pushpriority;

    /**
     * @param dungeon dungeon
     * @param x       x
     * @param y       y
     *                everything in a maze is an entity
     *                model how one entity interact with another entity use strategy pattern
     */
    public Entity(Dungeon dungeon, int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
        this.alive = new SimpleBooleanProperty(true);
        this.pushpriority = 0;

    }

    public int getPushpriority() {
        return pushpriority;
    }

    public void setPushpriority(int pushpriority) {
        this.pushpriority = pushpriority;
    }

    public String getName() {
        return name;
    }

    BooleanProperty alive() {
        return alive;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    void PerformBeMovedTowardsbyPlayer() {
        moveTowardsBehavior.moveTowards();
    }

    void PerformGetBombed() {
        getBombedBehavior.getBombed();
    }

    void PerformBeMovedTowardsbyBoulder(Boulder b) {
        boulderMoveTowadsBeheavior.setBoulder(b);
        boulderMoveTowadsBeheavior.moveTowards();
    }

    void PerformBePickedUp() {
        pickUpBehavior.pickUp();
    }

    public void setMoveTowardsBehavior(PlayerMoveTowardsBehavior moveTowardsBehavior) {
        this.moveTowardsBehavior = moveTowardsBehavior;
    }

    public void setPickUpBehavior(PickUpBehavior pickUpBehavior) {
        this.pickUpBehavior = pickUpBehavior;
    }

    public void setBoulderMoveTowadsBeheavior(BoulderMoveTowardsBehaviour boulderMoveTowadsBeheavior) {
        this.boulderMoveTowadsBeheavior = boulderMoveTowadsBeheavior;

    }

    void setGetBombedBehavior(GetBombedBehavior getBombedBehavior) {
        this.getBombedBehavior = getBombedBehavior;
    }

    public void setEntityImage(Image entityImage) {
        EntityImage = entityImage;
    }


    /**
     * @return the image view of this entity
     */
    public ImageView MakeImageViewFromEntity() {
        this.imageView = new ImageView(EntityImage);
        imageView.setX(getX());
        imageView.setY(getY());
        return imageView;
    }


}
