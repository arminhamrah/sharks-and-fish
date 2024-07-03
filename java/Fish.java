import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

public class Fish extends Critter
{
    // constants (note that these are "protected", so they are
    // available in subclasses of Fish)
    protected final static int BREEDAGE = 7;
    protected final static int OLDAGE = 17;
    protected final static double PROBOFDYING = 0.15;

    // private instance variables
    private int age;

    public Fish()
    {
        age=0;
    }

    public int getAge()
    {
        return age;
    }

    public void act()
    {
        age++;
        if (age>=OLDAGE)
        {
            if (PROBOFDYING>Math.random())
            {
                removeSelfFromGrid();
            }
        }
        else
        {
            super.act();
        }
    }
    
    public void processActors(ArrayList<Actor> actors)
    {
        ;
    }
}