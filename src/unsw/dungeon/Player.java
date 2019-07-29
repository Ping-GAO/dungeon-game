package unsw.dungeon;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {

    private BagPack bagPack;
    private String message;
    private boolean isOP;

    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void clearMessage() {
        message = "";
    }


    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.bagPack = new BagPack();
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.name = "player";
        this.setEntityImage(new Image("images/human_new.png"));
        this.message = "";
        this.isOP = false;
    }

    public BagPack getBagPack() {
        return bagPack;
    }

    public void setOP(boolean OP) {
        isOP = OP;
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
        checkIfOP();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            //Entity next = findEntityAt(getX(), getY() + 1);
            List<Entity> list = findAllEntityAt(getX(), getY() + 1);
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
        }
        checkIfOP();
    }

    public void moveLeft() {
        if (this.getX() > 0) {
            //Entity next = findEntityAt(getX() - 1, getY());
            List<Entity> list = findAllEntityAt(getX() - 1, getY());
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
        }
        checkIfOP();
    }

    public boolean isOP() {
        return isOP;
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
        checkIfOP();
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


    public void checkIfOP() {
        for (Entity e : getBagPack().getBagPack()) {
            if (e.getName().equals("invincibility")) {
                isOP = true;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // trigger after 5 sec pick up the potion
                        // System.out.println("get trigger");
                        dungeon.getPlayer().getBagPack().getBagPack().remove(e);
                        dungeon.getPlayer().setOP(false);
                    }
                }, 5 * 1000);
            }
        }
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
