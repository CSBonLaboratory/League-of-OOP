package angels;

import champions.*;
import greatMagician.GreatMagician;

public class TheDoomer extends Angel {

    public TheDoomer(int x,int y)
    {
        super(x,y);
        isGood=false;
    }

    public void interactWith(Knight knight)
    {
        if(knight.getCurrentHp()!=0) {
            knight.setCurrentHp(0);
            notifyInteractionUpdates(knight);
        }
    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0) {
            pyromancer.setCurrentHp(0);
            notifyInteractionUpdates(pyromancer);
        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0) {
            rogue.setCurrentHp(0);
            notifyInteractionUpdates(rogue);
        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0) {
            wizard.setCurrentHp(0);
            notifyInteractionUpdates(wizard);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel TheDoomer was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }
    public void updateInteraction(Champion champion)
    {
        GreatMagician magician=GreatMagician.getInstance();

        String notification;

        notification="TheDoomer hit ";

        if(champion instanceof Pyromancer){
            notification=notification+"Pyromancer "+champion.getId();
            magician.getNotifications().add(notification);
            magician.getNotifications().add("Player Pyromancer "+champion.getId()+" was killed by an angel");
        }
        if(champion instanceof Knight)
        {
            notification=notification+"Knight "+champion.getId();
            magician.getNotifications().add(notification);
            magician.getNotifications().add("Player Knight " +champion.getId()+" was killed by an angel");
        }
        if(champion instanceof Rogue)
        {
            notification=notification+"Rogue "+champion.getId();
            magician.getNotifications().add(notification);
            magician.getNotifications().add("Player Rogue " +champion.getId()+" was killed by an angel");
        }
        if(champion instanceof Wizard)
        {
            notification=notification+"Wizard "+champion.getId();
            magician.getNotifications().add(notification);
            magician.getNotifications().add("Player Wizard " +champion.getId()+" was killed by an angel");
        }

    }
}
