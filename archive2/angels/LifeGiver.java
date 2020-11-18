package angels;


import champions.*;
import greatMagician.GreatMagician;

public class LifeGiver extends Angel {

    public LifeGiver(int x,int y)
    {
        super(x,y);
        isGood=true;
    }

    public void interactWith(Knight knight)
    {
        if(knight.getCurrentHp()!=0) {
            knight.setCurrentHp(knight.getCurrentHp() + 100);

            if(knight.getCurrentHp()>knight.getMaxHp())
                knight.setCurrentHp(knight.getMaxHp());
            notifyInteractionUpdates(knight);
        }
    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0) {
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() + 80);

            if(pyromancer.getCurrentHp()>pyromancer.getMaxHp())
                pyromancer.setCurrentHp(pyromancer.getMaxHp());

            notifyInteractionUpdates(pyromancer);
        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0) {
            rogue.setCurrentHp(rogue.getCurrentHp() + 90);

            if(rogue.getCurrentHp()>rogue.getMaxHp())
                rogue.setCurrentHp(rogue.getMaxHp());

            notifyInteractionUpdates(rogue);

        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0) {
            wizard.setCurrentHp(wizard.getCurrentHp() + 120);

            if(wizard.getCurrentHp()>=wizard.getMaxHp())
                wizard.setCurrentHp(wizard.getMaxHp());

            notifyInteractionUpdates(wizard);


        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel LifeGiver was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }

    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification;

        notification="LifeGiver helped ";

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
