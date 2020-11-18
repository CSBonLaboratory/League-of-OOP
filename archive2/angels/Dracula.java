package angels;

import champions.*;
import greatMagician.GreatMagician;

public class Dracula extends Angel {

    public Dracula(int x,int y)
    {
        super(x,y);
        isGood=false;
    }

    public void interactWith(Knight knight)
    {
        if(knight.getCurrentHp()!=0)
        {
            this.changeModifiers(knight, -0.2f);
            knight.setCurrentHp(knight.getCurrentHp() - 60);
            notifyInteractionUpdates(knight);

            if(knight.getCurrentHp()<0)
                knight.setCurrentHp(0);
        }
    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0)
        {
            this.changeModifiers(pyromancer, -0.3f);
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() - 40);
            notifyInteractionUpdates(pyromancer);

            if(pyromancer.getCurrentHp()<0)
                pyromancer.setCurrentHp(0);
        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0)
        {
            this.changeModifiers(rogue, -0.1f);
            rogue.setCurrentHp(rogue.getCurrentHp() - 35);
            notifyInteractionUpdates(rogue);

            if(rogue.getCurrentHp()<0)
                rogue.setCurrentHp(0);
        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0)
        {
            this.changeModifiers(wizard, -0.4f);
            wizard.setCurrentHp(wizard.getCurrentHp() - 20);
            notifyInteractionUpdates(wizard);

            if(wizard.getCurrentHp()<0)
                wizard.setCurrentHp(0);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel Dracula was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }
    public void updateInteraction(Champion champion)
    {
        GreatMagician magician=GreatMagician.getInstance();

        String notification;

        notification="Dracula hit ";

        if(champion instanceof Pyromancer) {
            notification = notification + "Pyromancer " + champion.getId();
            magician.getNotifications().add(notification);
            if(champion.getCurrentHp()<=0)
                magician.getNotifications().add("Player Pyromancer "+champion.getId()+" was killed by an angel");

        }
        if(champion instanceof Knight) {
            notification = notification + "Knight " + champion.getId();
            magician.getNotifications().add(notification);
            if(champion.getCurrentHp()<=0)
                magician.getNotifications().add("Player Knight "+champion.getId()+" was killed by an angel");
        }
        if(champion instanceof Rogue) {
            notification = notification + "Rogue " + champion.getId();
            magician.getNotifications().add(notification);
            if(champion.getCurrentHp()<=0)
                magician.getNotifications().add("Player Rogue "+champion.getId()+" was killed by an angel");
        }
        if(champion instanceof Wizard) {
            notification = notification + "Wizard " + champion.getId();
            magician.getNotifications().add(notification);
            if(champion.getCurrentHp()<=0)
                magician.getNotifications().add("Player Wizard "+champion.getId()+" was killed by an angel");
        }

    }

}
