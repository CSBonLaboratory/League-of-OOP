package greatMagician;

import angels.Angel;
import champions.*;

import java.util.ArrayList;


public class GreatMagician {

    private static GreatMagician instance=null;

    private static ArrayList<Champion> championObservers;

    private static ArrayList<Angel> angelObservers;

    private static ArrayList<String> notifications;

    private GreatMagician(){
        championObservers=new ArrayList<>();
        angelObservers=new ArrayList<>();
        notifications=new ArrayList<>();
    }

    public ArrayList<Champion> getChampionObservers()
    {
        return championObservers;
    }
    public ArrayList<Angel> getAngelObservers()
    {
        return angelObservers;
    }
    public ArrayList<String> getNotifications()
    {
        return notifications;
    }

    public void addObserver(Champion champion)
    {
        championObservers.add(champion);
    }
    public void addObserver(Angel angel)
    {
        angelObservers.add(angel);
    }

    public void updateChampionsKills(int win,int lose)
    {
        championObservers.get(win).update(win,lose,championObservers.get(lose));

    }
    public void updateChampionsLevels(int oldLvel,int id)
    {
        championObservers.get(id).updateLevels(oldLvel,championObservers.get(id));
    }


    public void updateAngelSpawns(Angel angel)
    {
         for(int i=0;i<angelObservers.size();i++)
             if(angelObservers.get(i)==angel)
                 angelObservers.get(i).updateSpawn();
    }
    public void updateAngelInteraction(Angel angel,Champion champion)
    {
           angel.updateInteraction(champion);
    }



    public static GreatMagician getInstance()
    {
        if(instance==null)
            instance=new GreatMagician();

        return instance;

    }
}
