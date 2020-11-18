
//Bontas Carol Sebastian 321CA
package main;
import angels.Angel;
import champions.*;
import greatMagician.GreatMagician;


import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        inputReader input = new inputReader(args[0]);
        int nrChampions = input.getNrChampions();
        ArrayList<Champion> champions = input.getChampionArray();
        int nrRounds = input.getNrRounds();
        String[] moves = input.getMoves();
        ArrayList<ArrayList<Angel>> angelArray=input.getAngelArray();


        int i;
        GreatMagician magician=GreatMagician.getInstance();






        for (i=0;i<nrRounds;i++)
        {




            magician.getNotifications().add("~~ Round "+(i+1)+" ~~");

            movePhase(moves,champions,i);

            battlePhase(champions);

            angelPhase(champions,angelArray.get(i));






            magician.getNotifications().add("\n");
        }

        magician.getNotifications().add("~~ Results ~~");



        outputReader output= new outputReader(args[1],champions);

    }

    private static void movePhase(String[] moves,ArrayList<Champion> champions,int currentRound)
    {
         int i;

         for(i=0;i<champions.size();i++)
             if(champions.get(i).canMove()==true && champions.get(i).getCurrentHp()>0)
             {
                 switch (moves[currentRound].charAt(i))
                 {
                     case 'L':
                         champions.get(i).setColumn(champions.get(i).getColumn()-1);
                         break;
                     case 'R':
                         champions.get(i).setColumn(champions.get(i).getColumn()+1);
                         break;
                     case 'U':
                         champions.get(i).setRow(champions.get(i).getRow()-1);
                         break;
                     case 'D':
                         champions.get(i).setRow(champions.get(i).getRow()+1);

                 }
             }
    }
    private static void battlePhase(ArrayList<Champion> champions) {


       int i,j;
       boolean canAttack;
       Arena arena=Arena.getInstance();

        for(j=0;j<champions.size();j++)
        {
            if(champions.get(j).getEffect()!=null)       //aplica DoT
            {

                float damage=champions.get(j).getEffect().getDamage();
                if(damage<champions.get(j).getCurrentHp())
                    champions.get(j).setCurrentHp(champions.get(j).getCurrentHp() - damage);

                else
                    champions.get(j).setCurrentHp(0);



            }
            if(champions.get(j).getCurrentHp()!=0 && champions.get(j).canMove()==true)  //aplica strategii
            {
                champions.get(j).chooseStrategy();
                if(champions.get(j).getStrategy()!=null)
                champions.get(j).applyStrategy();
            }

            if(champions.get(j).getEffect()!=null)    // se reduce durata efectului la sfarsitul rundei
            {
                champions.get(j).getEffect().reduceDuration();

                if(champions.get(j).getEffect().getDuration()==0)
                    champions.get(j).deleteEffect();
            }
        }


       for(i=0;i<champions.size();i++)
       {


           if(champions.get(i).getCurrentHp()>0) {
               canAttack = true;

               for (j = 0; j < i; j++) {
                   if (champions.get(j).getCurrentHp() > 0 &&
                           champions.get(j).getRow() == champions.get(i).getRow() &&
                           champions.get(j).getColumn() == champions.get(i).getColumn()
                   ) {
                       canAttack = false;            // a gasit un campion cu care s-a luptat inainte deci nu mai exista un campion dupa el cu care se poate lupta


                       break;
                   }


               }

               if (canAttack == true) {
                   for (j = i + 1; j < champions.size(); j++) {
                       if (champions.get(j).getCurrentHp() > 0 &&
                               champions.get(j).getRow() == champions.get(i).getRow() &&
                               champions.get(j).getColumn() == champions.get(i).getColumn()
                       ) {
                           float damageI, damageJ;
                           int levelI, levelJ;



                           damageI = champions.get(i).accept(champions.get(j));
                           damageJ = champions.get(j).accept(champions.get(i));





                           if(damageJ>=champions.get(j).getCurrentHp())
                               champions.get(i).notifyKillUpdates(i,j);       // I este id-ul invingatorului iar J este id-ul pierzatorului

                           if(damageI>=champions.get(i).getCurrentHp())
                               champions.get(j).notifyKillUpdates(j,i);      // J este id-ul invingatorului iar I este id-ul pierzatorului


                           newHP(champions.get(i), damageI);
                           newHP(champions.get(j), damageJ);


                           levelI = champions.get(i).getLevel();
                           levelJ = champions.get(j).getLevel();




                           if (champions.get(i).getCurrentHp() == 0 && champions.get(j).getCurrentHp() == 0)
                               break;

                           if (champions.get(i).getCurrentHp() == 0) {
                               champions.get(j).giveXP(levelI);
                               if(champions.get(j).getLevel()!=levelJ)        // a dat level up dupa ce a omorat un campion
                               {
                                   champions.get(j).notifyLevelUp(levelJ,j);
                               }
                           }
                           if (champions.get(j).getCurrentHp() == 0) {
                               champions.get(i).giveXP(levelJ);
                               if(champions.get(i).getLevel()!=levelI)   // a dat level up dupa ce a omorat un campion
                               {
                                   champions.get(i).notifyLevelUp(levelI,i);
                               }

                           }




                           break;
                       }

                   }

               }
           }




       }



    }

    public static void angelPhase(ArrayList<Champion> champions,ArrayList<Angel> angelRound)
    {
        int i,j;

        for(i=0;i<angelRound.size();i++)
        {
            angelRound.get(i).notifySpawnUpdates();

            for (j=0;j<champions.size();j++)
            {

                if(angelRound.get(i).getRow()==champions.get(j).getRow() && angelRound.get(i).getCollumn()==champions.get(j).getColumn())
                {



                    champions.get(j).acceptAngel(angelRound.get(i));

                }
            }
        }

        for(i=0;i<champions.size();i++) {
            if (champions.get(i).getCurrentHp() < 0)
                champions.get(i).setCurrentHp(0);




        }


    }

    private static void newHP(Champion victim,float damage)        //functie cu care calculam hp-ul ramas dupa batalie ; NU se foloseste pentru calculul hp-ulul dupa aplicarea efectelor
    {


            if(damage<victim.getCurrentHp())
            {
                victim.setCurrentHp(victim.getCurrentHp()-damage);
            }
            else
            {
                victim.setCurrentHp(0);

            }


    }

}
