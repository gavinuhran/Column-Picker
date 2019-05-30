import kareltherobot.*;
/**
 * Counts the number of beepers in a column and puts the same
 * number of beepers at the base of the column
 * 
 * @author 
 * @version 
 */
public class ColumnPicker extends Robot
{
    public ColumnPicker(int street, int avenue, Direction direction, int beepers)
    {
        super(street, avenue, direction, beepers);
    }
    
    public void countColumns()
    {
        while (!isNextColumn())
        {
            moveToNextColumn();
            int count = countColumn(0);
            moveToBottomOfColumn();
            putBeepers(count);
        }
        turnOff();
    }
    public void turnAround()
    {
        turnLeft();
        turnLeft();
    }
    public void turnRight()
    {
        turnAround();
        turnLeft();
    }
    public boolean isNextColumn()
    {
        turnRight();
        move();
        boolean flag = nextToABeeper();
        turnAround();
        move();
        turnRight();
        return flag;
    }
    public void moveToNextColumn()
    {
        turnRight();
        move();
        turnLeft();
    }
    public void move(int numSteps)
    {
        if (numSteps == 0)
            return;
        move();
        move(numSteps - 1);
    }
    public int countPile()
    {
        if (!nextToABeeper())
            return 0;
        else
        {   
            pickBeeper();
            int count = 1 + countPile();
            putBeeper();
            return count;
        }
    }
    public int countColumn(int height)
    {
        if (height == 8)
            return 0;
        move();
        return countPile() + countColumn(height + 1);
    }
    public void moveToBottomOfColumn()
    {
        turnAround();
        move(8);
        turnAround();
    }
    public void putBeepers(int numBeeper)
    {
        if (numBeeper == 0)
            return;
        putBeeper();
        putBeepers(numBeeper - 1);
    }
}