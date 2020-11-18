package angels;


import champions.*;
import greatMagician.GreatMagician;

public class XPAngel extends Angel {

    public XPAngel(int x, int y)
    {
        super(x,y);
        isGood=true;
    }

    public void interactWith(Knight knight)
    {

        if(knight.getCurrentHp()!=0)
        {
            int oldLevel=knight.getLevel();
            knight.setXp(knight.getXp() + 45);
            knight.levelUp();
            notifyInteractionUpdates(knight);

            if(oldLevel!=knight.getLevel())        // XPAngel l-a ajutat sa dea level up
                knight.notifyLevelUp(oldLevel,knight.getId());



        }
    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()!=0)
        {
            int oldLevel=pyromancer.getLevel();
            pyromancer.setXp(pyromancer.getXp() + 50);
            pyromancer.levelUp();
            notifyInteractionUpdates(pyromancer);

            if(oldLevel!=pyromancer.getLevel())
                pyromancer.notifyLevelUp(oldLevel,pyromancer.getId());
        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()!=0)
        {
            int oldLevel=rogue.getLevel();
            rogue.setXp(rogue.getXp() + 40);
            rogue.levelUp();
            notifyInteractionUpdates(rogue);

            if(oldLevel!=rogue.getLevel())
                rogue.notifyLevelUp(oldLevel,rogue.getId());
        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()!=0) {
            int oldLevel=wizard.getLevel();
            wizard.setXp(wizard.getXp() + 60);
            wizard.levelUp();
            notifyInteractionUpdates(wizard);

            if(oldLevel!=wizard.getLevel())
                wizard.notifyLevelUp(oldLevel,wizard.getId());
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel XPAngel was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }
    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification;

        notification="XPAngel helped ";

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
