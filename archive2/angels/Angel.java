package angels;

import champions.*;
import greatMagician.GreatMagician;

abstract  public class Angel {

    private int row;
    private int collumn;
    protected boolean isGood;
    protected GreatMagician subject;

    public Angel(int x,int y)
    {
        GreatMagician magician=GreatMagician.getInstance();
        this.row=x;
        this.collumn=y;
        this.subject=magician;

    }
    public int getRow()
    {
        return  row;
    }
    public int getCollumn()
    {
        return collumn;
    }
    public boolean getType()
    {
        return isGood;
    }

    protected void changeModifiers(Champion champion,float bonus)
    {

        champion.getBonuses().get(0).put("Rogue",champion.getBonuses().get(0).get("Rogue")+bonus);
        champion.getBonuses().get(0).put("Knight",champion.getBonuses().get(0).get("Knight")+bonus);
        champion.getBonuses().get(0).put("Pyromancer",champion.getBonuses().get(0).get("Pyromancer")+bonus);
        champion.getBonuses().get(0).put("Wizard",champion.getBonuses().get(0).get("Wizard")+bonus);

        champion.getBonuses().get(1).put("Rogue",champion.getBonuses().get(1).get("Rogue")+bonus);
        champion.getBonuses().get(1).put("Knight",champion.getBonuses().get(1).get("Knight")+bonus);
        champion.getBonuses().get(1).put("Pyromancer",champion.getBonuses().get(1).get("Pyromancer")+bonus);
        champion.getBonuses().get(1).put("Wizard",champion.getBonuses().get(1).get("Wizard")+bonus);



        if(champion instanceof Knight && ((Knight) champion).firstVisit==false && bonus<0)
            champion.getBonuses().get(0).put("Knight",champion.getBonuses().get(0).get("Knight")-bonus);

        if(champion instanceof Knight && bonus>0)
            ((Knight) champion).firstVisit=true;


    }





    public void notifySpawnUpdates()
    {
        this.subject.updateAngelSpawns(this);
    }

    public void notifyInteractionUpdates(Champion champion)
    {
        this.subject.updateAngelInteraction(this,champion);
    }


    abstract public void interactWith(Knight champion);
    abstract public void interactWith(Pyromancer champion);
    abstract public void interactWith(Wizard champion);
    abstract public void interactWith(Rogue champion);
    abstract public void updateSpawn();
    abstract public void updateInteraction(Champion champion);

}
