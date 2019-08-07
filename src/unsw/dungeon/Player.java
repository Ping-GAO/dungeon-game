package unsw.dungeon;

import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ping GAO
 */
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

    private int enemyKilled;

    private boolean atExit;

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }


    public boolean isAtExit() {
        return atExit;
    }

    public void setAtExit(boolean atExit) {
        this.atExit = atExit;
    }

    private Goal subGoal;

    public Goal getSubGoal() {
        return subGoal;
    }

    public Player(Dungeon dungeon, int x, int y) throws FileNotFoundException {
        super(dungeon, x, y);
        this.bagPack = new BagPack();
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.name = "player";
        this.setEntityImage(new Image("images/human_new.png"));
        this.message = "";
        this.isOP = false;
        this.setGetBombedBehavior(new GetDestroyed(this));
        this.enemyKilled = 0;
        this.atExit = false;


        JSONObject json = new JSONObject(new JSONTokener(new FileReader("dungeons/advanced.json")));
        JSONObject GoalCondition = json.getJSONObject("goal-condition");
        subGoal = generateGoalFromJSON(GoalCondition);
    }


    private Goal generateGoalFromJSON(JSONObject jsonObject) {
        GoalFactory goalFactory = new GoalFactory(this);
        Goal GoalResult;
        String Operator = jsonObject.getString("goal");
        if (!Operator.equals("AND")) {
            if (!Operator.equals("OR")) {
                GoalResult = goalFactory.getGoal(Operator);
                return GoalResult;
            }
        }

        JSONArray jsonEntities = jsonObject.getJSONArray("subgoals");

        Goal SubRight;
        Goal SubLeft;

        SubRight = generateGoalFromJSON(jsonEntities.getJSONObject(0));
        SubLeft = generateGoalFromJSON(jsonEntities.getJSONObject(1));
        GoalResult = new SubGoal(SubLeft, SubRight, Operator);
        return GoalResult;
    }


    public BagPack getBagPack() {
        return bagPack;
    }

    public void setOP(boolean OP) {
        isOP = OP;
    }

    public void moveUp() {
        if (getY() > 0) {
            List<Entity> list = findAllEntityAt(getX(), getY() - 1);
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
        }
        checkIfOP();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
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
            List<Entity> list = findAllEntityAt(getX() + 1, getY());
            for (Entity e : list) {
                e.PerformBePickedUp();
                e.PerformBeMovedTowardsbyPlayer();
            }
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

                setMessage("Get 5 secs of invincibility.");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        dungeon.getPlayer().getBagPack().getBagPack().remove(e);
                        dungeon.getPlayer().setOP(false);
                        setMessage("Invincibility effect end.");
                    }
                }, 5 * 1000);

            }

        }
    }

    public List<Entity> findAllEntityAt(int x, int y) {
        List<Entity> list = new ArrayList<>();
        for (Entity e : dungeon.getEntities()) {
            if (e != null && e.getX() == x && e.getY() == y) {
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
