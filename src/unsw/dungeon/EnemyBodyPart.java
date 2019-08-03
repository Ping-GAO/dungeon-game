package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class EnemyBodyPart extends Entity {

    public EnemyBodyPart(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.name = "monsterPart";
    }
}
