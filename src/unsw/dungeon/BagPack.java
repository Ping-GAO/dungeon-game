package unsw.dungeon;


import java.util.ArrayList;

/**
 * @author Ping GAO
 */
public class BagPack {

    private ArrayList<Entity> bagPack;

    /**
     * constructor
     */
    public BagPack() {
        bagPack = new ArrayList<>();


    }

    public ArrayList<Entity> getBagPack() {
        return bagPack;
    }


    public void addToBagPack(Entity entity) {
        bagPack.add(entity);
    }

    /**
     * @return the description of bagpack
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();


        for (int i = 0; i < bagPack.size(); i++) {
            if (bagPack.get(i).getName().equals("treasure")) {
                continue;
            }
            result.append(bagPack.get(i).getName());

            if (bagPack.get(i).getName().equals("sword")) {
                result.append(" (").append(((Sword) bagPack.get(i)).getDurability()).append(")");
            }
            if (i != bagPack.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * @return the sword player has in bagpack
     */
    public Sword getSword() {
        Sword sword = null;
        for (Entity e : bagPack) {
            if (e.getName().equals("sword")) {
                sword = (Sword) e;
            }
        }
        return sword;
    }

    public int gettreasureNum() {
        int cnt = 0;
        for (Entity entity : bagPack) {
            if (entity.getName().equals("treasure")) {
                cnt++;
            }

        }
        return cnt;
    }
}
