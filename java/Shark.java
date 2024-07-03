import info.gridworld.actor.Critter;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import java.awt.Color;
import info.gridworld.grid.*;
// for 'a shark only eats fish', override get actors

public class Shark extends Critter
{
    // constants
    private static final int MUSTEAT = 8;

    // private instance variables
    private int turnsSinceEaten;

    public Shark()
    {
        super();
        setColor(Color.black);
    }

    public void makeMove(Location loc)
    {
        if(getLocation().equals(loc))
        {
            if(Math.random() < 0.5)
                setDirection(getDirection() + Location.HALF_LEFT);
            else
                setDirection(getDirection() + Location.HALF_RIGHT);
        }
        else
        {
            Location currentLoc = getLocation();
            int direction = currentLoc.getDirectionToward(loc);
            setDirection(direction);
            super.makeMove(loc);
        }
    }

    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        Location current = getLocation();
        Grid<Actor> gr = getGrid();
        int dir = getDirection();
        Location left = current.getAdjacentLocation(dir+Location.LEFT);
        Location right = current.getAdjacentLocation(dir + Location.RIGHT);
        if(gr.isValid(left) && gr.get(left) == null)
            result.add(left);
        if(gr.isValid(right) && gr.get(right) == null)
            result.add(right);
        return result;
    }

    public ArrayList<Actor> getActors() // make sure only eat fish
    {
        ArrayList<Actor> actors = super.getActors();
        ArrayList<Actor> justFish = new ArrayList<Actor>();

        for (Actor a : actors)
        {
            if (a instanceof Fish)
            {
                justFish.add(a);
            }
        }
        return justFish;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        if(actors.size() > 0)
        {
            int index = (int)(Math.random()*actors.size());
            Actor actor = actors.get(index);
            actor.removeSelfFromGrid();
            turnsSinceEaten = 0;
        }
        else
            turnsSinceEaten++;
    }

    public void act()
    {
        if(turnsSinceEaten == MUSTEAT)
            removeSelfFromGrid();
        else
            super.act();
    }

}

