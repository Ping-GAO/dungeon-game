package unsw.dungeon;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ping GAO
 */
public class Enemy extends Entity {
    private Timeline enemyTimeline;
    boolean[][] canPassThrough;
    private Player player;
    public Enemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.setMoveTowardsBehavior(new PlayerMoveTowardsNoWay());
        this.setPickUpBehavior(new PickUpNoWay());
        this.setBoulderMoveTowadsBeheavior(new BoulderMoveTowardsNoWay());
        this.name = "enemy";
        this.setEntityImage(new Image("images/deep_elf_master_archer.png"));
        canPassThrough = new boolean[dungeon.getHeight()][dungeon.getWidth()];
        this.player = dungeon.getPlayer();
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
        //System.out.println("player is " + dungeon.getPlayer().isOP());


        if (!dungeon.getPlayer().isOP()) {
            if (this.alive().getValue()) {
                if (pair.x == dungeon.getPlayer().getY()) {
                    if (pair.y == dungeon.getPlayer().getX()) {
                        dungeon.getPlayer().alive().setValue(false);
                        dungeon.getPlayer().setMessage("You died.");
                        player.setEnemyKilled(player.getEnemyKilled() + 1);
                        //System.out.println("died");
                    }
                }
            }
        } else {
            if (pair.x == dungeon.getPlayer().getY()) {
                if (pair.y == dungeon.getPlayer().getX()) {
                    this.alive().setValue(false);
                }
            }

        }
    }

    public void stopTimeLine() {
        enemyTimeline.stop();
    }
    public void startTimeLine() {

        enemyTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), (ActionEvent event1) -> {
            // how to break from inside

            initializeMatrix(canPassThrough);
            checkPassbility(canPassThrough);
            Pair pair = findEnemyNextMoveViaSP(canPassThrough, new Pair(player.getY(),
                    player.getX()), new Pair(this.getY(), this.getX()));

            if (pair != null) {
                updateEnemy(this, pair);
            }
        }));
        enemyTimeline.setCycleCount(Animation.INDEFINITE);
        enemyTimeline.play();
    }

    private Pair findEnemyNextMoveViaSP(boolean[][] canPassThrough, Pair start, Pair des) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            canPassThrough[pair.x][pair.y] = false;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (i * j != 0) {
                        continue;
                    }
                    if (checkIfOutOfB(pair.x + i, pair.y + j)) {
                        if (pair.x + i == des.x && pair.y + j == des.y) {
                            return pair;
                        } else if (canPassThrough[pair.x + i][pair.y + j]) {
                            queue.add(new Pair(pair.x + i, pair.y + j));
                            //System.out.println("x is"+(pair.x + i)+"y is "+(pair.y + j));
                        }

                    }
                }
            }
        }


        return null;
    }

    private boolean checkIfOutOfB(int x1, int y1) {
        if (x1 < 0 || x1 > dungeon.getHeight() - 1) {
            return false;
        }
        return y1 >= 0 && y1 <= dungeon.getWidth() - 1;
    }

    private void checkPassbility(boolean[][] canPassThrough) {
        for (Entity e : dungeon.getEntities()) {
            if (e != null) {
                // pass all can get through scenarios
                if (e.getName().equals("emptySpace")) {
                    continue;
                }
                if (e.getName().equals("door") && ((Door) e).isOpen().getValue()) {
                    continue;
                }
                canPassThrough[e.getY()][e.getX()] = false;
            }
        }
    }

    private void initializeMatrix(boolean[][] canPassThrough) {
        for (int i = 0; i < dungeon.getHeight(); i++) {
            for (int j = 0; j < dungeon.getWidth(); j++) {
                canPassThrough[i][j] = true;
            }
        }
    }

    private void updateEnemy(Enemy enemy, Pair pair) {
        enemy.checkIfPlayer(pair);
        if (!player.alive().getValue()) {
            enemyTimeline.stop();
        }
        if (pair.x != enemy.getY()) {
            if (pair.x > enemy.getY()) {
                enemy.moveDown();
            } else {
                enemy.moveUp();
            }
        } else {
            if (pair.y > enemy.getX()) {
                enemy.moveRight();
            } else {
                enemy.moveLeft();
            }
        }
    }

}
