package angels;

import champions.*;
import greatMagician.GreatMagician;

public class LevelUpAngel extends Angel {

    public LevelUpAngel(int x,int y)
    {
        super(x,y);
        isGood=true;
    }

    public void interactWith(Knight knight)
    {

           if(knight.getCurrentHp()!=0)
        {
            knight.setXp(250 + knight.getLevel() * 50);
            knight.levelUp();
            this.changeModifiers(knight, 0.1f);
            notifyInteractionUpdates(knight);
        }

    }
    public void interactWith(Pyromancer pyromancer)
    {

        if(pyromancer.getCurrentHp()!=0)
        {
            pyromancer.setXp(250 + pyromancer.getLevel() * 50);
            pyromancer.levelUp();

            this.changeModifiers(pyromancer, 0.2f);
            notifyInteractionUpdates(pyromancer);
        }

    }
    public void interactWith(Rogue rogue)
    {

        if(rogue.getCurrentHp()!=0)
        {
            rogue.setXp(250 + rogue.getLevel() * 50);
            rogue.levelUp();

            this.changeModifiers(rogue, 0.15f);
            notifyInteractionUpdates(rogue);
        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0)
        {
            wizard.setXp(250 + wizard.getLevel() * 50);
            wizard.levelUp();

            this.changeModifiers(wizard, 0.25f);
            notifyInteractionUpdates(wizard);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel LevelUpAngel was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }

    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification;

        notification="LevelUpAngel helped ";

        if(champion instanceof Pyromancer) {
            notification = notification + "Pyromancer " + champion.getId();
            greatMagician.getNotifications().add(notification);
            greatMagician.getNotifications().add("Pyromancer "+champion.getId()+" reached level "+champion.getLevel());
        }
        if(champion instanceof Knight) {
            notification = notification + "Knight " + champion.getId();
            greatMagician.getNotifications().add(notification);
            greatMagician.getNotifications().add("Knight "+champion.getId()+" reached level "+champion.getLevel());
        }
        if(champion instanceof Rogue) {
            notification = notification + "Rogue " + champion.getId();
            greatMagician.getNotifications().add(notification);
            greatMagician.getNotifications().add("Rogue "+champion.getId()+" reached level "+champion.getLevel());
        }
        if(champion instanceof Wizard) {
            notification = notification + "Wizard " + champion.getId();
            greatMagician.getNotifications().add(notification);
            greatMagician.getNotifications().add("Wizard "+champion.getId()+" reached level "+champion.getLevel());
        }





    }

}
