package angels;

import champions.*;
import greatMagician.GreatMagician;

public class GoodBoy extends Angel {
    public GoodBoy(int x,int y)
    {
        super(x,y);
        isGood=true;

    }

    public void interactWith(Knight knight)
    {
        if(knight.getCurrentHp()!=0) {
            this.changeModifiers(knight, 0.4f);
            knight.setCurrentHp(knight.getCurrentHp() + 20);
            if(knight.getCurrentHp()>knight.getMaxHp())
                knight.setCurrentHp(knight.getMaxHp());

            notifyInteractionUpdates(knight);
        }
    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0) {
            this.changeModifiers(pyromancer, 0.5f);
            pyromancer.setCurrentHp(pyromancer.getCurrentHp() + 30);
            if(pyromancer.getCurrentHp()>pyromancer.getMaxHp())
                pyromancer.setCurrentHp(pyromancer.getMaxHp());

            notifyInteractionUpdates(pyromancer);

        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0) {
            this.changeModifiers(rogue, 0.4f);
            rogue.setCurrentHp(rogue.getCurrentHp() + 40);
            if(rogue.getCurrentHp()>rogue.getMaxHp())
                rogue.setCurrentHp(rogue.getMaxHp());

            notifyInteractionUpdates(rogue);

        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0) {
            this.changeModifiers(wizard, 0.3f);
            wizard.setCurrentHp(wizard.getCurrentHp() + 50);
            if(wizard.getCurrentHp()>wizard.getMaxHp())
                wizard.setCurrentHp(wizard.getMaxHp());

            notifyInteractionUpdates(wizard);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel GoodBoy was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }

    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification;

        notification="GoodBoy helped ";

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
