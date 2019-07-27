package unsw.dungeon;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private BagPack bagPack;


    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.bagPack = new BagPack();
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.name = "player";
        this.setEntityImage(new Image("images/human_new.png"));
    }

    public BagPack getBagPack() {
        return bagPack;
    }

    public void moveUp() {
        if (getY() > 0) {
            //Entity next = findEntityAt(getX(), getY() - 1);
            List<Entity> list = findAllEntityAt(getX(), getY() - 1);
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
            //next.PerformBeMovedTowardsbyPlayer();
        }

    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            //Entity next = findEntityAt(getX(), getY() + 1);
            List<Entity> list = findAllEntityAt(getX(), getY() + 1);
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
            //next.PerformBeMovedTowardsbyPlayer();
        }

    }

    public void moveLeft() {
        if (this.getX() > 0) {
            //Entity next = findEntityAt(getX() - 1, getY());
            List<Entity> list = findAllEntityAt(getX() - 1, getY());
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
            // System.out.println("letf is " + next.getName());
            //next.PerformBeMovedTowardsbyPlayer();

        }

    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            //Entity next = findEntityAt(getX() + 1, getY());
            List<Entity> list = findAllEntityAt(getX() + 1, getY());
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
            // next.PerformBeMovedTowardsbyPlayer();
        }

    }

    public boolean checkIfHaveBomb() {
        boolean found = false;
        for (Entity e : this.bagPack.getBagPack()) {
            if (e.getName().equals("bomb")) {
                found = true;
                break;
            }
        }
        return found;
    }



    public List<Entity> findAllEntityAt(int x, int y) {

        List<Entity> list = new ArrayList<>();
        for (Entity e : dungeon.getEntities()) {
            if (e.getX() == x && e.getY() == y) {
                list.add(e);
            }
        }

        if (list.isEmpty()) {
            EmptySpace emptySpace = new EmptySpace(dungeon, x, y);
            dungeon.addEntity(emptySpace);
            list.add(emptySpace);
        }
        // when entities stack up sort them with priority
        list.sort((lhs, rhs) -> Integer.compare(rhs.getPushpriority(), lhs.getPushpriority()));

        return list;
    }

}
