package unsw.dungeon;

/**
 * @author Ping GAO
 */
public class SubGoal implements Goal {
    private Goal Left;
    private Goal Right;
    private String Operator;

    public SubGoal(Goal Left, Goal Right, String Operator) {
        this.Left = Left;
        this.Right = Right;
        this.Operator = Operator;
    }

    @Override
    public boolean evaluate() {
        if (Operator.equals("AND")) {
            return Left.evaluate() && Right.evaluate();
        } else {
            return Left.evaluate() || Right.evaluate();
        }
    }
}
