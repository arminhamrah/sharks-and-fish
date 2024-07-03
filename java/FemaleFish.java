import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.grid.*;
import info.gridworld.grid.Location;

public class FemaleFish extends Fish
{
    // constants
    private final static int GESTATIONPERIOD = 5;

    // private instance variables
    private int turnsPregnant;

    public FemaleFish()
    {
        super();
        setColor(Color.PINK);
        turnsPregnant = 0;
    }
    
    public void act()
    {
        if(turnsPregnant == 0)
            setColor(Color.pink);
        else
            setColor(Color.red);
        super.act();
        if(turnsPregnant >= GESTATIONPERIOD)
        {
            giveBirth();
        }
    }

    public void giveBirth()
    {
        Grid<Actor> gr = getGrid();
        ArrayList<Location> locs = gr.getEmptyAdjacentLocations(getLocation());
        if(locs.size() > 0)
        {
            int index = (int)(Math.random()*locs.size());
            Location loc = locs.get(index);
            Fish baby;
            if(Math.random() < 0.5)
                baby = new FemaleFish();
            else
                baby = new MaleFish();
            baby.putSelfInGrid(gr, loc);
            turnsPregnant = 0;
        }
    }
    
    @Override //loop thru actors andcehck if each 
    public void processActors(ArrayList<Actor> actors)
    {

        if (turnsPregnant>0)
            setColor(Color.RED);

        if (getAge()>=BREEDAGE && turnsPregnant ==0)
            for (Actor a : actors)
            {
                if (a instanceof MaleFish && ((MaleFish)a).getAge()>=BREEDAGE)
                {
                    turnsPregnant++;
                    return;
                }
            }
    }
}