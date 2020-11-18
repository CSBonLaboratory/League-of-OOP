package angels;


import champions.*;
import greatMagician.GreatMagician;

public class Spawner extends Angel {

    public Spawner(int x,int y)
    {
        super(x,y);
        isGood=true;
    }

    public void interactWith(Knight knight)
    {
       if(knight.getCurrentHp()==0) {
           knight.setCurrentHp(200);

           notifyInteractionUpdates(knight);
       }

    }
    public void interactWith(Pyromancer pyromancer)
    {
        if(pyromancer.getCurrentHp()==0) {
            pyromancer.setCurrentHp(150);

            notifyInteractionUpdates(pyromancer);
        }
    }
    public void interactWith(Rogue rogue)
    {
        if(rogue.getCurrentHp()==0) {
            rogue.setCurrentHp(180);

            notifyInteractionUpdates(rogue);
        }
    }
    public void interactWith(Wizard wizard)
    {
        if(wizard.getCurrentHp()==0) {
            wizard.setCurrentHp(120);

            notifyInteractionUpdates(wizard);
        }
    }
    public void updateSpawn()
    {
        GreatMagician magician=GreatMagician.getInstance();

        String spawn;

        spawn="Angel Spawner was spawned at "+this.getRow()+" "+this.getCollumn();

        magician.getNotifications().add(spawn);
    }

    public void updateInteraction(Champion champion)
    {
        GreatMagician greatMagician=GreatMagician.getInstance();

        String notification,respawn="";

        notification="Spawner helped ";

        if(champion instanceof Pyromancer) {
            notification = notification + "Pyromancer " + champion.getId();
            respawn="Player Pyromancer "+champion.getId()+" was brought to life by an angel";
        }
        if(champion instanceof Knight) {
            notification = notification + "Knight " + champion.getId();
            respawn="Player Knight "+champion.getId()+" was brought to life by an angel";
        }
        if(champion instanceof Rogue) {
            notification = notification + "Rogue " + champion.getId();
            respawn="Player Rogue "+champion.getId()+" was brought to life by an angel";
        }
        if(champion instanceof Wizard) {
            notification = notification + "Wizard " + champion.getId();
            respawn="Player Wizard "+champion.getId()+" was brought to life by an angel";
        }
        greatMagician.getNotifications().add(notification);
        greatMagician.getNotifications().add(respawn);

    }
}
