package angels;


import champions.*;
import greatMagician.GreatMagician;

public class SmallAngel extends Angel {

    public SmallAngel(int x,int y)
    {
        super(x,y);
        isGood=true;
    }

    public void interactWith(Knight knight)
    {
        if(knight.getCurrentHp()!=0)
        {
            this.changeModifiers(knight, 0.1f);
            knight.setCurrentHp(knight.getCurrentHp() + 10);

            notifyInteractionUpdates(knight);
        }
    }

    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0)
        {

            this.changeModifiers(pyromancer, 0.15f);
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() + 15);

            notifyInteractionUpdates(pyromancer);
        }
    }

    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0)
        {
            this.changeModifiers(rogue, 0.05f);
            rogue.setCurrentHp(rogue.getCurrentHp() + 20);

            notifyInteractionUpdates(rogue);
        }
    }

    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0)
        {
            this.changeModifiers(wizard, 0.1f);
            wizard.setCurrentHp(wizard.getCurrentHp() + 25);

            notifyInteractionUpdates(wizard);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel SmallAngel was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }

    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification;

        notification="SmallAngel helped ";

        if(champion instanceof Pyromancer)
            notification=notification+"Pyromancer "+champion.getId();
        if(champion instanceof Knight)
            notification=notification+"Knight "+champion.getId();
        if(champion instanceof Rogue)
            notification=notification+"Rogue "+champion.getId();
        if(champion instanceof Wizard)
            notification=notification+"Wizard "+champion.getId();

        greatMagician.getNotifications().add(notification);
    }


}
