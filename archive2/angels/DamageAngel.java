package angels;


import champions.*;
import greatMagician.GreatMagician;

public class DamageAngel extends Angel {

     public DamageAngel(int x,int y)
     {
         super(x,y);
         isGood=true;
     }

     public void interactWith(Knight knight)
     {
         if(knight.getCurrentHp()!=0) {
             this.changeModifiers(knight, 0.15f);
             this.notifyInteractionUpdates(knight);
         }

     }
     public void interactWith(Pyromancer pyromancer)
     {
         if(pyromancer.getCurrentHp()!=0) {
             this.changeModifiers(pyromancer, 0.2f);
             this.notifyInteractionUpdates(pyromancer);
         }
     }
     public void interactWith(Rogue rogue)
     {
         if(rogue.getCurrentHp()!=0) {
             this.changeModifiers(rogue, 0.3f);
             this.notifyInteractionUpdates(rogue);
         }
     }
     public void interactWith(Wizard wizard)
     {
         if(wizard.getCurrentHp()!=0) {
             this.changeModifiers(wizard, 0.4f);
             this.notifyInteractionUpdates(wizard);
         }
     }

     public void updateSpawn()
     {
         GreatMagician magician=GreatMagician.getInstance();

         String spawn;

         spawn="Angel DamageAngel was spawned at "+this.getRow()+" "+this.getCollumn();

         magician.getNotifications().add(spawn);
     }

     public void updateInteraction(Champion champion)
     {
         GreatMagician greatMagician=GreatMagician.getInstance();

         String notification;

         notification="DamageAngel helped ";

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
